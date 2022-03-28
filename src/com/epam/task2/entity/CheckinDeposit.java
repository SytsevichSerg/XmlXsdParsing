
package com.epam.task2.entity;

import java.sql.Time;
import java.time.LocalDate;
import java.time.YearMonth;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "checking-type")
@XmlRootElement
public class CheckinDeposit extends Deposit{
    
    private LocalDate timeConstraints;
    private double irreducibleBalance;
    
    public CheckinDeposit() {
        
    }
    
    public CheckinDeposit(Bank bank, Country country, String depositor, String accountId, double amount, float profitability, boolean depositCallable, boolean withdrawalCallable, LocalDate timeConstraints, double irreducibleBalance) {
        super(bank, country, depositor, accountId, amount, profitability, depositCallable, withdrawalCallable);
        this.timeConstraints = timeConstraints;
        this.irreducibleBalance = irreducibleBalance;
    }

    @XmlElement(name = "time-constraints")
    public LocalDate getTimeConstraints() {
        return timeConstraints;
    }

    public void setTimeConstraints(LocalDate timeConstraints) {
        this.timeConstraints = timeConstraints;
    }
    
    @XmlElement(name = "irreducible-balance")
    public double getIrreducibleBalance() {
        return irreducibleBalance;
    }

    public void setIrreducibleBalance(double irreducibleBalance) {
        this.irreducibleBalance = irreducibleBalance;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!super.equals(o)) {
            return false;
        }
        CheckinDeposit that = (CheckinDeposit) o;
        return timeConstraints.equals(that.timeConstraints) &&
                irreducibleBalance == that.irreducibleBalance;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = result * prime + timeConstraints.hashCode();
        result = result * prime + (int)getIrreducibleBalance();
        return result;
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() +
                "{" + super.toString() +
                ", timeConstraints=" + timeConstraints.toString() +
                ", irreducibleBalance='" + getIrreducibleBalance() + '\'' +
                '}';
    }
}
