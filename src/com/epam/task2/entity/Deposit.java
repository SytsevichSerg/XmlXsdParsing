/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epam.task2.entity;

import com.epam.task2.parser.YearMonthAdapter;
import java.sql.Time;
import java.util.Objects;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.time.YearMonth;



public abstract class Deposit {
    private Bank bank;
    private Country country;
    private String depositor;
    private String accountId;
    private double amount;
    private float profitability;
    private YearMonth timeConstraints;

    public Deposit() {
        
    }
    
    public Deposit(Bank bank, Country country, String depositor, String accountId, double amount, float profitability, YearMonth timeConstraints) {
        this.bank = bank;
        this.country = country;
        this.depositor = depositor;
        this.accountId = accountId;
        this.amount = amount;
        this.profitability = profitability;
        this.timeConstraints = timeConstraints;
    }
    
    @XmlElement(name = "bank-name")
    public Bank getBank() {
        return bank;
    }

    /**
     * @param bankName the bankName to set
     */
    public void setBank(Bank bank) {
        this.bank = bank;
    }

    /**
     * @return the country
     */
    public Country getCountry() {
        return country;
    }

    /**
     * @param country the country to set
     */
    public void setCountry(Country country) {
        this.country = country;
    }

    /**
     * @return the depositor
     */
    public String getDepositor() {
        return depositor;
    }

    /**
     * @param depositor the depositor to set
     */
    public void setDepositor(String depositor) {
        this.depositor = depositor;
    }

    /**
     * @return the accountId
     */
    public String getAccountId() {
        return accountId;
    }

    /**
     * @param accountId the accountId to set
     */
    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    /**
     * @return the amount
     */
    public double getAmount() {
        return amount;
    }

    /**
     * @param amount the amount to set
     */
    public void setAmount(double amount) {
        this.amount = amount;
    }

    /**
     * @return the profitability
     */
    public float getProfitability() {
        return profitability;
    }

    /**
     * @param profitability the profitability to set
     */
    public void setProfitability(float profitability) {
        this.profitability = profitability;
    }

    @XmlJavaTypeAdapter(YearMonthAdapter.class)
    @XmlElement(name = "time-constraints")
    public YearMonth getTimeConstraints() {
        return timeConstraints;
    }

    /**
     * @param timeConstraints the timeConstraints to set
     */
    public void setTimeConstraints(YearMonth timeConstraints) {
        this.timeConstraints = timeConstraints;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Deposit that = (Deposit) o;
        return bankName == that.bankName && country == that.country && depositor == that.depositor
                && accountId == that.accountId && amount == that.amount && Objects.equals(timeConstraints, that.timeConstraints);
    }
        
    @Override
    public int hashCode() {

	final int prime = 101;
	int hashCode = 1;
        hashCode = prime * hashCode + (bankName == null ? 0 : getBankName().hashCode());
        hashCode = prime * hashCode + (country == null ? 0 : getCountry().hashCode());
        hashCode = prime * hashCode + (depositor == null ? 0 : getDepositor().hashCode());
        hashCode = prime * hashCode + (accountId == null ? 0 : getAccountId().hashCode());
        hashCode = prime * hashCode + (int)getAmount();
        return hashCode;	
    }
    
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("bankName='").append(bankName).append('\'').append("country='").append(country).append('\'')
			.append(", depositor='").append(depositor).append('\'').append(", accountId=").append(accountId)
                        .append(", amount=").append(amount).append(", profitability=").append(profitability).append(", timeConstraints=").append(timeConstraints);   	     
                    
        return builder.toString();    
    }

}
