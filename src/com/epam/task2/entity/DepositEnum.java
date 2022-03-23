
package com.epam.task2.entity;


public enum DepositEnum {
    BANK("bank-name"),
    COUNTRY("country"),
    DEPOSITOR("depositor"),
    ACCOUNT("account-id"),
    AMOUNT("amount-on-deposit"),
    PROFITABILITY("profitability"),
    TIME("time-constraints");
    private String value;
    
    private DepositEnum(String value) {
        this.value = value;
    }
    
    public String getValue() {
        return value;
    }
}
