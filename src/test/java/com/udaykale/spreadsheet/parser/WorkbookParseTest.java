package com.udaykale.spreadsheet.parser;

import com.udaykale.spreadsheet.SpringTest;
import com.udaykale.spreadsheet.custom.NegativeTest;
import com.udaykale.spreadsheet.custom.PositiveTest;
import com.udaykale.spreadsheet.domain.workbook.TestWorkbook;
import com.udaykale.spreadsheet.domain.workbook.TestWorkbookWithoutAnnotation;
import com.udaykale.spreadsheet.extension.CellDeserializerException;
import org.apache.poi.ss.usermodel.Workbook;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import javax.inject.Inject;
import java.lang.reflect.InvocationTargetException;

/**
 * @author uday
 */
public class WorkbookParseTest extends SpringTest {

    @Inject
    Workbook workbook;

    @Test
    @PositiveTest
    public void parseWholeWorkbook()
            throws CellDeserializerException, InstantiationException, InvocationTargetException, NoSuchMethodException, RowParserException, IllegalAccessException, CellParserException, SheetParserException, WorkbookParserException {

        WorkbookParser<TestWorkbook> workbookParser = new WorkbookParser<>();
        workbookParser.parse(workbook, TestWorkbook.class);
    }

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    @NegativeTest
    public void nullWorkbook()
            throws CellDeserializerException, InstantiationException, InvocationTargetException, NoSuchMethodException, RowParserException, IllegalAccessException, CellParserException, SheetParserException, WorkbookParserException {

        expectedException.expect(WorkbookParserException.class);
        expectedException.expectMessage("Workbook cannot be null");
        WorkbookParser<TestWorkbook> workbookParser = new WorkbookParser<>();
        workbookParser.parse(null, TestWorkbook.class);
    }

    @Test
    @NegativeTest
    public void nullTClass()
            throws CellDeserializerException, InstantiationException, InvocationTargetException, NoSuchMethodException, RowParserException, IllegalAccessException, CellParserException, SheetParserException, WorkbookParserException {

        expectedException.expect(WorkbookParserException.class);
        expectedException.expectMessage("tClass cannot be null");
        WorkbookParser<TestWorkbook> workbookParser = new WorkbookParser<>();
        workbookParser.parse(workbook, null);
    }

    @Test
    @NegativeTest
    public void testWorkbookWithoutAnnotation()
            throws CellDeserializerException, InstantiationException, InvocationTargetException, NoSuchMethodException, RowParserException, IllegalAccessException, CellParserException, SheetParserException, WorkbookParserException {

        expectedException.expect(WorkbookParserException.class);
        expectedException.expectMessage("tClass does not have @Workbook annotation");
        WorkbookParser<TestWorkbookWithoutAnnotation> workbookParser = new WorkbookParser<>();
        workbookParser.parse(workbook, TestWorkbookWithoutAnnotation.class);
    }
}
