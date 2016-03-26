package com.udaykale.spreadsheet.parser;

import com.udaykale.spreadsheet.extension.CellDeserializer;
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

    Map<Integer, RowCellFields> cellPositionAndTypeMap(Class<T> vClazz)
            throws IllegalAccessException, InstantiationException {

        Map<Integer, RowCellFields> fieldMap = new HashMap<>();
        Field[] fields = vClazz.getDeclaredFields();

        // Find fields eligible to be parsed as cells and their cell position
        for (Field field : fields) {
            field.setAccessible(true);
            if (!field.isAnnotationPresent(com.udaykale.spreadsheet.annotation.Cell.class)) {
                continue;
            }
            Class<?> type = field.getType();
            com.udaykale.spreadsheet.annotation.Cell cell = field.getAnnotation(com.udaykale.spreadsheet.annotation.Cell.class);
            if (Integer.class == type || String.class == type || Double.class == type ||
                    Boolean.class == type || Date.class == type) {
                fieldMap.put(cell.position() - 1, new RowCellFields(field, null));
            } else if (cell.deserializer() != CellDeserializer.None.class) {
                fieldMap.put(cell.position() - 1, new RowCellFields(field, cell.deserializer().newInstance()));
            } else {
                // Exception
            }
        }
        return fieldMap;
    }

    T parse(Row row, Class<T> tClass, Map<Integer, RowCellFields> fieldMap)
            throws IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException, SpreadsheetParserException {

        if (row == null) {
            // Exception
        }

        CellParser cellParser = new CellParser();
        T instance = tClass.newInstance();

        for (Map.Entry<Integer, RowCellFields> positionSet : fieldMap.entrySet()) {
            Cell cell = row.getCell(positionSet.getKey());
            RowCellFields rowCellFields = positionSet.getValue();
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
