/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package validator;

import com.epam.task2.builder.AbstractDepositBuilder;
import com.epam.task2.exception.ParsingXMLException;
import com.epam.task2.util.ResourcePathUtil;
import com.epam.task2.validator.XmlFileValidator;
import static org.testng.Assert.*;
import static org.testng.AssertJUnit.assertEquals;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 *
 * @author Lena
 */
public class DepositValidatorTest {
    
    public DepositValidatorTest() {
    }

    @Test
    public void testIsValidXML() throws ParsingXMLException {
        boolean expected = false;
        String schemaFileName = ResourcePathUtil.getResourcePath(AbstractDepositBuilder.SCHEMA_RESOURCE_NAME);
        XmlFileValidator validator = XmlFileValidator.getInstance();
        boolean actual = validator.isCorrect("data/deposits.xml", schemaFileName);
        assertEquals(expected, actual);
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @BeforeMethod
    public void setUpMethod() throws Exception {
    }

    @AfterMethod
    public void tearDownMethod() throws Exception {
    }
}
