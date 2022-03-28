/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epam.task2.entity;

import com.epam.task2.parser.YearMonthAdapter;
import java.math.BigDecimal;
import java.sql.Time;
import java.util.Objects;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.time.YearMonth;
import javax.xml.bind.annotation.*;


@XmlType(name = "deposit")
public abstract class Deposit {
   
    private Country country;
    private boolean depositCallable;
    private boolean withdrawalCallable;
    private String depositor;
    private String accountId;
    private double amount;
    private float profitability;
    private Bank bank;

    public Deposit() {
        
    }
    
    // from attributes
    public Deposit(String accountId, boolean depositCallable, boolean withdrawalCallable) {
        
        this.accountId = accountId;
        this.depositCallable = depositCallable;
        this.withdrawalCallable = withdrawalCallable;
    }
    
    public Deposit(Bank bank, Country country, String depositor, String accountId, double amount, float profitability, boolean depositCallable, boolean withdrawalCallable) {
        this.bank = bank;
        this.country = country;
        this.depositor = depositor;
        this.accountId = accountId;
        this.amount = amount;
        this.profitability = profitability;
        this.depositCallable = depositCallable;
        this.withdrawalCallable = withdrawalCallable;
    }
    
    @XmlElement(name = "bank-name")
    public Bank getBank() {
        return bank;
    }

    public void setBank(Bank bank) {
        this.bank = bank;
    }

    @XmlElement(name = "country")
    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    @XmlElement(name = "depositor")
    public String getDepositor() {
        return depositor;
    }

    public void setDepositor(String depositor) {
        this.depositor = depositor;
    }

    @XmlAttribute(name = "account-id")
    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    @XmlElement(name = "amount-on-deposit")
    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    @XmlElement(name = "profitability")
    public float getProfitability() {
        return profitability;
    }

    public void setProfitability(float profitability) {
        this.profitability = profitability;
    }

    @XmlAttribute(name = "deposit-callable")
    public boolean isDepositCallable() {
        return depositCallable;
    }

    public void setDepositCallable(boolean depositCallable) {
        this.depositCallable = depositCallable;
    }

    @XmlAttribute(name = "withdrawal-callable")
    public boolean isWithdrawalCallable() {
        return withdrawalCallable;
    }

    public void setWithdrawalCallable(boolean withdrawalCallable) {
        this.withdrawalCallable = withdrawalCallable;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Deposit that = (Deposit) o;
        return bank.equals(that.bank) && country.equals(that.country) && depositor.equals(that.depositor) 
                && accountId.equals(that.accountId) && amount == that.amount && depositCallable == that.depositCallable && withdrawalCallable == that.withdrawalCallable;
    }
        
    @Override
    public int hashCode() {

	final int prime = 101;
	int hashCode = 1;
        hashCode = prime * hashCode + (bank == null ? 0 : getBank().hashCode());
        hashCode = prime * hashCode + (country == null ? 0 : getCountry().hashCode());
        hashCode = prime * hashCode + (depositor == null ? 0 : getDepositor().hashCode());
        hashCode = prime * hashCode + (accountId == null ? 0 : getAccountId().hashCode());
        hashCode = prime * hashCode + (int)getAmount();
        hashCode = prime * hashCode + (int)getProfitability();
        hashCode = prime * hashCode + (depositCallable ? 1 : 0);
        hashCode = prime * hashCode + (withdrawalCallable ? 1 : 0);
        return hashCode;	
    }
    
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("bank='").append(bank.getName()).append('\'').append("country='").append(country.getName()).append('\'')
			.append(", depositor='").append(depositor).append('\'').append(", accountId=").append(accountId)
                        .append(", amount=").append(amount).append(", profitability=").append(profitability).append(", depositCallable=").append(depositCallable).append(", withdrawalCallable=").append(withdrawalCallable);   	     
                    
        return builder.toString();    
    }

    

}
