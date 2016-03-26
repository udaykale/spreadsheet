package com.udaykale.spreadsheet.parser;

import com.udaykale.spreadsheet.extension.CellDeserializer;

import java.lang.reflect.Field;

/**
 * @author uday
 */
class RowCellFields {

    private Field field;
    private CellDeserializer<?> cellDeserializer;

    public RowCellFields(Field field, CellDeserializer<?> cellDeserializer) {
        this.field = field;
        this.cellDeserializer = cellDeserializer;
    }

    Field getField() {
        return field;
    }

    void setField(Field field) {
        this.field = field;
    }

    CellDeserializer<?> getCellDeserializer() {
        return cellDeserializer;
    }

    void setCellDeserializer(CellDeserializer<?> cellDeserializer) {
        this.cellDeserializer = cellDeserializer;
    }
}
