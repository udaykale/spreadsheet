package spreadsheet.domain;

import com.sun.org.apache.xpath.internal.operations.String;
import com.udaykale.spreadsheet.annotation.Workbook;
import com.udaykale.spreadsheet.annotation.Ignore;
import com.udaykale.spreadsheet.annotation.Sheet;

/**
 * @author uday
 */
@Workbook
public class TestWorkbook {

    @Sheet(name = "sad")
    private TestSheet1 testSheet1;

    @Sheet(name = "das")
    private TestSheet2 testSheet2;

    @Ignore
    private String name;
}
