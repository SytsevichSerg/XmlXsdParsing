
package com.epam.task2.entity;

import java.sql.Time;


public class AccumulationDeposit extends Deposit{
    
    public AccumulationDeposit(String bankName, String country, String depositor, String accountId, double amount, float profitability, Time timeConstraints) {
        super(bankName, country, depositor, accountId, amount, profitability, timeConstraints);
    }
    
}
