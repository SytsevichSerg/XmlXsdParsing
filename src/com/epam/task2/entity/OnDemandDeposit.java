
package com.epam.task2.entity;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "on-demand")
@XmlRootElement
public class OnDemandDeposit extends Deposit{
    
    OnDemandDeposit(){
        
    }
/*    public CaramelCandy(String vendorCode, String name, YearMonth expirationDate, int energy, Value value, List<Ingredient> ingredients, Production production, boolean isLollipop, String flavor) {
        super(vendorCode, name, expirationDate, energy, value, ingredients, production);
        this.isLollipop = isLollipop;
        this.flavor = flavor;
    }
*/
}
