/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package builder;

import com.epam.task2.builder.AbstractDepositBuilder;
import com.epam.task2.builder.DepositBuilderFactory;
import com.epam.task2.builder.ParserType;
import com.epam.task2.entity.Deposit;
import com.epam.task2.exception.ParsingXMLException;
import java.util.Set;
import javax.xml.bind.JAXBException;
import static org.testng.Assert.*;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


public class DepositJaxbBuilderTest {
    
    public DepositJaxbBuilderTest() {
    }

    private Set<Deposit> expected;
    @Test
    public void TestDepositJaxbBuilder() throws JAXBException {
        AbstractDepositBuilder builder;
        Set<Deposit> actual = null;
        try {
            builder = DepositBuilderFactory.getInstance().createDepositBuilder(ParserType.JAXB);
            builder.buildSetDeposits("data/deposits.xml");
            actual = builder.getDeposits();
        } catch (ParsingXMLException e) {
            fail(e.getMessage());
        }
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
