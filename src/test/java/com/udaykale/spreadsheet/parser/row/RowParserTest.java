package com.udaykale.spreadsheet.parser.row;

import com.udaykale.spreadsheet.domain.rows.StudentInfoSheetHeader;
import com.udaykale.spreadsheet.extension.CellDeserializerException;
import com.udaykale.spreadsheet.parser.CellParserException;
import com.udaykale.spreadsheet.parser.RowParser;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

/**
 * @author uday
 */
public class RowParserTest {

    @Test
    public void rowParserTest()
            throws IOException, NoSuchMethodException, InstantiationException, CellParserException, CellDeserializerException, IllegalAccessException, InvocationTargetException {

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
}
