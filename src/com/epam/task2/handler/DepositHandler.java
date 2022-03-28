
package com.epam.task2.handler;

import com.epam.task2.entity.*;
import com.epam.task2.exception.ParsingXMLException;
import java.time.LocalDate;
import java.util.ArrayList;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;


public class DepositHandler extends DefaultHandler{
    private static final Logger LOG = LogManager.getLogger();
    private static final String ELEMENT_ON_DEMAND_DEPOSIT = DepositXmlTag.ON_DEMAND_DEPOSIT.getName();
    private static final String ELEMENT_ACCUMULATION_DEPOSIT = DepositXmlTag.ACCUMULATION_DEPOSIT.getName();
    private static final String ELEMENT_CHECKIN_DEPOSIT = DepositXmlTag.CHECKIN_DEPOSIT.getName();
    private static final String ELEMENT_EXPRESS_DEPOSIT = DepositXmlTag.EXPRESS_DEPOSIT.getName();
    private static final String ELEMENT_SAVING_DEPOSIT = DepositXmlTag.SAVING_DEPOSIT.getName();
    private static final String ELEMENT_METAL_DEPOSIT = DepositXmlTag.METAL_DEPOSIT.getName();
    private static final String ELEMENT_METALS = DepositXmlTag.METALS.getName();
    private static final String ELEMENT_METAL = DepositXmlTag.METAL.getName();
    
    private Set<Deposit> deposits;
    private Deposit currentDeposit;
    //private DepositEnum currentEnum;
    
    //private OnDemandDeposit currentOnDemandDeposit;
    //private AccumulationDeposit currentAccumulationDeposit;
    //private CheckinDeposit currentCheckinDeposit;
    //private ExpressDeposit currentExpressDeposit;
    //private SavingDeposit currentSavingDeposit;
    //private MetalDeposit currentMetalDeposit;
    
    private DepositXmlTag currentXmlTag;
    private EnumSet<DepositXmlTag> withText;
    private List<Metal> currentMetals;
    private Metal currentMetal;
    
    public DepositHandler() {
        deposits = new HashSet<Deposit>();
        withText = EnumSet.range(DepositXmlTag.BANK_NAME, DepositXmlTag.METAL_PURCHASE_RATE);
    }
    public Set<Deposit> getDeposits() {
        return deposits;
    }
    public void startElement(String uri, String localName, String qName, Attributes attrs) {
     
        String id =null;
        boolean depositCallable = true; 
        boolean withdrawalCallable  = true;
        
        for(int i = 0; i< attrs.getLength(); i++)
        {
            switch (i) {
                case 0: id = attrs.getValue(0);
                    break;
                case 1: depositCallable = attrs.getValue(1).equals("true");
                    break;
                case 2: withdrawalCallable = attrs.getValue(2).equals("true");
                    break;
            }
        }
        
        if(ELEMENT_ON_DEMAND_DEPOSIT.equals(qName)){
            currentDeposit = new OnDemandDeposit(id, depositCallable, withdrawalCallable);
        } else if (ELEMENT_ACCUMULATION_DEPOSIT.equals(qName)) {
            currentDeposit = new AccumulationDeposit(id, depositCallable, withdrawalCallable);
        } else if (ELEMENT_CHECKIN_DEPOSIT.equals(qName)) {
            currentDeposit = new CheckinDeposit(id, depositCallable, withdrawalCallable);
        } else if (ELEMENT_EXPRESS_DEPOSIT.equals(qName)) {
            currentDeposit = new ExpressDeposit(id, depositCallable, withdrawalCallable);
        } else if (ELEMENT_SAVING_DEPOSIT.equals(qName)) {
            currentDeposit = new SavingDeposit(id, depositCallable, withdrawalCallable);
        }  else if (ELEMENT_METAL_DEPOSIT.equals(qName)) {
            currentDeposit = new MetalDeposit(id, depositCallable, withdrawalCallable);
        }  else if (ELEMENT_METALS.equals(qName)) {
            currentMetals = new ArrayList<>();
        } else if (ELEMENT_METAL.equals(qName)) {
            currentMetal = new Metal();
        } else {
            DepositXmlTag temp = DepositXmlTag.getDepositXmlTag(qName);
            if (withText.contains(temp)) {
                currentXmlTag = temp;
            }
        }
    }
    
    public void endElement(String uri, String localName, String qName) {
        if (ELEMENT_ON_DEMAND_DEPOSIT.equals(qName) ||
            ELEMENT_ACCUMULATION_DEPOSIT.equals(qName) ||
            ELEMENT_CHECKIN_DEPOSIT.equals(qName) || 
            ELEMENT_EXPRESS_DEPOSIT.equals(qName) ||
            ELEMENT_SAVING_DEPOSIT.equals(qName) ||
            ELEMENT_METAL_DEPOSIT.equals(qName)) {
                deposits.add(currentDeposit);
        }else if (ELEMENT_METAL.equals(qName)) {
            currentMetals.add(currentMetal);
        } else if (ELEMENT_METALS.equals(qName)) {
            MetalDeposit deposit = (MetalDeposit) currentDeposit;
            deposit.setMetals(currentMetals);
        }
    }
     
    public void characters(char[] ch, int start, int length) {
        String data = new String(ch, start, length).trim();
        if (currentXmlTag != null) {
            switch (currentXmlTag) {
                case BANK_NAME : currentDeposit.setBank(Bank.getBank(data));
                break;
                case COUNTRY : currentDeposit.setCountry(Country.getCounty(data));
                break;
                case DEPOSITOR : currentDeposit.setDepositor(data);
                break;
                case AMOUNT_ON_DEPOSIT: currentDeposit.setAmount(Double.parseDouble(data));
                break;
                case PROFITABILITY: currentDeposit.setProfitability(Float.parseFloat(data));
                break;
                case TIME_CONSTRAINTS : {
                    if (currentDeposit instanceof ExpressDeposit) {
                        ExpressDeposit deposit = (ExpressDeposit) currentDeposit;
                        deposit.setTimeConstraints(LocalDate.parse(data));
                    }
                    if (currentDeposit instanceof CheckinDeposit) {
                        CheckinDeposit deposit = (CheckinDeposit) currentDeposit;
                        deposit.setTimeConstraints(LocalDate.parse(data));
                    }
                    if (currentDeposit instanceof AccumulationDeposit) {
                        AccumulationDeposit deposit = (AccumulationDeposit) currentDeposit;
                        deposit.setTimeConstraints(LocalDate.parse(data));
                    }
                    if (currentDeposit instanceof SavingDeposit) {
                        SavingDeposit deposit = (SavingDeposit) currentDeposit;
                        deposit.setTimeConstraints(LocalDate.parse(data));
                    }
                }break;
                case IRREDUCIBLE_BALANCE: 
                    if (currentDeposit instanceof CheckinDeposit) {
                        CheckinDeposit deposit = (CheckinDeposit) currentDeposit;
                        deposit.setIrreducibleBalance(Double.parseDouble(data));
                    }
                break;
                case DATE_UNTIL_REPLENISHMENT_ALLOWED: 
                    if (currentDeposit instanceof AccumulationDeposit) {
                        AccumulationDeposit deposit = (AccumulationDeposit) currentDeposit;
                        deposit.setDateUntilReplenishmentAllowed(LocalDate.parse(data));
                    }
                break;
                case METAL_NOMENCLATURE: 
                    try{
                        currentMetal.setNomencalture(MetalNomenclature.getMetalNomenclature(data));
                    } catch (ParsingXMLException exception) {
                        LOG.warn(exception);
                    }
                break;
                case WEIGHT: currentMetal.setWeight(Double.parseDouble(data));
                break;
                case METAL_PURCHASE_RATE: currentMetal.setRate(Double.parseDouble(data));
                break;

                default : throw new EnumConstantNotPresentException(
                        currentXmlTag.getDeclaringClass(), currentXmlTag.name());//fixme wrap exception
            }
        }
        currentXmlTag = null;
    }

}
