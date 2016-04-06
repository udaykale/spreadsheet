package com.udaykale.spreadsheet;

import org.junit.runner.RunWith;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author uday
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SpreadsheetTestConfig.class)
@ActiveProfiles("test")
public abstract class SpringTest {
}
