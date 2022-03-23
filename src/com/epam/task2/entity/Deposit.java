/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epam.task2.entity;

import java.sql.Time;


public class Deposit {
    private String bankName;
    private String country;
    private String depositor;
    private String accountId;
    private double amount;
    private float profitability;
    private Time timeConstraints;

    public Deposit(String bankName, String country, String depositor, String accountId, double amount, float profitability, Time timeConstraints) {
        this.bankName = bankName;
        this.country = country;
        this.depositor = depositor;
        this.accountId = accountId;
        this.amount = amount;
        this.profitability = profitability;
        this.timeConstraints = timeConstraints;
    }
    /**
     * @return the bankName
     */
    public String getBankName() {
        return bankName;
    }

    /**
     * @param bankName the bankName to set
     */
    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    /**
     * @return the country
     */
    public String getCountry() {
        return country;
    }

    /**
     * @param country the country to set
     */
    public void setCountry(String country) {
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

    /**
     * @return the timeConstraints
     */
    public Time getTimeConstraints() {
        return timeConstraints;
    }

    /**
     * @param timeConstraints the timeConstraints to set
     */
    public void setTimeConstraints(Time timeConstraints) {
        this.timeConstraints = timeConstraints;
    }
    
    
    
}