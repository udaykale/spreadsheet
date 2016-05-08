package com.udaykale.spreadsheet.parser;

import com.udaykale.spreadsheet.SpringTest;
import com.udaykale.spreadsheet.custom.NegativeTest;
import com.udaykale.spreadsheet.custom.PositiveTest;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import javax.annotation.Resource;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author uday
 */
public class CellParserTest extends SpringTest {

    @Resource(name = "secondRow")
    private Row row;

    @Test
    @PositiveTest
    public void parseCorrectString()
            throws IOException, InvalidFormatException, InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException, CellParserException {

        String expected = "Tom";
        Cell cell = row.getCell(4);
        CellParser<String> cellParser = new CellParser<>();
        String actual = cellParser.parse(cell, String.class);
        Assert.assertEquals(expected, actual);
    }

    @Test
    @PositiveTest
    public void parseCorrectInteger()
            throws IOException, InvalidFormatException, InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException, CellParserException {

        Integer expected = 1;
        Cell cell = row.getCell(0);
        CellParser<Integer> cellParser = new CellParser<>();
        Integer actual = cellParser.parse(cell, Integer.class);
        Assert.assertEquals(expected, actual);
    }

    @Test
    @PositiveTest
    public void parseCorrectDate()
            throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException, IOException, ParseException, CellParserException {

        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yy");
        Date expected = formatter.parse("10/10/05");
        Cell cell = row.getCell(10);
        CellParser<Date> cellParser = new CellParser<>();
        Date actual = cellParser.parse(cell, Date.class);
        Assert.assertEquals(expected, actual);
    }

    @Test
    @PositiveTest
    public void parseCorrectBoolean()
            throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException, IOException, ParseException, CellParserException {

        Boolean expected = Boolean.TRUE;
        Cell cell = row.getCell(13);
        CellParser<Boolean> cellParser = new CellParser<>();
        Boolean actual = cellParser.parse(cell, Boolean.class);
        Assert.assertEquals(expected, actual);
    }

    @Test
    @PositiveTest
    public void parseCorrectDouble()
            throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException, IOException, ParseException, CellParserException {

        Double expected = 23.41;
        Cell cell = row.getCell(14);
        CellParser<Double> cellParser = new CellParser<>();
        Double actual = cellParser.parse(cell, Double.class);
        Assert.assertEquals(expected, actual);
    }

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    @NegativeTest
    public void cellParserNullCell()
            throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException, IOException, ParseException, CellParserException {

        expectedException.expect(CellParserException.class);
        expectedException.expectMessage("Cell cannot be null");
        CellParser<Double> cellParser = new CellParser<>();
        cellParser.parse(null, Double.class);
    }

    @Test
    @NegativeTest
    public void cellParserNullType()
            throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException, IOException, ParseException, CellParserException {

        expectedException.expect(CellParserException.class);
        expectedException.expectMessage("Type cannot be null");
        Cell cell = row.getCell(14);
        CellParser<Double> cellParser = new CellParser<>();
        Class<Double> clazz = null;
        cellParser.parse(cell, clazz);
    }

    @Test
    @NegativeTest
    public void cellParserInvalidType()
            throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException, IOException, ParseException, CellParserException {

        expectedException.expect(CellParserException.class);
        expectedException.expectMessage("Unable to parse the type 'class java.lang.Exception'");
        Cell cell = row.getCell(14);
        CellParser<Exception> cellParser = new CellParser<>();
        Class<Exception> clazz = Exception.class;
        cellParser.parse(cell, clazz);
    }
}
