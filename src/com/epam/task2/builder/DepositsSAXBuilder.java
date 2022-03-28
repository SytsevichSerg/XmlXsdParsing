
package com.epam.task2.builder;

import com.epam.task2.handler.DepositHandler;
import java.io.IOException;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import java.net.URL;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.parsers.SAXParser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class DepositsSAXBuilder extends AbstractDepositBuilder{
    
    private static final Logger LOG = LogManager.getLogger();
    
    public DepositsSAXBuilder(){
        
        super();
    }

    @Override
    public void buildSetDeposits(String path) {
        try {
            ClassLoader loader = getClass().getClassLoader();
            URL resource = loader.getResource(path);

            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();
            XMLReader reader = saxParser.getXMLReader();
            DepositHandler handler = new DepositHandler();

            reader.setContentHandler(handler);
            reader.parse(resource.getFile());

            deposits.addAll(handler.getDeposits());
        } catch (ParserConfigurationException | SAXException | IOException e) {
            LOG.error("sax error", e);
        }
    }
}
