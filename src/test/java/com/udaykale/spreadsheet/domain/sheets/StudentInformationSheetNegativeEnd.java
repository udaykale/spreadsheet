package com.udaykale.spreadsheet.domain.sheets;

import com.udaykale.spreadsheet.annotation.Row;
import com.udaykale.spreadsheet.annotation.Rows;
import com.udaykale.spreadsheet.domain.rows.StudentInfoSheetHeader;
import com.udaykale.spreadsheet.domain.rows.StudentInformationRow;

import java.util.List;

/**
 * @author uday
 */
public class StudentInformationSheetNegativeEnd {

    @Row
    private StudentInfoSheetHeader studentInfoSheetHeader;

    @Rows(start = 2, end = -1)
    private List<StudentInformationRow> StudentInformationRows;

    private String name;

    private Integer rowCount;
}
