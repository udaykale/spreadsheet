package spreadsheet.domain;

import com.sun.org.apache.xpath.internal.operations.String;
import com.udaykale.spreadsheet.annotation.Ignore;
import com.udaykale.spreadsheet.annotation.Row;
import com.udaykale.spreadsheet.annotation.Rows;

/**
 * @author uday
 */
public class TestSheet2 {

    @Rows
    private TestRows1 testRows1;

    @Row
    private TestRow1 testRow1;

    @Ignore
    private String name;

    @Ignore
    private Integer rowCount;
}
