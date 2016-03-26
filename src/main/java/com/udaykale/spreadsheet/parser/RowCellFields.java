package com.udaykale.spreadsheet.parser;

import com.udaykale.spreadsheet.extension.CellDeserializer;

import java.lang.reflect.Field;

/**
 * @author uday
 */
public class RowCellFields {

    private Field field;
    private CellDeserializer<?> cellDeserializer;

    public RowCellFields(Field field, CellDeserializer<?> cellDeserializer) {
        this.field = field;
        this.cellDeserializer = cellDeserializer;
    }

    public Field getField() {
        return field;
    }

    public void setField(Field field) {
        this.field = field;
    }

    public CellDeserializer<?> getCellDeserializer() {
        return cellDeserializer;
    }

    public void setCellDeserializer(CellDeserializer<?> cellDeserializer) {
        this.cellDeserializer = cellDeserializer;
    }
}
