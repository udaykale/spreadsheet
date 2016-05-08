package com.udaykale.spreadsheet.parser;

import com.udaykale.spreadsheet.SpringTest;
import com.udaykale.spreadsheet.custom.NegativeTest;
import com.udaykale.spreadsheet.custom.PositiveTest;
import com.udaykale.spreadsheet.domain.sheets.StudentInformationSheet;
import com.udaykale.spreadsheet.domain.sheets.StudentInformationSheet1;
import com.udaykale.spreadsheet.domain.sheets.StudentInformationSheetNegativeEnd;
import com.udaykale.spreadsheet.domain.sheets.StudentInformationSheetNegativeStart;
import com.udaykale.spreadsheet.extension.CellDeserializerException;
import org.apache.poi.ss.usermodel.Sheet;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import javax.inject.Inject;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

/**
 * @author uday
 */
public class SheetParserTest extends SpringTest {

    @Inject
    private Sheet sheet;

    @Test
    @PositiveTest
    public void parseWholeSheetTest()
            throws IOException, IllegalAccessException, InvocationTargetException, RowParserException, InstantiationException, CellParserException, SheetParserException, CellDeserializerException, NoSuchMethodException {

        SheetParser<StudentInformationSheet> sheetParser = new SheetParser<>();
        sheetParser.parse(sheet, StudentInformationSheet.class);
    }

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    @NegativeTest
    public void nullSheet()
            throws IOException, IllegalAccessException, InvocationTargetException, RowParserException, InstantiationException, CellParserException, SheetParserException, CellDeserializerException, NoSuchMethodException {

        expectedException.expect(SheetParserException.class);
        expectedException.expectMessage("Sheet cannot be null");
        SheetParser<StudentInformationSheet> sheetParser = new SheetParser<>();
        sheetParser.parse(null, StudentInformationSheet.class);
    }

    @Test
    @NegativeTest
    public void nullSheetClassObject()
            throws IOException, IllegalAccessException, InvocationTargetException, RowParserException, InstantiationException, CellParserException, SheetParserException, CellDeserializerException, NoSuchMethodException {

        expectedException.expect(SheetParserException.class);
        expectedException.expectMessage("tClass' cannot be null");
        SheetParser<StudentInformationSheet> sheetParser = new SheetParser<>();
        sheetParser.parse(sheet, null);
    }

    @Test
    @NegativeTest
    public void rowsAnnotationOnNonList()
            throws IllegalAccessException, InvocationTargetException, RowParserException, InstantiationException, CellParserException, SheetParserException, CellDeserializerException, NoSuchMethodException {

        expectedException.expect(SheetParserException.class);
        expectedException.expectMessage("@Rows must be on a field of type 'java.util.List");
        SheetParser<StudentInformationSheet1> sheetParser = new SheetParser<>();
        sheetParser.parse(sheet, StudentInformationSheet1.class);
    }

    @Test
    @NegativeTest
    public void negativeRowsStart()
            throws IllegalAccessException, InvocationTargetException, RowParserException, InstantiationException, CellParserException, SheetParserException, CellDeserializerException, NoSuchMethodException {

        expectedException.expect(SheetParserException.class);
        expectedException.expectMessage("Start value must be >= 0");
        SheetParser<StudentInformationSheetNegativeStart> sheetParser = new SheetParser<>();
        sheetParser.parse(sheet, StudentInformationSheetNegativeStart.class);
    }

    @Test
    @NegativeTest
    public void negativeRowsEnd()
            throws IllegalAccessException, InvocationTargetException, RowParserException, InstantiationException, CellParserException, SheetParserException, CellDeserializerException, NoSuchMethodException {

        expectedException.expect(SheetParserException.class);
        expectedException.expectMessage("End value must be >= 0");
        SheetParser<StudentInformationSheetNegativeEnd> sheetParser = new SheetParser<>();
        sheetParser.parse(sheet, StudentInformationSheetNegativeEnd.class);
    }
}
