
package com.epam.task2.builder;

import com.epam.task2.entity.Deposit;
import com.epam.task2.handler.DepositHandler;
import java.io.IOException;
import java.util.Set;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;
import java.net.URL;
import javax.xml.parsers.ParserConfigurationException;

import javax.xml.parsers.SAXParserFactory;
import javax.xml.parsers.SAXParser;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class DepositsSAXBuilder extends AbstractDepositBuilder{
    
    private static final Logger LOG = LogManager.getLogger();
    private DepositHandler dh;
    private XMLReader reader;
    private ClassLoader loader;
    
    public DepositsSAXBuilder(){
        
        super();
        dh = new DepositHandler();
        try {
            loader = getClass().getClassLoader();
            reader = XMLReaderFactory.createXMLReader();
            reader.setContentHandler(dh);
            
        } catch (SAXException e) {
            LOG.error("SAX parser error: ", e);
        }
    }

    @Override
    public void buildSetDeposits(String path) {
        try {
            
            URL resource = loader.getResource(path);
            reader.parse(resource.getFile());

            deposits.addAll(dh.getDeposits());
        } catch ( SAXException | IOException e) {
            LOG.error("SAX parser or IO error", e);
        }
    }
}
