
package com.epam.task2.builder;
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

    public DepositBuilderFactory createDepositBuilder(ParserType type) {
        switch (type) {
            case SAX -> {
                return new DepositsSAXBuilder();
            }

            case DOM -> {
                return new DepositsDOMBuilder();
            }

            case STAX -> {
                return new DepositsSTAXBuilder();
            }
            default -> {
                LOG.info("Constant is not present in enum" + type + "i build default SAX builder ");
                return new DepositsSAXBuilder();
            }

        }
    }
    
}
