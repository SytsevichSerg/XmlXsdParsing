
package com.epam.task2.entity;

import java.sql.Time;
import java.time.YearMonth;

public class SavingDeposit extends Deposit{
    
    public SavingDeposit(String bankName, String country, String depositor, String accountId, double amount, float profitability, YearMonth timeConstraints) {
        super(bankName, country, depositor, accountId, amount, profitability, timeConstraints);
    }
    
}
