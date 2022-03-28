
package com.epam.task2.entity;

import java.sql.Time;
import java.time.LocalDate;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "accumulation-type")
@XmlRootElement
public class AccumulationDeposit extends Deposit{
    
    private LocalDate timeConstraints;
    private LocalDate dateUntilReplenishmentAllowed;
    
    public AccumulationDeposit() {
         super();
    }
    
    public AccumulationDeposit(String accountId, boolean depositCallable, boolean withdrawalCallable) {
        super(accountId, depositCallable, withdrawalCallable);
    }
    
    public AccumulationDeposit(Bank bank, Country country, String depositor, String accountId, double amount, float profitability, boolean depositCallable, boolean withdrawalCallable, 
            LocalDate timeConstraints, LocalDate dateUntilReplenishmentAllowed) {
        super(bank, country, depositor, accountId, amount, profitability, depositCallable, withdrawalCallable);
        this.timeConstraints = timeConstraints;
        this.dateUntilReplenishmentAllowed = dateUntilReplenishmentAllowed;
    }
    
    public static AccumulationDeposit setNewAccumulationDeposit(Deposit deposit,  LocalDate timeConstraints, LocalDate dateUntilReplenishmentAllowed) {
        return new AccumulationDeposit(deposit.getBank(), deposit.getCountry(), deposit.getDepositor(), deposit.getAccountId(), 
                deposit.getAmount(), deposit.getProfitability(), deposit.isDepositCallable(), deposit.isWithdrawalCallable(), timeConstraints, dateUntilReplenishmentAllowed);
    }

    @XmlElement(name = "time-constraints")
    public LocalDate getTimeConstraints() {
        return timeConstraints;
    }

    public void setTimeConstraints(LocalDate timeConstraints) {
        this.timeConstraints = timeConstraints;
    }

    @XmlElement(name = "date-until-replenishment-allowed")
    public LocalDate getDateUntilReplenishmentAllowed() {
        return dateUntilReplenishmentAllowed;
    }

    public void setDateUntilReplenishmentAllowed(LocalDate dateUntilReplenishmentAllowed) {
        this.dateUntilReplenishmentAllowed = dateUntilReplenishmentAllowed;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!super.equals(o)) {
            return false;
        }
        AccumulationDeposit that = (AccumulationDeposit) o;
        return timeConstraints.equals(that.timeConstraints) &&
                dateUntilReplenishmentAllowed.equals(that.dateUntilReplenishmentAllowed);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = result * prime + timeConstraints.hashCode();
        result = result * prime + dateUntilReplenishmentAllowed.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() +
                "{" + super.toString() +
                ", timeConstraints=" + timeConstraints.toString() +
                ", dateUntilReplenishmentAllowed='" + dateUntilReplenishmentAllowed.toString() + '\'' +
                '}';
    }
}
