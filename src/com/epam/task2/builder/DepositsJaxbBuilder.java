
package com.epam.task2.builder;

import com.epam.task2.entity.Deposits;
import com.epam.task2.exception.ParsingXMLException;
import com.epam.task2.util.ResourcePathUtil;
import com.epam.task2.validator.XmlFileValidator;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class DepositsJaxbBuilder extends AbstractDepositBuilder{
    private static final Logger LOG = LogManager.getLogger();
    private final Unmarshaller unmarshaller;
    
    public DepositsJaxbBuilder() throws ParsingXMLException, JAXBException {
        try {
            JAXBContext context = JAXBContext.newInstance(Deposits.class);
            unmarshaller = context.createUnmarshaller();
        } catch (JAXBException jaxbException) {
            LOG.error("DepositsJaxbBuilder is not created. ", jaxbException);
            throw new ParsingXMLException(jaxbException);
        }
    }
    
    @Override
    public void buildSetDeposits(String fileName) throws ParsingXMLException {
        String schemaFileName = ResourcePathUtil.getResourcePath(AbstractDepositBuilder.SCHEMA_RESOURCE_NAME);
        XmlFileValidator validator = XmlFileValidator.getInstance();
        if (validator.isCorrect(fileName, schemaFileName)) {
            try {
                InputStream fileInputStream = new FileInputStream(fileName);
                Deposits depositsObject = (Deposits) unmarshaller.unmarshal(fileInputStream);
                deposits = depositsObject.getDeposits();
                LOG.info("Set of deposits is built. " + deposits);
            } catch (FileNotFoundException | JAXBException exception) {
                LOG.error("Exception when build Set of deposits", exception);
                throw new ParsingXMLException(exception);
            }
        } else {
            LOG.info("File '" + fileName + "' does not match schema '" + schemaFileName + "'");
            throw new ParsingXMLException("File '" + fileName + "' does not match schema '" + schemaFileName + "'");
        }
    }
    
}
