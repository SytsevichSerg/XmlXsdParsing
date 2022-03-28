/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epam.task2.parser;

import java.time.YearMonth;
import javax.xml.bind.annotation.adapters.XmlAdapter;

public class YearMonthAdapter extends XmlAdapter<String, YearMonth> {
    public YearMonth unmarshal(String date) {
        return YearMonth.parse(date);
    }

    public String marshal(YearMonth date) {
        return date.toString();
    }
}
