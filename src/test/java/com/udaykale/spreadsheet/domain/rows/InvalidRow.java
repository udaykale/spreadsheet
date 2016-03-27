package com.udaykale.spreadsheet.domain.rows;

import com.udaykale.spreadsheet.annotation.Cell;

/**
 * @author uday
 */
public class InvalidRow {

    @Cell(position = 1)
    private Exception exception;
}
