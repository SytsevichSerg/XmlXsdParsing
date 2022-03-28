
package com.epam.task2.entity;

import java.util.HashSet;
import java.util.Set;
import javax.xml.bind.annotation.*;

@XmlRootElement
public class Deposits {
    private Set<Deposit> deposits;
    
    public Deposits() {
        this.deposits = new HashSet<>();
    }
    
    public Set<Deposit> getDeposits() {
        return deposits;
    }
    
    @XmlElementWrapper
    @XmlElements({
        @XmlElement(type=OnDemandDeposit.class, name = "on-demand-deposi"),
        @XmlElement(type=ExpressDeposit.class, name = "express-deposi"),
        @XmlElement(type=CheckinDeposit.class, name = "checkin-deposit"),
        @XmlElement(type=AccumulationDeposit.class, name = "accumulation-deposit"),
        @XmlElement(type=SavingDeposit.class, name = "saving-deposit"),
        @XmlElement(type=MetalDeposit.class, name = "metal-deposit")
    })
    
    public void setDeposits(Set<Deposit> deposits){
        this.deposits = deposits;
    }
    
    public void add(Deposit deposit) {
        if(this.deposits == null) {
            this.deposits = new HashSet<>();
        }
        deposits.add(deposit);
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || this.getClass() != o.getClass()) {
            return false;
        }
        Deposits that = (Deposits) o;
        return this.deposits != null ? this.deposits.equals(that.deposits) : that.deposits == null;
    }

    @Override
    public int hashCode() {
        return deposits != null ? deposits.hashCode() : 0;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder(this.getClass().getSimpleName());
        stringBuilder.append("{deposits=").append(deposits).append('}');
        return stringBuilder.toString();
    }
}
