
package com.epam.task2.handler;

import com.epam.task2.entity.AccumulationDeposit;
import com.epam.task2.entity.CheckinDeposit;
import com.epam.task2.entity.Deposit;
import com.epam.task2.entity.DepositEnum;
import com.epam.task2.entity.ExpressDeposit;
import com.epam.task2.entity.MetalDeposit;
import com.epam.task2.entity.OnDemandDeposit;
import com.epam.task2.entity.SavingDeposit;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;


public class DepositHandler extends DefaultHandler{
    private static final String ELEMENT_ON_DEMAND_DEPOSIT = DepositXmlTag.ON_DEMAND_DEPOSIT.getName();
    private static final String ELEMENT_ACCUMULATION_DEPOSIT = DepositXmlTag.ACCUMULATION_DEPOSIT.getName();
    private static final String ELEMENT_CHECKIN_DEPOSIT = DepositXmlTag.CHECKIN_DEPOSIT.getName();
    private static final String ELEMENT_EXPRESS_DEPOSIT = DepositXmlTag.EXPRESS_DEPOSIT.getName();
    private static final String ELEMENT_SAVING_DEPOSIT = DepositXmlTag.SAVING_DEPOSIT.getName();
    private static final String ELEMENT_METAL_DEPOSIT = DepositXmlTag.METAL_DEPOSIT.getName();
    
    private Set<Deposit> deposits;
    private Deposit currentDeposit;
    private DepositEnum currentEnum;
    
    private OnDemandDeposit currentOnDemandDeposit;
    private AccumulationDeposit currentAccumulationDeposit;
    private CheckinDeposit currentCheckinDeposit;
    private ExpressDeposit currentExpressDeposit;
    private SavingDeposit currentSavingDeposit;
    private MetalDeposit currentMetalDeposit;
    
    private DepositXmlTag currentXmlTag;
    private EnumSet<DepositEnum> withText;
    
    public DepositHandler() {
        deposits = new HashSet<Deposit>();
        withText = EnumSet.range(DepositEnum.BANK, DepositEnum.TIME);
    }
    public Set<Deposit> getDeposits() {
        return deposits;
    }
    public void startElement(String uri, String localName, String qName, Attributes attrs) {
        
        if(ELEMENT_ON_DEMAND_DEPOSIT.equals(qName)){
            currentOnDemandDeposit = new OnDemandDeposit();
            currentDeposit = currentOnDemandDeposit;//fixme warning
        } else if (ELEMENT_ACCUMULATION_DEPOSIT.equals(qName)) {
            currentAccumulationDeposit = new AccumulationDeposit();
            currentDeposit = currentAccumulationDeposit;//fixme warning
        } else if (ELEMENT_CHECKIN_DEPOSIT.equals(qName)) {
            currentCheckinDeposit = new CheckinDeposit();
            currentDeposit = currentCheckinDeposit;//fixme warning
        } else if (ELEMENT_EXPRESS_DEPOSIT.equals(qName)) {
            currentExpressDeposit = new ExpressDeposit();
            currentDeposit = currentExpressDeposit;//fixme warning
        } else if (ELEMENT_SAVING_DEPOSIT.equals(qName)) {
            currentSavingDeposit = new SavingDeposit();
            currentDeposit = currentSavingDeposit;//fixme warning
        }  else if (ELEMENT_METAL_DEPOSIT.equals(qName)) {
            currentMetalDeposit = new MetalDeposit();
            currentDeposit = currentMetalDeposit;//fixme warning
        } else {
            DepositXmlTag temp = DepositXmlTag.getDepositXmlTag(qName);
            if (withText.contains(temp)) {
                currentXmlTag = temp;
            }
        }
    }
}
