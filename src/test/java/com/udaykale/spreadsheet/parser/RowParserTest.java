package com.udaykale.spreadsheet.parser;

import com.udaykale.spreadsheet.custom.NegativeTest;
import com.udaykale.spreadsheet.custom.PositiveTest;
import com.udaykale.spreadsheet.domain.rows.InvalidRow;
import com.udaykale.spreadsheet.domain.rows.StudentInfoSheetHeader;
import com.udaykale.spreadsheet.extension.CellDeserializerException;
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
public class RowParserTest {

    @Test
    @PositiveTest
    public void rowParserTest()
            throws IOException, NoSuchMethodException, InstantiationException, CellParserException, CellDeserializerException, IllegalAccessException, InvocationTargetException, RowParserException {

        StudentInfoSheetHeader expected = new StudentInfoSheetHeader();
        expected.setRegNumber("Registration#");
        expected.setRollNumber("Roll#");
        expected.setGrade("Grade");
        expected.setClassName("Class");
        expected.setName("Name");
        expected.setGender("Gender");
        expected.setFathersName("Father's Name");
        expected.setMothersName("Mother's Name");
        expected.setGuardiansName("Guardian's Name");
        expected.setSurname("Surname");
        expected.setDob("DOB");
        expected.setPhoneNumber("Phone#");
        expected.setDoj("DOJ");
        expected.setActive("Active");
        expected.setAverage("Average");

        ClassLoader classLoader = getClass().getClassLoader();
        String filePath = classLoader.getResource("School_Student_Information.xlsx").getPath();
        Workbook workbook = new XSSFWorkbook(filePath);
        Sheet sheet = workbook.getSheet("info");
        Row row = sheet.getRow(0);

        RowParser<StudentInfoSheetHeader> rowParser = new RowParser<>();
        StudentInfoSheetHeader actual = rowParser.parse(row, StudentInfoSheetHeader.class);
        workbook.close();
        Assert.assertTrue(expected.equals(actual));
    }

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    @NegativeTest
    public void rowParserWithNullRow()
            throws NoSuchMethodException, RowParserException, InstantiationException, CellParserException, CellDeserializerException, IllegalAccessException, InvocationTargetException {

        expectedException.expect(RowParserException.class);
        expectedException.expectMessage("Row cannot be null");
        RowParser<StudentInfoSheetHeader> rowParser = new RowParser<>();
        rowParser.parse(null, StudentInfoSheetHeader.class);
    }

    @Test
    @NegativeTest
    public void rowParserWithNullRowType()
            throws NoSuchMethodException, RowParserException, InstantiationException, CellParserException, CellDeserializerException, IllegalAccessException, InvocationTargetException, IOException {

        expectedException.expect(RowParserException.class);
        expectedException.expectMessage("Row Type 'tClass' cannot be null");
        ClassLoader classLoader = getClass().getClassLoader();
        String filePath = classLoader.getResource("School_Student_Information.xlsx").getPath();
        Workbook workbook = new XSSFWorkbook(filePath);
        Sheet sheet = workbook.getSheet("info");
        Row row = sheet.getRow(0);
        RowParser<StudentInfoSheetHeader> rowParser = new RowParser<>();
        workbook.close();
        rowParser.parse(row, null);
    }

    @Test
    @NegativeTest
    public void rowParserWithInvalidCell()
            throws NoSuchMethodException, RowParserException, InstantiationException, CellParserException, CellDeserializerException, IllegalAccessException, InvocationTargetException, IOException {

        expectedException.expect(RowParserException.class);
        expectedException.expectMessage("Field of type class java.lang.Exception cannot be parsed");
        ClassLoader classLoader = getClass().getClassLoader();
        String filePath = classLoader.getResource("School_Student_Information.xlsx").getPath();
        Workbook workbook = new XSSFWorkbook(filePath);
        Sheet sheet = workbook.getSheet("info");
        Row row = sheet.getRow(0);
        RowParser<InvalidRow> rowParser = new RowParser<>();
        workbook.close();
        rowParser.parse(row, InvalidRow.class);
    }
}
