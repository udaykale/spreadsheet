package com.udaykale.spreadsheet.domain.sheets;

import com.udaykale.spreadsheet.annotation.Row;
import com.udaykale.spreadsheet.annotation.Rows;
import com.udaykale.spreadsheet.domain.rows.StudentInfoSheetHeader;
import com.udaykale.spreadsheet.domain.rows.StudentInformationRow;

/**
 * @author uday
 */
public class StudentInformationSheet1 {

    @Row
    private StudentInfoSheetHeader studentInfoSheetHeader;

    @Rows(start = 2)
    private StudentInformationRow StudentInformationRows;

    private String name;

    private Integer rowCount;
}
