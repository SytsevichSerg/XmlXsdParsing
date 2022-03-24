
package com.epam.task2.entity;

import java.sql.Time;
import java.time.YearMonth;


public class ExpressDeposit extends Deposit{
            
    public ExpressDeposit(String bankName, String country, String depositor, String accountId, double amount, float profitability, YearMonth timeConstraints) {
        super(bankName, country, depositor, accountId, amount, profitability, timeConstraints);
    }
    
}
