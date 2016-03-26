package com.udaykale.spreadsheet.domain.deserializer;

import com.udaykale.spreadsheet.extension.CellDeserializer;
import com.udaykale.spreadsheet.extension.CellDeserializerException;
import org.apache.poi.ss.usermodel.Cell;

/**
 * @author uday
 */
public class PassCheckCellDeserializer extends CellDeserializer<Boolean> {

    private static final double minPassingScore = 25.0;

    @Override
    public Boolean deserialize(Cell cell)
            throws CellDeserializerException {

        if (cell.getCellType() != Cell.CELL_TYPE_NUMERIC) {
            throw new CellDeserializerException("Cell is not of Numeric type");
        }

        if (minPassingScore <= cell.getNumericCellValue()) {
            return true;
        } else {
            return false;
        }
    }
}
