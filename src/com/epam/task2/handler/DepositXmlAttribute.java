
package com.epam.task2.handler;


public enum DepositXmlAttribute {
    ACCOUNT_ID("account-id"),
    DEPOSIT_CALLABLE("deposit-callable"),
    WITHDRAWAL_CALLABLE("withdrawal-callable");
    
    private String name;

    DepositXmlAttribute(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
