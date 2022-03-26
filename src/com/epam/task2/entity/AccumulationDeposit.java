
package com.epam.task2.entity;

import java.sql.Time;
import java.time.YearMonth;


public class AccumulationDeposit extends Deposit{
    
    public AccumulationDeposit() {
        
    }
    
    public AccumulationDeposit(Bank bank, Country country, String depositor, String accountId, double amount, float profitability, YearMonth timeConstraints) {
        super(bank, country, depositor, accountId, amount, profitability, timeConstraints);
    }
    
}
