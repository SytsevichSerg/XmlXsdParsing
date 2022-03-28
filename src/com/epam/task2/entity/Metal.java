/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epam.task2.entity;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "metal")
public class Metal implements Serializable {

    private static final long serialVersionUID = 1L;

    private MetalNomenclature nomencalture;
    private double weight;
    private double rate;

    
    
    @XmlElement(name = "metal-nomenclature")
    public MetalNomenclature getNomencalture() {
        return nomencalture;
    }

    public void setNomencalture(MetalNomenclature nomencalture) {
        this.nomencalture = nomencalture;
    }

    @XmlElement(name = "weight")
    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

   @XmlElement(name = "metal-purchase-rate")
   public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }
    
    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || object.getClass() != this.getClass()) {
            return false;
        }
        Metal that = (Metal) object;
        return weight == that.weight && rate == that.rate && 
                nomencalture == that.nomencalture;
    }
    
    @Override
    public int hashCode() {
        final int prime = 101;
        int hash = 1;
        hash = prime*hash + (nomencalture == null ? 0 : nomencalture.hashCode());
        hash = prime*hash + (int)weight;
        hash = prime*hash + (int)rate;
        return hash;
    }
    
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("metal-nomenclature='").append(nomencalture).append('\'').
                append(", weight='").append(weight).append('\'').
                append(", metal-purchase-rate=").append(rate);
        return builder.toString();
    }
    
}
