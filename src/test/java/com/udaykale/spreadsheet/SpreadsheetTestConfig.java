package com.udaykale.spreadsheet;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.io.IOException;
import java.net.URL;

/**
 * @author uday
 */
@Profile("test")
@Configuration
public class SpreadsheetTestConfig {

    @Bean
    public Workbook getWorkbook() throws IOException {
        Class<?> clazz = getClass();
        ClassLoader classLoader = clazz.getClassLoader();
        if (classLoader == null) throw new NullPointerException();
        URL url = classLoader.getResource("School_Student_Information.xlsx");
        if (url == null) throw new NullPointerException();
        String filePath = url.getPath();
        if (filePath == null) throw new NullPointerException();
        return new XSSFWorkbook(filePath);
    }

    @Bean
    public Sheet getSheet() throws IOException {
        Workbook workbook = getWorkbook();
        return workbook.getSheet("info");
    }

    @Bean
    public Row getHeaderRow() throws IOException {
        Sheet sheet = getSheet();
        return sheet.getRow(1);
    }

//    @Bean
//    public Row getSecondRow() throws IOException {
//        Sheet sheet = getSheet();
//        return sheet.getRow(1);
//    }
}
