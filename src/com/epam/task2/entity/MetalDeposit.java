
package com.epam.task2.entity;

import java.sql.Time;
import java.time.YearMonth;


public class MetalDeposit extends Deposit{
    
    public MetalDeposit(String bankName, String country, String depositor, String accountId, double amount, float profitability, YearMonth timeConstraints) {
        super(bankName, country, depositor, accountId, amount, profitability, timeConstraints);
    }
    
}
