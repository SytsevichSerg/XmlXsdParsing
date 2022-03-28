
package com.epam.task2.entity;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;


public class MetalDeposit extends Deposit{
    
    private List<Metal> metals;
    
    public MetalDeposit() {
        metals = new ArrayList<>();
    }
    
    public MetalDeposit(Bank bank, Country country, String depositor, String accountId, double amount, float profitability, boolean depositCallable, boolean withdrawalCallable, List<Metal> metals) {
        super(bank, country, depositor, accountId, amount, profitability, depositCallable, withdrawalCallable);
        this.metals = metals;
    }

    @XmlElementWrapper(name = "metals")
    @XmlElement(name = "metal")
    public List<Metal> getMetals() {
        return metals;
    }

    public void setMetals(List<Metal> metals) {
        this.metals = metals != null ? new ArrayList<>(metals) : new ArrayList<>() ;
    }
    
    @Override
    public boolean equals(Object o) {
        
        boolean ret = true;
        if (this == o) {
            return true;
        }
        if (!super.equals(o)) {
            return false;
        }
        MetalDeposit that = (MetalDeposit) o;
        if (that.metals == null ? metals != null : !(that.metals.equals(metals))) {
            ret = false;
        }
        return ret;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + (metals == null ? 0 : metals.hashCode());
        return result;
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() +
                "{" + super.toString() +
                ", metals=" + metals + '\'' +
                '}';
    }
    
}
