package com.udaykale.spreadsheet.domain.deserializer;

import com.udaykale.spreadsheet.extension.CellDeserializer;
import com.udaykale.spreadsheet.extension.CellDeserializerException;
import org.apache.poi.ss.usermodel.Cell;

/**
 * @author uday
 */
public class AverageCheckCellDeserializer extends CellDeserializer<Boolean> {

    private static final String AVERAGE_STRING = "Average";

    @Override
    public Boolean deserialize(Cell cell)
            throws CellDeserializerException {

        if (Cell.CELL_TYPE_STRING != cell.getCellType()) {
            throw new CellDeserializerException("Cell is not of String type");
        }

        return AVERAGE_STRING.equals(cell.getStringCellValue());
    }
}
