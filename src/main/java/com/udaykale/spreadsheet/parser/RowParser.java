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
 * @param <T>
 * @author uday
 */
public class RowParser<T> {

    /**
     * @param row
     * @param tClass
     * @param fieldMap
     * @return
     * @throws IllegalAccessException
     * @throws InstantiationException
     * @throws NoSuchMethodException
     * @throws InvocationTargetException
     */
    public T parse(Row row, Class<T> tClass, Map<Integer, Field> fieldMap)
            throws IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {

        T instance = null;

        if (row != null) {
            instance = (T) tClass.newInstance();
            for (Map.Entry<Integer, Field> positionSet : fieldMap.entrySet()) {
                Cell cellValue = row.getCell(positionSet.getKey());
                setFieldFromCellValue(instance, positionSet.getValue(), cellValue);
            }
        } else {
            // Exception
        }

        return instance;
    }

    /**
     * Assigns the cell value to object depending on its type
     */
    private void setFieldFromCellValue(Object instance, Field field, Cell cell)
            throws IllegalAccessException, NoSuchMethodException, InvocationTargetException, InstantiationException {

        if (null != cell) {
            Class<?> type = field.getType();
            if (String.class == type) {
                cell.setCellType(Cell.CELL_TYPE_STRING);
                field.set(instance, cell.getStringCellValue());
            } else if (Double.class == type || Integer.class == type) {
                cell.setCellType(Cell.CELL_TYPE_NUMERIC);
                field.setDouble(instance, cell.getNumericCellValue());
            } else if (Date.class == type) {
                cell.setCellType(Cell.CELL_TYPE_STRING);
                field.set(instance, cell.getStringCellValue());
            } else if (Boolean.class == type) {
                cell.setCellType(Cell.CELL_TYPE_BOOLEAN);
                field.set(instance, cell.getBooleanCellValue());
            } else {
                com.udaykale.spreadsheet.annotation.Cell cellAnnotation = field.getAnnotation(com.udaykale.spreadsheet.annotation.Cell.class);
                CellDeserializer<?> cellDeserializer = cellAnnotation.deserializer().newInstance();
                field.set(instance, cellDeserializer.deserialize(cell));
            }
        } else {
            // Exception
        }
    }

    /**
     * @param vClazz
     * @param <V>
     * @return
     */
    public static <V> Map<Integer, Field> cellPositionAndTypeMap(Class<V> vClazz) {

        Map<Integer, Field> fieldMap = new HashMap<>();
        Field[] fields = vClazz.getDeclaredFields();

        // Find fields eligible to be parsed as cells and their cell position
        for (Field field : fields) {
            field.setAccessible(true);
            if (!field.isAnnotationPresent(com.udaykale.spreadsheet.annotation.Cell.class)) {
                continue;
            }
            Class<?> type = field.getType();
            com.udaykale.spreadsheet.annotation.Cell cell = field.getAnnotation(com.udaykale.spreadsheet.annotation.Cell.class);
            if (cell.deserializer() == CellDeserializer.class || Integer.class == type || String.class == type || Double.class == type || Boolean.class == type || Date.class == type) {
                fieldMap.put(cell.position() - 1, field);
            } else {
                // Exception
            }
        }
        return fieldMap;
    }
}
