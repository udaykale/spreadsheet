package com.udaykale.spreadsheet.parser;

import com.udaykale.spreadsheet.extension.CellDeserializer;
import com.udaykale.spreadsheet.extension.CellDeserializerException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author uday
 */
class RowParserWithFieldMap<T> {

    Map<RowCellFields, Integer> cellPositionAndTypeMap(Class<T> tClass)
            throws IllegalAccessException, InstantiationException {


        if (null == tClass) {
            // Exception
        }

        Map<RowCellFields, Integer> fieldMap = new HashMap<>();
        Field[] fields = tClass.getDeclaredFields();

        // Find fields eligible to be parsed as cells and their cell position
        for (Field field : fields) {
            field.setAccessible(true);
            if (!field.isAnnotationPresent(com.udaykale.spreadsheet.annotation.Cell.class)) {
                continue;
            }
            Class<?> type = field.getType();
            com.udaykale.spreadsheet.annotation.Cell cell = field.getAnnotation(com.udaykale.spreadsheet.annotation.Cell.class);
            if (cell.deserializer() != CellDeserializer.None.class) {
                fieldMap.put(new RowCellFields(field, cell.deserializer().newInstance()), cell.position() - 1);
            } else if (Integer.class == type || String.class == type || Double.class == type ||
                    Boolean.class == type || Date.class == type) {
                fieldMap.put(new RowCellFields(field, null), cell.position() - 1);
            } else {
                // Exception
            }
        }
        return fieldMap;
    }

    T parse(Row row, Class<T> tClass, Map<RowCellFields, Integer> fieldMap)
            throws IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException, CellParserException, CellDeserializerException {

        if (null == row) {
            // Exception
        }

        if (null == tClass) {
            // Exception
        }

        if (null == fieldMap) {
            // Exception
        }

        CellParser cellParser = new CellParser();
        T instance = tClass.newInstance();

        for (Map.Entry<RowCellFields, Integer> positionSet : fieldMap.entrySet()) {
            Cell cell = row.getCell(positionSet.getValue());
            RowCellFields rowCellFields = positionSet.getKey();
            Field field = rowCellFields.getField();
            if (rowCellFields.getCellDeserializer() != null) {
                field.set(instance, cellParser.parse(cell, rowCellFields.getCellDeserializer()));
            } else {
                field.set(instance, cellParser.parse(cell, field.getType()));
            }
        }

        return instance;
    }
}
