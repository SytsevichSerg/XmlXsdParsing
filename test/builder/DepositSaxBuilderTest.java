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
import org.testng.annotations.Test;

import static org.testng.AssertJUnit.assertEquals;
import static org.testng.FileAssert.fail;


public class DepositSaxBuilderTest {
    private Set<Deposit> expected;
    @Test
    public void TestDepositDomBuilder() throws JAXBException {
        AbstractDepositBuilder builder;
        Set<Deposit> actual = null;
        try {
            builder = DepositBuilderFactory.getInstance().createDepositBuilder(ParserType.SAX);
            builder.buildSetDeposits("data/deposits.xml");
            actual = builder.getDeposits();
        } catch (ParsingXMLException e) {
            fail(e.getMessage());
        }
        assertEquals(expected, actual);
    }
}
