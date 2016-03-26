package com.udaykale.spreadsheet.extension;

import org.apache.poi.ss.usermodel.Cell;

/**
 * @author uday
 */
public abstract class CellDeserializer<T> {
    public abstract T deserialize(Cell cell) throws CellDeserializerException;

    public abstract static class None extends CellDeserializer {
        private None() {
        }
    }
}
