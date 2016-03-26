package com.udaykale.spreadsheet.parser.cell;

import com.udaykale.spreadsheet.custom.NegativeTest;
import com.udaykale.spreadsheet.custom.PositiveTest;
import com.udaykale.spreadsheet.domain.deserializer.AverageCheckCellDeserializer;
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
    public void deserializeToCheckAverageString()
            throws IOException, InvocationTargetException, NoSuchMethodException, InstantiationException, CellParserException, IllegalAccessException, CellDeserializerException {

        Boolean expected = true;
        ClassLoader classLoader = getClass().getClassLoader();
        String filePath = classLoader.getResource("School_Student_Information.xlsx").getPath();
        Workbook workbook = new XSSFWorkbook(filePath);
        Sheet sheet = workbook.getSheet("info");
        Row row = sheet.getRow(0);
        Cell cell = row.getCell(14);
        CellParser<Boolean> cellParser = new CellParser<>();
        AverageCheckCellDeserializer averageCheckCellDeserializer = new AverageCheckCellDeserializer();
        Boolean actual = cellParser.parse(cell, averageCheckCellDeserializer);
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
        AverageCheckCellDeserializer averageCheckCellDeserializer = new AverageCheckCellDeserializer();
        cellParser.parse(null, averageCheckCellDeserializer);
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
        Row row = sheet.getRow(0);
        Cell cell = row.getCell(14);
        CellParser<Boolean> cellParser = new CellParser<>();
        AverageCheckCellDeserializer averageCheckCellDeserializer = null;
        cellParser.parse(cell, averageCheckCellDeserializer);
    }
}
