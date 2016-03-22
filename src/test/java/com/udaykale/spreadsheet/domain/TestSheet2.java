package com.udaykale.spreadsheet.domain;

import com.udaykale.spreadsheet.annotation.Row;
import com.udaykale.spreadsheet.annotation.Rows;
import com.udaykale.spreadsheet.domain.rows.SchoolStudentInfoSheetHeader;

import java.util.List;

/**
 * @author uday
 */
public class TestSheet2 {

    @Rows
    private List<TestRows1> testRows1;

    @Row
    private SchoolStudentInfoSheetHeader schoolStudentInfoSheetHeader;

    private String name;

    private Integer rowCount;
}
