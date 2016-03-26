package com.udaykale.spreadsheet.parser;

import com.udaykale.spreadsheet.parser.cell.CellParseTestSuite;
import com.udaykale.spreadsheet.parser.row.RowParserTest;
import com.udaykale.spreadsheet.parser.sheet.SheetParserTest;
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