
package com.epam.task2.entity;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


@XmlType(name = "country")
@XmlEnum
public enum Country {
    @XmlEnumValue("AT")
    AT("AT"),
    @XmlEnumValue("PL")
    PL("PL"),
    @XmlEnumValue("BY")
    BY("BY"),
    @XmlEnumValue("RU")
    RU("RU"),
    @XmlEnumValue("DE")
    DE("DE"),
    @XmlEnumValue("US")
    US("US"),
    @XmlEnumValue("UA")
    UA("UA"),
    @XmlEnumValue("LT")
    LT("LT"),
    @XmlEnumValue("LV")
    LV("LV");
    
    private String name;
    
     Country(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static Country getCounty(String name) {
        for (Country country : Country.values()) {
            if(name.equals(country.getName())){
                return country;
            }
        }
        return null;
    }
}
