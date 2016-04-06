package com.udaykale.spreadsheet.parser;

import com.udaykale.spreadsheet.extension.CellDeserializerException;
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

    public T parse(Sheet sheet, Class<T> tClass)
            throws IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException, CellParserException, CellDeserializerException, RowParserException, SheetParserException {

        if (null == sheet) {
            throw new SheetParserException("Sheet cannot be null");
        }

        if (null == tClass) {
            throw new SheetParserException("'tClass' cannot be null");
        }

        Field[] fields = tClass.getDeclaredFields();
        T sheetInstance = tClass.newInstance();

        for (Field field : fields) {
            field.setAccessible(true);
            if (field.isAnnotationPresent(com.udaykale.spreadsheet.annotation.Row.class)) {
                parseRow(sheet, sheetInstance, field);
            } else if (field.isAnnotationPresent(com.udaykale.spreadsheet.annotation.Rows.class)) {
                parseRows(sheet, sheetInstance, field);
            }
        }

        return sheetInstance;
    }

    private void parseRows(Sheet sheet, T sheetInstance, Field field)
            throws IllegalAccessException, InstantiationException, RowParserException, NoSuchMethodException, InvocationTargetException, CellParserException, CellDeserializerException, SheetParserException {

        if (!List.class.isAssignableFrom(field.getType())) {
            throw new SheetParserException("@Rows must be on a field of type 'java.util.List'");
        }
        com.udaykale.spreadsheet.annotation.Rows rowsAnnotation = field.getAnnotation(com.udaykale.spreadsheet.annotation.Rows.class);
        int start = rowsAnnotation.start();
        if (start < 0) {
            throw new SheetParserException("Start value must be >= 0");
        }
        if (start != 0) {
            start = rowsAnnotation.start() - 1;
        }
        int end = rowsAnnotation.end();
        if (end < 0) {
            throw new SheetParserException("End value must be >= 0");
        }
        if (end == 0) {
            end = sheet.getPhysicalNumberOfRows();
        }
        List list = new ArrayList<>();
        ParameterizedType fieldParameterizedType = (ParameterizedType) field.getGenericType();
        Class<?> fieldActualClass = (Class<?>) fieldParameterizedType.getActualTypeArguments()[0];
        RowParserWithFieldMap rowParserWithFieldMap = new RowParserWithFieldMap();
        Map<Integer, RowCellFields> fieldMap = rowParserWithFieldMap.cellPositionAndTypeMap(fieldActualClass);
        for (int position = start; position < end; position++) {
            Row row = sheet.getRow(position);
            list.add(rowParserWithFieldMap.parse(row, fieldActualClass, fieldMap));
        }
        field.set(sheetInstance, list);
    }

    private void parseRow(Sheet sheet, T sheetInstance, Field field)
            throws IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException, CellParserException, CellDeserializerException, RowParserException {

        com.udaykale.spreadsheet.annotation.Row rowAnnotation = field.getAnnotation(com.udaykale.spreadsheet.annotation.Row.class);
        int position = rowAnnotation.position();
        Row row = sheet.getRow(position);
        RowParserWithFieldMap rowParserWithFieldMap = new RowParserWithFieldMap();
        field.set(sheetInstance, rowParserWithFieldMap.parse(row, field.getType(), rowParserWithFieldMap.cellPositionAndTypeMap(field.getType())));
    }
}
