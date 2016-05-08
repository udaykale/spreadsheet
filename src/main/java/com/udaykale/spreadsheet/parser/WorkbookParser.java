package com.udaykale.spreadsheet.parser;

import com.udaykale.spreadsheet.extension.CellDeserializerException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

/**
 * @author uday
 */
public class WorkbookParser<T> {

    public T parse(Workbook workbook, Class<T> tClass)
            throws IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException, CellParserException, CellDeserializerException, RowParserException, SheetParserException, WorkbookParserException {

        if (tClass == null) {
            throw new WorkbookParserException("tClass cannot be null");
        }

        if (!tClass.isAnnotationPresent(com.udaykale.spreadsheet.annotation.Workbook.class)) {
            throw new WorkbookParserException("tClass does not have @Workbook annotation");
        }

        if (null == workbook) {
            throw new WorkbookParserException("Workbook cannot be null");
        }

        Field[] fields = tClass.getDeclaredFields();
        T workbookInstance = tClass.newInstance();
        SheetParser sheetParser = new SheetParser<>();
        for (Field field : fields) {
            field.setAccessible(true);
            if (field.isAnnotationPresent(com.udaykale.spreadsheet.annotation.Sheet.class)) {
                com.udaykale.spreadsheet.annotation.Sheet sheetAnnotation = field.getAnnotation(com.udaykale.spreadsheet.annotation.Sheet.class);
                String sheetName = sheetAnnotation.name();
                Sheet sheet = workbook.getSheet(sheetName);
                field.set(workbookInstance, sheetParser.parse(sheet, field.getType()));
            }
        }

        return workbookInstance;
    }
}
