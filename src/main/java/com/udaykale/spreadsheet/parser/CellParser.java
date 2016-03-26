package com.udaykale.spreadsheet.parser;

import com.udaykale.spreadsheet.extension.CellDeserializer;
import org.apache.poi.ss.usermodel.Cell;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;

/**
 * @author uday
 */
public class CellParser<T> {

    /**
     * Assigns the cell value to object depending on its type
     */
    public T parse(Cell cell, Class<T> type)
            throws IllegalAccessException, NoSuchMethodException, InvocationTargetException, InstantiationException, SpreadsheetParserException {

        if (null == cell) {
            throw new SpreadsheetParserException("Cell cannot be null");
        }
        if (type == null) {
            throw new SpreadsheetParserException("Type cannot be null");
        }
        if (String.class == type) {
            cell.setCellType(Cell.CELL_TYPE_STRING);
            return (T) cell.getStringCellValue();
        } else if (Double.class == type) {
            cell.setCellType(Cell.CELL_TYPE_NUMERIC);
            return (T) (Double) cell.getNumericCellValue();
        } else if (Integer.class == type) {
            cell.setCellType(Cell.CELL_TYPE_NUMERIC);
            return (T) (Integer) (int) cell.getNumericCellValue();
        } else if (Date.class == type) {
            return (T) cell.getDateCellValue();
        } else if (Boolean.class == type) {
            cell.setCellType(Cell.CELL_TYPE_BOOLEAN);
            return (T) (Boolean) cell.getBooleanCellValue();
        } else {
            throw new SpreadsheetParserException(String.format("Unable to parse the type '%s'", type.toString()));
        }
    }

    /**
     * Assigns the cell value to object depending on its type
     */
    public T parse(Cell cell, CellDeserializer<T> cellDeserializer)
            throws IllegalAccessException, NoSuchMethodException, InvocationTargetException, InstantiationException, SpreadsheetParserException {

        if (null == cell) {
            throw new SpreadsheetParserException("Cell cannot be null");
        }

        if (null == cellDeserializer) {
            throw new SpreadsheetParserException("Cell Deserializer cannot be null");
        }

        return cellDeserializer.deserialize(cell);
    }
}
