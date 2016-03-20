package com.udaykale.spreadsheet.parser;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author uday
 */
public class SheetParser<T> {

    protected T parse(Sheet sheet, Class<T> tClass)
            throws IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {

        Field[] fields = tClass.getDeclaredFields();
        T sheetInstance = tClass.newInstance();
        RowParser rowParser = new RowParser();

        for (Field field : fields) {
            field.setAccessible(true);
            if (field.isAnnotationPresent(com.udaykale.spreadsheet.annotation.Row.class)) {
                com.udaykale.spreadsheet.annotation.Row rowAnnotation = field.getAnnotation(com.udaykale.spreadsheet.annotation.Row.class);
                int position = rowAnnotation.position();
                Row row = sheet.getRow(position);
                Map<Integer, Field> fieldMap = RowParser.cellPositionAndTypeMap(field.getType());
                field.set(sheetInstance, rowParser.parse(row, field.getType(), fieldMap));
            } else if (field.isAnnotationPresent(com.udaykale.spreadsheet.annotation.Rows.class)) {
                field.setAccessible(true);
                if (List.class.isAssignableFrom(field.getType())) {
                    // Error
                }
                com.udaykale.spreadsheet.annotation.Rows rowsAnnotation = field.getAnnotation(com.udaykale.spreadsheet.annotation.Rows.class);
                int start = rowsAnnotation.start() - 1;
                int end = rowsAnnotation.end();
                List list = new ArrayList<>();
                ParameterizedType fieldParameterizedType = (ParameterizedType) field.getGenericType();
                Class<?> fieldActualClass = (Class<?>) fieldParameterizedType.getActualTypeArguments()[0];
                Map<Integer, Field> fieldMap = RowParser.cellPositionAndTypeMap(fieldActualClass);
                for (int position = start; position <= end; position++) {
                    Row row = sheet.getRow(position);
                    list.add(rowParser.parse(row, fieldActualClass, fieldMap));
                }
                field.set(sheetInstance, list);
            }
        }

        return sheetInstance;
    }
}
