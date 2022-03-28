
package com.epam.task2.handler;


public enum DepositXmlTag {
    DEPOSITS("deposits"),
    ON_DEMAND_DEPOSIT("on-demand-deposit"),
    EXPRESS_DEPOSIT("express-deposit"),
    CHECKIN_DEPOSIT("checkin-deposit"),
    ACCUMULATION_DEPOSIT("accumulation-deposit"),
    SAVING_DEPOSIT("saving-deposit"),
    METAL_DEPOSIT("metal-deposit"),
    METALS("metals"),
    METAL("metal"),
    BANK_NAME("bank-name"),
    COUNTRY("country"),
    DEPOSITOR("depositor"),
    AMOUNT_ON_DEPOSIT("amount-on-deposit"),
    PROFITABILITY("profitability"),
    TIME_CONSTRAINTS("time-constraints"),
    IRREDUCIBLE_BALANCE("irreducible-balance"),
    DATE_UNTIL_REPLENISHMENT_ALLOWED("date-until-replenishment-allowed"),
    METAL_NOMENCLATURE("metal-nomenclature"),
    WEIGHT("weight"),
    METAL_PURCHASE_RATE("metal-purchase-rate");
    private String name;

    DepositXmlTag(String name) {
        this.name = name; 
    }

    public static DepositXmlTag getDepositXmlTag(String name){
        for(DepositXmlTag tag : DepositXmlTag.values()){
            if(name.equals(tag.getName())){
                return tag;
            }
        }
        return null;//fixme throw exc
    }

    public String getName() {
        return name;
    }
    
}
