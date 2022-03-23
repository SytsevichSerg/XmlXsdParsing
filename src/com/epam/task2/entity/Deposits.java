
package com.epam.task2.entity;

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
        @XmlElement(type=OnDemandDeposit.class, name = "on-demand")
    })
}
