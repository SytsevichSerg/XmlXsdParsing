
package com.epam.task2.builder;

import com.epam.task2.entity.Deposit;
import java.util.HashSet;
import java.util.Set;


public abstract class AbstractDepositBuilder {
    protected Set<Deposit> deposits;
    
    AbstractDepositBuilder(){
        deposits = new HashSet<>();
    }
    
    public abstract void buildSetDeposits(String fileName);
    
    public Set<Deposit> getDeposits() {
        return deposits;
    }
    
}
