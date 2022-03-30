
package builder;

import com.epam.task2.builder.AbstractDepositBuilder;
import com.epam.task2.builder.DepositBuilderFactory;
import com.epam.task2.builder.ParserType;
import com.epam.task2.entity.Deposit;
import com.epam.task2.exception.ParsingXMLException;
import java.util.Set;
import javax.xml.bind.JAXBException;
import org.testng.annotations.Test;
import static org.testng.Assert.fail;
import static org.testng.AssertJUnit.assertEquals;


public class DepositDomBuilderTest {
    private Set<Deposit> expected;
    @Test
    public void TestDepositDomBuilder() throws JAXBException {
        AbstractDepositBuilder builder;
        Set<Deposit> actual = null;
        try {
            builder = DepositBuilderFactory.getInstance().createDepositBuilder(ParserType.DOM);
            builder.buildSetDeposits("data/deposits.xml");
            actual = builder.getDeposits();
        } catch (ParsingXMLException e) {
            fail(e.getMessage());
        }
        assertEquals(expected, actual);
    }
}
