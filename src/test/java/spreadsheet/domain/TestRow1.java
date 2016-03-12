package spreadsheet.domain;

import com.udaykale.spreadsheet.annotation.Cell;
import com.udaykale.spreadsheet.annotation.Row;

/**
 * @author uday
 */
public class TestRow1 {

    @Cell
    private Integer id;

    @Cell
    private Integer position;

    @Cell
    private String value;
}
