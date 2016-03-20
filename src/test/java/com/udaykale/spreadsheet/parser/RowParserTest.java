package com.udaykale.spreadsheet.parser;

import com.udaykale.spreadsheet.custom.PositiveTest;
import com.udaykale.spreadsheet.domain.SchoolStudentInfoSheetHeader;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
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
    @PositiveTest
    public void parseCorrectStrings()
            throws IOException, InvalidFormatException, InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {

        // Registration#	Roll#	Grade	Class	Name
        // Gender	Father's Name	Mother's Name
        // Guardian's Name	Surname	DOB	Phone#	DOJ
        SchoolStudentInfoSheetHeader testSchoolStudentInfoSheetHeader = new SchoolStudentInfoSheetHeader();
        testSchoolStudentInfoSheetHeader.setRegNumber("Registration#");
        testSchoolStudentInfoSheetHeader.setRollNumber("Roll#");
        testSchoolStudentInfoSheetHeader.setGrade("Grade");
        testSchoolStudentInfoSheetHeader.setClassName("Class");
        testSchoolStudentInfoSheetHeader.setName("Name");
        testSchoolStudentInfoSheetHeader.setGender("Gender");
        testSchoolStudentInfoSheetHeader.setFathersName("Father's Name");
        testSchoolStudentInfoSheetHeader.setMothersName("Mother's Name");
        testSchoolStudentInfoSheetHeader.setGuardiansName("Guardian's Name");
        testSchoolStudentInfoSheetHeader.setSurname("Surname");
        testSchoolStudentInfoSheetHeader.setDob("DOB");
        testSchoolStudentInfoSheetHeader.setPhoneNumber("Phone#");
        testSchoolStudentInfoSheetHeader.setDoj("DOJ");
        ClassLoader classLoader = getClass().getClassLoader();
        String filePath = classLoader.getResource("School_Student_Information.xlsx").getPath();
        Workbook workbook = new XSSFWorkbook(filePath);
        Sheet sheet = workbook.getSheet("info");
        Row row = sheet.getRow(0);
        RowParser<SchoolStudentInfoSheetHeader> rowParser = new RowParser<>();
        SchoolStudentInfoSheetHeader schoolStudentInfoSheetHeader =
                rowParser.parse(row, SchoolStudentInfoSheetHeader.class,
                        RowParser.cellPositionAndTypeMap(SchoolStudentInfoSheetHeader.class));
        workbook.close();
        Assert.assertTrue(testSchoolStudentInfoSheetHeader.equals(schoolStudentInfoSheetHeader));
    }
}
