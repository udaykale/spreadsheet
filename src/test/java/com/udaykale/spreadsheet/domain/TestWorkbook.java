package com.udaykale.spreadsheet.domain;

import com.udaykale.spreadsheet.annotation.Sheet;
import com.udaykale.spreadsheet.annotation.Workbook;
import com.udaykale.spreadsheet.domain.sheets.StudentInformationSheet;

/**
 * @author uday
 */
@Workbook
public class TestWorkbook {

    @Sheet(name = "das")
    private StudentInformationSheet testSheet2;

    private String name;
}
