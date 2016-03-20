package com.udaykale.spreadsheet.domain;

import com.udaykale.spreadsheet.annotation.Sheet;
import com.udaykale.spreadsheet.annotation.Workbook;

/**
 * @author uday
 */
@Workbook
public class TestWorkbook {

    @Sheet(name = "sad")
    private TestSheet1 testSheet1;

    @Sheet(name = "das")
    private TestSheet2 testSheet2;

    private String name;
}
