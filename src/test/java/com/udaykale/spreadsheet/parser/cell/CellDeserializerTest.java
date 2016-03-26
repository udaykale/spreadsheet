package com.udaykale.spreadsheet.parser.cell;

import com.udaykale.spreadsheet.custom.NegativeTest;
import com.udaykale.spreadsheet.custom.PositiveTest;
import com.udaykale.spreadsheet.domain.deserializer.PassCheckCellDeserializer;
import com.udaykale.spreadsheet.extension.CellDeserializerException;
import com.udaykale.spreadsheet.parser.CellParser;
import com.udaykale.spreadsheet.parser.CellParserException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

/**
 * @author uday
 */
public class CellDeserializerTest {

    @Test
    @PositiveTest
    public void deserializeAverageToFindPassed()
            throws IOException, InvocationTargetException, NoSuchMethodException, InstantiationException, CellParserException, IllegalAccessException, CellDeserializerException {

        Boolean expected = false;
        ClassLoader classLoader = getClass().getClassLoader();
        String filePath = classLoader.getResource("School_Student_Information.xlsx").getPath();
        Workbook workbook = new XSSFWorkbook(filePath);
        Sheet sheet = workbook.getSheet("info");
        Row row = sheet.getRow(1);
        Cell cell = row.getCell(14);
        CellParser<Boolean> cellParser = new CellParser<>();
        PassCheckCellDeserializer passCheckCellDeserializer = new PassCheckCellDeserializer();
        Boolean actual = cellParser.parse(cell, passCheckCellDeserializer);
        Assert.assertEquals(expected, actual);
    }

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    @NegativeTest
    public void nullCell()
            throws IOException, InvocationTargetException, NoSuchMethodException, InstantiationException, CellParserException, IllegalAccessException, CellDeserializerException {

        expectedException.expect(CellParserException.class);
        expectedException.expectMessage("Cell cannot be null");
        CellParser<Boolean> cellParser = new CellParser<>();
        PassCheckCellDeserializer passCheckCellDeserializer = new PassCheckCellDeserializer();
        cellParser.parse(null, passCheckCellDeserializer);
    }
    @Test
    @NegativeTest
    public void nullDeserializer()
            throws IOException, InvocationTargetException, NoSuchMethodException, InstantiationException, CellParserException, IllegalAccessException, CellDeserializerException {

        expectedException.expect(CellParserException.class);
        expectedException.expectMessage("Cell Deserializer cannot be null");
        ClassLoader classLoader = getClass().getClassLoader();
        String filePath = classLoader.getResource("School_Student_Information.xlsx").getPath();
        Workbook workbook = new XSSFWorkbook(filePath);
        Sheet sheet = workbook.getSheet("info");
        Row row = sheet.getRow(1);
        Cell cell = row.getCell(14);
        CellParser<Boolean> cellParser = new CellParser<>();
        PassCheckCellDeserializer passCheckCellDeserializer = null;
        cellParser.parse(cell, passCheckCellDeserializer);
    }
}
