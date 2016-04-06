package com.udaykale.spreadsheet.parser;

import com.udaykale.spreadsheet.SpringTest;
import com.udaykale.spreadsheet.custom.NegativeTest;
import com.udaykale.spreadsheet.custom.PositiveTest;
import com.udaykale.spreadsheet.domain.deserializer.AverageCheckCellDeserializer;
import com.udaykale.spreadsheet.extension.CellDeserializerException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import javax.inject.Inject;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

/**
 * @author uday
 */
public class CellDeserializerTest extends SpringTest {

    @Inject
    private Row row;

    @Test
    @PositiveTest
    public void deserializeToCheckAverageString()
            throws IOException, InvocationTargetException, NoSuchMethodException, InstantiationException, CellParserException, IllegalAccessException, CellDeserializerException {

        Boolean expected = true;
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
        Cell cell = row.getCell(14);
        CellParser<Boolean> cellParser = new CellParser<>();
        AverageCheckCellDeserializer averageCheckCellDeserializer = null;
        cellParser.parse(cell, averageCheckCellDeserializer);
    }
}
