
package com.epam.task2.handler;


public enum DepositXmlTag {
    DEPOSITS("deposits"),
    ON_DEMAND_DEPOSIT("on-demand-deposit"),
    EXPRESS_DEPOSIT("express-deposit"),
    CHECKIN_DEPOSIT("checkin-deposit"),
    ACCUMULATION_DEPOSIT("accumulation-deposit"),
    SAVING_DEPOSIT("saving-deposit"),
    METAL_DEPOSIT("metal-deposit"),
    BANK_NAME("bank-name"),
    COUNTRY("country"),
    DEPOSITOR("depositor"),
    METAL("metal"),
    WEIGHT("weight"),
    METAL_PURCHASE_RATE("metal-purchase-rate"),
    METAL_SALES_RATE("metal-sales-rate");
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
