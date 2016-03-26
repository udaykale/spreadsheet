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

        if (cell.getCellType() != Cell.CELL_TYPE_STRING) {
            throw new CellDeserializerException("Cell is not of String type");
        }

        if (AVERAGE_STRING.equals(cell.getStringCellValue())) {
            return true;
        } else {
            return false;
        }
    }
}
