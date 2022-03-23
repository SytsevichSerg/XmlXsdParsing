/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epam.task2.handler;

import com.epam.task2.entity.Deposit;
import java.util.Set;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;


public class SimpleDepositHandler extends DefaultHandler{
    @Override 
    public void startDocument(){
        //start parsing
    }
    
    @Override 
    public void startElement(String uri, String localName, String qName, Attributes attrs){
        String s = localName;
        for (int i =0; i< attrs.getLength(); i++) {
            s+= " " + attrs.getLocalName(i) + "=" + attrs.getValue(i);
        }
        //
    }

    public Set<Deposit> getDeposits() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
