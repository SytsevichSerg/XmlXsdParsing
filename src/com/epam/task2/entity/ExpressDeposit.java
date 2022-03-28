
package com.epam.task2.entity;

import java.time.LocalDate;
import javax.xml.bind.annotation.XmlElement;


public class ExpressDeposit extends Deposit{
    
    private LocalDate timeConstraints;
    
    public ExpressDeposit(){
        
    }
    
    public ExpressDeposit(Bank bank, Country country, String depositor, String accountId, double amount, float profitability,  boolean depositCallable, boolean withdrawalCallable, LocalDate timeConstraints) {
        super(bank, country, depositor, accountId, amount, profitability, depositCallable, withdrawalCallable);
        
        this.timeConstraints = timeConstraints;
    }

    @XmlElement(name = "time-constraints")
    public LocalDate getTimeConstraints() {
        return timeConstraints;
    }

    public void setTimeConstraints(LocalDate timeConstraints) {
        this.timeConstraints = timeConstraints;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!super.equals(o)) {
            return false;
        }
        ExpressDeposit that = (ExpressDeposit) o;
        return timeConstraints.equals(that.timeConstraints);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = result * prime + timeConstraints.hashCode();

        return result;
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() +
                "{" + super.toString() +
                ", timeConstraints=" + timeConstraints.toString() + '\'' +
                '}';
    }
    
}
