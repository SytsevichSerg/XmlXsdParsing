
package com.epam.task2.entity;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


@XmlType(name = "bank-name")
@XmlEnum
public enum Bank {
    @XmlEnumValue("Absolutbank")
    ABSOLUTBANK("Absolutbank"),
    @XmlEnumValue("Alpha-Bank")
    ALPHA_BANK("Alpha-Bank"),
    @XmlEnumValue("BankBelWEB")
    BANKBELWEB("BankBelWEB"),
    @XmlEnumValue("BankWTB")
    BANKWTB("BankWTB"),
    @XmlEnumValue("SberBank")
    SBARBANK("SberBank"),
    @XmlEnumValue("Tecnobank")
    TECHNOBANK("Tecnobank"),
    @XmlEnumValue("MTBank")
    MTBANK("MTBank"),
    @XmlEnumValue("Paritetbank")
    PARITETBANK("Paritetbank"),
    @XmlEnumValue("Priorbank")
    PRIORBANK("Priorbank");
    
    private String name;

    Bank(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static Bank getBank(String name) {
        for (Bank bank : Bank.values()) {
            if(name.equals(bank.getName())){
                return bank;
            }
        }
        return null;
    }
    
}
