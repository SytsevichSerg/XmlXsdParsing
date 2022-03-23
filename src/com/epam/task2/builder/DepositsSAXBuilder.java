
package com.epam.task2.builder;

import com.epam.task2.entity.Deposit;
import com.epam.task2.handler.DepositHandler;
import java.io.IOException;
import java.util.Set;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

public class DepositsSAXBuilder extends AbstractDepositBuilder{
    
    private DepositHandler dh;
    private XMLReader reader;
    
    public DepositsSAXBuilder(){
        
        super();
        dh = new DepositHandler();
        try {
            reader = XMLReaderFactory.createXMLReader();
            reader.setContentHandler(dh);
            
        } catch (SAXException e) {
            System.err.print("SAX parser error: " + e);
        }
    }
    

    @Override
    public void buildSetDeposits(String filename) {
        try {
            reader.parse(filename);
        } catch (IOException | SAXException e) {
            System.err.print("SAX parser or IO error" + e);
        } 
        deposits = dh.getDeposits();
    }
    
}
