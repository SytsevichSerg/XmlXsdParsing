
package com.epam.task2.entity;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "metal")
@XmlEnum
public enum Metal {
    AU("AU"),
    AG("AG"),
    PT("PT"),
    PD("PD");

    private final String value;

    Metal(String value){
        this.value = value;
    }

    public String getName(){
        return value;
    }

    public static Metal getMetal(String name){
        for (Metal metal : Metal.values()) {
            if(name.equals(metal.getName())){
                return metal;
            }
        }
        return null;
    }
    
}
