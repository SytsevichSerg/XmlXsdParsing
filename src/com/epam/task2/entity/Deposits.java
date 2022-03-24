
package com.epam.task2.entity;

import java.util.HashSet;
import java.util.Set;
import javax.xml.bind.annotation.*;

@XmlRootElement
public class Deposits {
    Set<Deposit> deposits;
    
    public Set<Deposit> getDeposits() {
        return deposits;
    }
    
    @XmlElementWrapper
    @XmlElements({
        @XmlElement(type=OnDemandDeposit.class, name = "on-demand"),
        @XmlElement(type=ExpressDeposit.class, name = "express"),
        @XmlElement(type=CheckinDeposit.class, name = "checkin"),
        @XmlElement(type=AccumulationDeposit.class, name = "accumulation"),
        @XmlElement(type=SavingDeposit.class, name = "saving"),
        @XmlElement(type=MetalDeposit.class, name = "metal")
    })
    
    public void setDeposits(Set<Deposit> deposits){
        this.deposits = deposits;
    }
    
    public void add(Deposit deposit) {
        if(this.deposits == null) {
            this.deposits = new HashSet<>();
        }
    }
}
