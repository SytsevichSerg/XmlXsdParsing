
package com.epam.task2.entity;

import com.epam.task2.exception.ParsingXMLException;
import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlEnumValue;

@XmlType(name = "metal-nomenclature")
@XmlEnum
public enum MetalNomenclature {
    @XmlEnumValue("Au")
    AU("Au"),
    @XmlEnumValue("Ag")
    AG("Ag"),
    @XmlEnumValue("Pt")
    PT("Pt"),
    @XmlEnumValue("Pd")
    PD("Pd");

    private final String value;

    MetalNomenclature(String value){
        this.value = value;
    }

    public String getName(){
        return value;
    }

    public static MetalNomenclature getMetalNomenclature(String name) throws ParsingXMLException{
        for (MetalNomenclature metal : MetalNomenclature.values()) {
            if(name.equals(metal.getName())){
                return metal;
            }
        }
        throw new ParsingXMLException("MetalNomenclature with name '" + name + "' doesn't exist");
    }
    
}
