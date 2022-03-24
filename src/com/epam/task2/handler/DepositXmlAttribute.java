
package com.epam.task2.handler;


public enum DepositXmlAttribute {
    ACCOUNT_ID("account-id"),
    AMOUNT_ON_DEPOSIT("amount-on-deposit"),
    PROFITABILITY("profitability");
    
    private String name;

    DepositXmlAttribute(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
