
package com.epam.task2.handler;

import com.epam.task2.entity.*;

import java.util.EnumSet;
import java.util.HashSet;
import java.util.List;
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
    private static final String ELEMENT_METALS = DepositXmlTag.METALS.getName();
    private static final String ELEMENT_METAL = DepositXmlTag.METAL.getName();
    
    private Set<Deposit> deposits;
    private Deposit currentDeposit;
    //private DepositEnum currentEnum;
    
    private OnDemandDeposit currentOnDemandDeposit;
    private AccumulationDeposit currentAccumulationDeposit;
    private CheckinDeposit currentCheckinDeposit;
    private ExpressDeposit currentExpressDeposit;
    private SavingDeposit currentSavingDeposit;
    private MetalDeposit currentMetalDeposit;
    
    private DepositXmlTag currentXmlTag;
    private EnumSet<DepositXmlTag> withText;
    private List<Metal> currentMetals;
    
    public DepositHandler() {
        deposits = new HashSet<Deposit>();
        withText = EnumSet.range(DepositXmlTag.BANK_NAME, DepositXmlTag.METAL_PURCHASE_RATE);
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
    public void endElement(String uri, String localName, String qName) {
        if (ELEMENT_ON_DEMAND_DEPOSIT.equals(qName)) {
            deposits.add(currentOnDemandDeposit);
        } else if (ELEMENT_ACCUMULATION_DEPOSIT.equals(qName)) {
            deposits.add(currentAccumulationDeposit);
        } else if (ELEMENT_CHECKIN_DEPOSIT.equals(qName)) {
            deposits.add(currentCheckinDeposit);
        } else if (ELEMENT_EXPRESS_DEPOSIT.equals(qName)) {
            deposits.add(currentExpressDeposit);
        } else if (ELEMENT_SAVING_DEPOSIT.equals(qName)) {
            deposits.add(currentSavingDeposit);
        } else if (ELEMENT_METAL_DEPOSIT.equals(qName)) {
            deposits.add(currentMetalDeposit);
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
                case ACCOUNT_ID : currentDeposit.setAccountId(data);
                break;
                case AMOUNT_ON_DEPOSIT -> currentDeposit.setAmount(Double.parseDouble(data));
                case PROFITABILITY -> currentDeposit.setProfitability(Float.parseFloat(data));
                case TIME -> currentDeposit.setTimeConstraints(YearMonth.parse(data));
                /*
                case CHOCOLATE_TYPE -> {
                    currentChocolateCandy = (ChocolateCandy) currentCandy;
                    currentChocolateCandy.setChocolateType(ChocolateType.getChocolateType(data));
                    currentCandy = currentChocolateCandy;//todo лишняя строка?
                }
                case FLAVOR -> {
                    currentCaramelCandy = (CaramelCandy) currentCandy;
                    currentCaramelCandy.setFlavor(data);
                    currentCandy = currentCaramelCandy;//todo лишняя строка?
                }
                case LOLLIPOP -> {
                    currentCaramelCandy = (CaramelCandy) currentCandy;
                    currentCaramelCandy.setLollipop(Boolean.parseBoolean(data));
                    currentCandy = currentCaramelCandy;//todo лишняя строка?
                }
                case INGREDIENT_NAME -> currentIngredient.setName(data);
                case WEIGHT -> currentIngredient.setWeight(Integer.parseInt(data));
                case CARBOHYDRATES -> {
                    Value value = currentCandy.getValue();
                    value.setCarbohydrates(Integer.parseInt(data));
                    currentCandy.setValue(value);
                }
                case FATS -> {
                    Value value = currentCandy.getValue();
                    value.setFats(Integer.parseInt(data));
                    currentCandy.setValue(value);
                }
                case PROTEINS -> {
                    Value value = currentCandy.getValue();
                    value.setProteins(Integer.parseInt(data));
                    currentCandy.setValue(value);
                }
                */
                case ENERGY -> currentCandy.setEnergy(Integer.parseInt(data));
                case PRODUCTION -> currentCandy.setProduction(Production.getProduction(data));
                case EXPIRATION_DATE -> currentCandy.setExpirationDate(YearMonth.parse(data));
                default -> throw new EnumConstantNotPresentException(
                        currentXmlTag.getDeclaringClass(), currentXmlTag.name());//fixme wrap exception
            }
        }
        currentXmlTag = null;
    }

}
