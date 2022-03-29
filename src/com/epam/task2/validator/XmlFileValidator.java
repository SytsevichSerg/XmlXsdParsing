
package com.epam.task2.validator;

import com.epam.task2.exception.ParsingXMLException;
import java.io.File;
import java.io.IOException;
import javax.xml.XMLConstants;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.SAXException;


public class XmlFileValidator {
    private static final Logger LOG = LogManager.getLogger();
    private static XmlFileValidator instance;

    private XmlFileValidator() {
    }

    public static XmlFileValidator getInstance() {
        if (instance == null) {
            instance = new XmlFileValidator();
        }
        return instance;
    }

    public boolean isCorrect(String xmlFileName, String schemaFileName) throws ParsingXMLException {
        String language = XMLConstants.W3C_XML_SCHEMA_NS_URI;
        try {
            SchemaFactory factory = SchemaFactory.newInstance(language);
            File schemaLocation = new File(schemaFileName);
            Schema schema = factory.newSchema(schemaLocation);
            Validator validator = schema.newValidator();
            Source source = new StreamSource(xmlFileName);
            validator.validate(source);
            LOG.info("File '" + xmlFileName + "' matches schema '" + schemaFileName + "'");
            return true;
        } catch (SAXException saxException) {
            LOG.info(saxException.getMessage());
            return false;
        } catch (IOException ioException) {
            LOG.error("Exception when validate file. File:'" + xmlFileName + "' Schema:'" + schemaFileName + "'");
            throw new ParsingXMLException(ioException);
        }
    }

}