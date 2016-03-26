package com.udaykale.spreadsheet.domain.sheets;

import com.udaykale.spreadsheet.annotation.Row;
import com.udaykale.spreadsheet.annotation.Rows;
import com.udaykale.spreadsheet.domain.rows.StudentInfoSheetHeader;
import com.udaykale.spreadsheet.domain.rows.StudentInformationRow;

import java.util.List;

/**
 * @author uday
 */
public class StudentInformationSheet {

    @Row
    private StudentInfoSheetHeader studentInfoSheetHeader;

    @Rows
    private List<StudentInformationRow> testRows1;

    private String name;

    private Integer rowCount;
}
