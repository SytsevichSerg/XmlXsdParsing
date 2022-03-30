
package com.epam.task2.util;

import com.epam.task2.handler.DepositHandler;
import java.io.IOException;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;


public class XmlXsdParsing {


    public static void main(String[] args){
        
        try {
            DepositHandler handler = new DepositHandler();
            XMLReader reader = XMLReaderFactory.createXMLReader();
            reader.setContentHandler(handler);
            reader.parse("resources/deposits.xml");
        } catch (SAXException exception) {
            //
            System.err.print("Sax parser error" + exception);
        } catch (IOException exception) {
            System.err.print("I/O error" + exception);
        }
        
        
    }
    
}
