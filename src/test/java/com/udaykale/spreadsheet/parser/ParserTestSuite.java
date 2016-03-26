package com.udaykale.spreadsheet.parser;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        CellParserTest.class,
        RowParserTest.class,
        SheetParserTest.class
})
public class ParserTestSuite {
}  