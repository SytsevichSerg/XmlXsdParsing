
package com.epam.task2.entity;

import java.sql.Time;
import java.time.YearMonth;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "on-demand")
@XmlRootElement
public class OnDemandDeposit extends Deposit{
    
    public OnDemandDeposit(String bankName, String country, String depositor, String accountId, double amount, float profitability, YearMonth timeConstraints) {
        super(bankName, country, depositor, accountId, amount, profitability, timeConstraints);
    }
    

}
