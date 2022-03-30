
package com.epam.task2.builder;

import com.epam.task2.entity.Deposit;
import com.epam.task2.exception.ParsingXMLException;
import java.util.HashSet;
import java.util.Set;


public abstract class AbstractDepositBuilder {
    public static final String SCHEMA_RESOURCE_NAME = "deposits.xsd";
    
    protected Set<Deposit> deposits;
    
    AbstractDepositBuilder(){
        deposits = new HashSet<>();
    }
    
    public Set<Deposit> getDeposits() {
        return deposits;
    }
        
    public abstract void buildSetDeposits(String fileName) throws ParsingXMLException;
}
