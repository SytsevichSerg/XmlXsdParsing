
package com.epam.task2.entity;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "on-demand")
@XmlRootElement
public class OnDemandDeposit extends Deposit{
    
    public OnDemandDeposit(){
        
    }
    
    public OnDemandDeposit(Bank bank, Country country, String depositor, String accountId, double amount, float profitability, boolean depositCallable, boolean withdrawalCallable) {
        super(bank, country, depositor, accountId, amount, profitability, depositCallable, withdrawalCallable);
    }
    
    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }

    @Override
    public int hashCode() {
       return super.hashCode();
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() +
                "{" + super.toString() + '\'' +
                '}';
    }

}
