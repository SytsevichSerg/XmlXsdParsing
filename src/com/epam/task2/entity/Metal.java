
package com.epam.task2.entity;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlEnumValue;

@XmlType(name = "metal")
@XmlEnum
public enum Metal {
    @XmlEnumValue("Au")
    AU("Au"),
    @XmlEnumValue("Ag")
    AG("Ag"),
    @XmlEnumValue("Pt")
    PT("Pt"),
    @XmlEnumValue("Pd")
    PD("Pd");

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
