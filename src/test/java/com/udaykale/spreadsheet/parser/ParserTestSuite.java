package com.udaykale.spreadsheet.parser;

import com.udaykale.spreadsheet.parser.cell.CellParseTestSuite;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        CellParseTestSuite.class,
        RowParserTest.class,
        SheetParserTest.class
})
public class ParserTestSuite {
}  