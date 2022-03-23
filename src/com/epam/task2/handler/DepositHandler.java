
package com.epam.task2.handler;

import com.epam.task2.entity.Deposit;
import com.epam.task2.entity.DepositEnum;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;


public class DepositHandler extends DefaultHandler{
    private Set<Deposit> deposits;
    private Deposit current;
    private DepositEnum currentEnum;
    private EnumSet<DepositEnum> withText;
    
    public DepositHandler() {
        deposits = new HashSet<Deposit>();
        withText = EnumSet.range(DepositEnum.BANK, DepositEnum.TIME);
    }
    public Set<Deposit> getDeposits() {
        return deposits;
    }
    @Override 
    public void startElement(String uri, String localName, String qName, Attributes attrs) {
        
        if("deposit".equals(localName)) {
            current = new Deposit(); //fixit - where is what attribute--
            current.setBankName(attrs.getValue(0));
            if(attrs.getLength() == 2){
                current.setCountry(attrs.getValue(1));
            }
        } else {
            DepositEnum temp = DepositEnum.valueOf(localName.toUpperCase(Locale.GERMANY));
        }
    }
}
