package com.udaykale.spreadsheet.parser;

import com.udaykale.spreadsheet.custom.NegativeTest;
import com.udaykale.spreadsheet.custom.PositiveTest;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author uday
 */
public class CellParserTest {

    @Test
    @PositiveTest
    public void parseCorrectString()
            throws IOException, InvalidFormatException, InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException, CellParserException {

        String expected = "Registration#";
        ClassLoader classLoader = getClass().getClassLoader();
        String filePath = classLoader.getResource("School_Student_Information.xlsx").getPath();
        Workbook workbook = new XSSFWorkbook(filePath);
        Sheet sheet = workbook.getSheet("info");
        Row row = sheet.getRow(0);
        Cell cell = row.getCell(0);
        CellParser<String> cellParser = new CellParser<>();
        String actual = cellParser.parse(cell, String.class);
        workbook.close();
        Assert.assertEquals(expected, actual);
    }

    @Test
    @PositiveTest
    public void parseCorrectInteger()
            throws IOException, InvalidFormatException, InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException, CellParserException {

        Integer expected = 1;
        ClassLoader classLoader = getClass().getClassLoader();
        String filePath = classLoader.getResource("School_Student_Information.xlsx").getPath();
        Workbook workbook = new XSSFWorkbook(filePath);
        Sheet sheet = workbook.getSheet("info");
        Row row = sheet.getRow(1);
        Cell cell = row.getCell(0);
        CellParser<Integer> cellParser = new CellParser<>();
        Integer actual = cellParser.parse(cell, Integer.class);
        workbook.close();
        Assert.assertEquals(expected, actual);
    }

    @Test
    @PositiveTest
    public void parseCorrectDate()
            throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException, IOException, ParseException, CellParserException {

        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yy");
        Date expected = formatter.parse("10/10/05");
        ClassLoader classLoader = getClass().getClassLoader();
        String filePath = classLoader.getResource("School_Student_Information.xlsx").getPath();
        Workbook workbook = new XSSFWorkbook(filePath);
        Sheet sheet = workbook.getSheet("info");
        Row row = sheet.getRow(1);
        Cell cell = row.getCell(10);
        CellParser<Date> cellParser = new CellParser<>();
        Date actual = cellParser.parse(cell, Date.class);
        workbook.close();
        Assert.assertEquals(expected, actual);
    }

    @Test
    @PositiveTest
    public void parseCorrectBoolean()
            throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException, IOException, ParseException, CellParserException {

        Boolean expected = Boolean.TRUE;
        ClassLoader classLoader = getClass().getClassLoader();
        String filePath = classLoader.getResource("School_Student_Information.xlsx").getPath();
        Workbook workbook = new XSSFWorkbook(filePath);
        Sheet sheet = workbook.getSheet("info");
        Row row = sheet.getRow(1);
        Cell cell = row.getCell(13);
        CellParser<Boolean> cellParser = new CellParser<>();
        Boolean actual = cellParser.parse(cell, Boolean.class);
        workbook.close();
        Assert.assertEquals(expected, actual);
    }

    @Test
    @PositiveTest
    public void parseCorrectDouble()
            throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException, IOException, ParseException, CellParserException {

        Double expected = 23.41;
        ClassLoader classLoader = getClass().getClassLoader();
        String filePath = classLoader.getResource("School_Student_Information.xlsx").getPath();
        Workbook workbook = new XSSFWorkbook(filePath);
        Sheet sheet = workbook.getSheet("info");
        Row row = sheet.getRow(1);
        Cell cell = row.getCell(14);
        CellParser<Double> cellParser = new CellParser<>();
        Double actual = cellParser.parse(cell, Double.class);
        workbook.close();
        Assert.assertEquals(expected, actual);
    }

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    @NegativeTest
    public void nullCell()
            throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException, IOException, ParseException, CellParserException {

        expectedException.expect(CellParserException.class);
        expectedException.expectMessage("Cell cannot be null");
        CellParser<Double> cellParser = new CellParser<>();
        cellParser.parse(null, Double.class);
    }

    @Test
    @NegativeTest
    public void nullType()
            throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException, IOException, ParseException, CellParserException {

        expectedException.expect(CellParserException.class);
        expectedException.expectMessage("Type cannot be null");
        ClassLoader classLoader = getClass().getClassLoader();
        String filePath = classLoader.getResource("School_Student_Information.xlsx").getPath();
        Workbook workbook = new XSSFWorkbook(filePath);
        Sheet sheet = workbook.getSheet("info");
        Row row = sheet.getRow(1);
        Cell cell = row.getCell(14);
        CellParser<Double> cellParser = new CellParser<>();
        Class<Double> clazz = null;
        cellParser.parse(cell, clazz);
    }

    @Test
    @NegativeTest
    public void invalidType()
            throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException, IOException, ParseException, CellParserException {

        expectedException.expect(CellParserException.class);
        expectedException.expectMessage("Unable to parse the type 'class java.lang.Exception'");
        ClassLoader classLoader = getClass().getClassLoader();
        String filePath = classLoader.getResource("School_Student_Information.xlsx").getPath();
        Workbook workbook = new XSSFWorkbook(filePath);
        Sheet sheet = workbook.getSheet("info");
        Row row = sheet.getRow(1);
        Cell cell = row.getCell(14);
        CellParser<Exception> cellParser = new CellParser<>();
        Class<Exception> clazz = Exception.class;
        cellParser.parse(cell, clazz);
    }
}