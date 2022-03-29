
package com.epam.task2.builder;
import com.epam.task2.exception.ParsingXMLException;
import javax.xml.bind.JAXBException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class DepositBuilderFactory {
    private static final Logger LOG = LogManager.getLogger();
    private static final DepositBuilderFactory instance = new DepositBuilderFactory();
    
     private DepositBuilderFactory() {

    }

    public static DepositBuilderFactory getInstance() {
        return instance;
    }

    public AbstractDepositBuilder createDepositBuilder(ParserType type) throws ParsingXMLException, JAXBException {
        switch (type) {
            case SAX:  
                return new DepositsSAXBuilder();
       
            case DOM : 
                return new DepositsDOMBuilder();
            
            case STAX :
                return new DepositsSTAXBuilder();
                
            case JAXB:
                return new DepositsJaxbBuilder();
            
            default : {
                LOG.info("Constant is not present in enum" + type + "i build default SAX builder ");
                return new DepositsSAXBuilder();
            }

        }
    }
    
}
