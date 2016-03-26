package com.udaykale.spreadsheet.parser;

import com.udaykale.spreadsheet.extension.CellDeserializerException;
import org.apache.poi.ss.usermodel.Row;

import java.lang.reflect.InvocationTargetException;

/**
 * @param <T>
 * @author uday
 */
public class RowParser<T> {


    public T parse(Row row, Class<T> tClass)
            throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException, CellParserException, CellDeserializerException {

        RowParserWithFieldMap<T> rowParserWithFieldMap = new RowParserWithFieldMap<>();
        return rowParserWithFieldMap.parse(row, tClass, rowParserWithFieldMap.cellPositionAndTypeMap(tClass));
    }
}
