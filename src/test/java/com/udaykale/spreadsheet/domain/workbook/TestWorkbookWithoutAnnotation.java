package com.udaykale.spreadsheet.domain.workbook;

import com.udaykale.spreadsheet.annotation.Sheet;
import com.udaykale.spreadsheet.domain.sheets.StudentInformationSheet;

/**
 * @author uday
 */
public class TestWorkbookWithoutAnnotation {

    @Sheet(name = "info")
    private StudentInformationSheet testSheet2;

    private String name;
}
