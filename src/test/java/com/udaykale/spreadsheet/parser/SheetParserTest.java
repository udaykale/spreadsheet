package com.udaykale.spreadsheet.parser;

import com.udaykale.spreadsheet.custom.PositiveTest;
import com.udaykale.spreadsheet.domain.sheets.StudentInformationSheet;
import com.udaykale.spreadsheet.extension.CellDeserializerException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

/**
 * @author uday
 */
public class SheetParserTest {

    @Test
    @PositiveTest
    public void sheetParserTest()
            throws IOException, IllegalAccessException, InvocationTargetException, RowParserException, InstantiationException, CellParserException, SheetParserException, CellDeserializerException, NoSuchMethodException {

        ClassLoader classLoader = getClass().getClassLoader();
        String filePath = classLoader.getResource("School_Student_Information.xlsx").getPath();
        Workbook workbook = new XSSFWorkbook(filePath);
        Sheet sheet = workbook.getSheet("info");
        SheetParser<StudentInformationSheet> sheetParser = new SheetParser<>();
        sheetParser.parse(sheet, StudentInformationSheet.class);
    }
}
