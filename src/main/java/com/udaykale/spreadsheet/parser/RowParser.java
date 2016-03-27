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
            throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException, CellParserException, CellDeserializerException, RowParserException {

        if (null == row) {
            throw new RowParserException("Row cannot be null");
        }

        if (null == tClass) {
            throw new RowParserException("Row Type 'tClass' cannot be null");
        }

        RowParserWithFieldMap<T> rowParserWithFieldMap = new RowParserWithFieldMap<>();
        return rowParserWithFieldMap.parse(row, tClass, rowParserWithFieldMap.cellPositionAndTypeMap(tClass));
    }
}
