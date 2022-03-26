
package com.epam.task2.entity;

import java.sql.Time;
import java.time.YearMonth;


public class MetalDeposit extends Deposit{
    
    public MetalDeposit() {
        
    }
    
    public MetalDeposit(Bank bank, Country country, String depositor, String accountId, double amount, float profitability, YearMonth timeConstraints) {
        super(bank, country, depositor, accountId, amount, profitability, timeConstraints);
    }
    
}
