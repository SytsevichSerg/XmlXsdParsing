/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epam.task2.builder;

import com.epam.task2.entity.Bank;
import com.epam.task2.entity.Country;
import com.epam.task2.entity.Deposit;
import com.epam.task2.entity.OnDemandDeposit;
import com.epam.task2.handler.DepositXmlTag;
import java.io.IOException;
import java.net.URL;
import java.time.YearMonth;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;


public class DepositsDOMBuilder extends AbstractDepositBuilder {
    
    private static final Logger LOG = LogManager.getLogger();
    private DocumentBuilder documentBuilder;
    
    public DepositsDOMBuilder() {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            documentBuilder = factory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            LOG.error("Dom configuration encounter error", e);
        }
    }
    
    @Override
    public void buildSetDeposits(String fileName) {
        Document doc;
        try {
            ClassLoader loader = getClass().getClassLoader();
            URL resource = loader.getResource(fileName);

            doc = documentBuilder.parse(resource.getFile());
            Element root = doc.getDocumentElement();
            
            createDeposits(root, DepositXmlTag.ON_DEMAND_DEPOSIT);
            createDeposits(root, DepositXmlTag.CHECKIN_DEPOSIT);
            createDeposits(root, DepositXmlTag.EXPRESS_DEPOSIT);
            createDeposits(root, DepositXmlTag.ACCUMULATION_DEPOSIT);
            createDeposits(root, DepositXmlTag.SAVING_DEPOSIT);
            createDeposits(root, DepositXmlTag.METAL_DEPOSIT);
        } catch (IOException | SAXException e) {
            LOG.error("Error while reading", e);
        }
    }
    private void createDeposits(Element root, DepositXmlTag depositXmlTag) {
        NodeList depositList = root.getElementsByTagName(depositXmlTag.getName());
        for (int i = 0; i < depositList.getLength(); i++) {
            Element depositElement = (Element) depositList.item(i);
            Deposit deposit = buildDeposit(depositElement, depositXmlTag);
            deposits.add(deposit);
        }
    }
    
    private float getElementFloatContent(Element element, String tagName) {
        String stringFloat = getElementTextContent(element, tagName);
        return Float.parseFloat(stringFloat);
    }
    
    private double getElementDoubleContent(Element element, String tagName) {
        String stringDouble = getElementTextContent(element, tagName);
        return Double.parseDouble(stringDouble);
    }
     
    private int getElementIntContent(Element element, String tagName) {
        String stringInt = getElementTextContent(element, tagName);
        return Integer.parseInt(stringInt);
    }


    private String getElementTextContent(Element element, String tagName) {
        NodeList nodeList = element.getElementsByTagName(tagName);
        Node node = nodeList.item(0);
        return node.getTextContent();
    }

    private Bank getElementBankName(Element element) {
        String bankName = getElementTextContent(element, DepositXmlTag.BANK_NAME.getName());
        return Bank.getBank(bankName);
    }

    private Country getElementCountry(Element element) {
        String country = getElementTextContent(element, DepositXmlTag.COUNTRY.getName());
        return Country.getCounty(country);
    }

    private YearMonth getElementYearMonthContent(Element element, String tagName) {
        String yearMonthString = getElementTextContent(element, tagName);
        return YearMonth.parse(yearMonthString);
    }

    private boolean getElementBooleanContent(Element candyElement, String tagName) {
        String booleanString = getElementTextContent(candyElement, tagName);
        return Boolean.parseBoolean(booleanString);
    }
    
    private Deposit buildDeposit(Element element, DepositXmlTag depositXmlTag) {
         
         Deposit deposit = new OnDemandDeposit();

        String bankName = getElementTextContent(element, DepositXmlTag.BANK_NAME.getName());
        String country = getElementTextContent(element, DepositXmlTag.COUNTRY.getName());
        OperatorName operatorName = OperatorName.getNameFromString(element.getAttribute(tariffXmlTag.OPERATOR_NAME.getValue()));
        int monthPayRoll = Integer.parseInt(getElementTextContent(element, TariffXmlTag.MONTH_PAY_ROLL.getValue()));
        int smsPrise = Integer.parseInt(getElementTextContent(element, TariffXmlTag.SMS_PRISE.getValue()));
        int costConnect = Integer.parseInt(getElementTextContent(element, TariffXmlTag.COST_CONNECT.getValue()));
        LocalDate dateСonnectingTariff = LocalDate.parse(getElementTextContent(element, TariffXmlTag.DATE_CONNECTING_TARIFF
                .getValue()));
        switch (tariffXmlTag) {
            case INTERNET_TARIFF -> {
                int numberFreeMegabytes = Integer.parseInt(getElementTextContent(element, TariffXmlTag.NUMBER_FREE_MEGABYTES.getValue()));
                int costMegabytesAfterFree = Integer.parseInt(getElementTextContent(element, TariffXmlTag.COST_MEGABYTES_AFTER_FREE.getValue()));
                int costRoamingMegabytes = Integer.parseInt(getElementTextContent(element, TariffXmlTag.COST_ROAMING_MEGABYTES.getValue()));
                int numberFreeMegabytesSocialNetworks = Integer.parseInt(getElementTextContent(element, TariffXmlTag.NUMBER_FREE_MEGABYTES_SOCIAL_NETWORKS.getValue()));
                InternetTariff temp = InternetTariff.setNewInternetTariff(tariff, numberFreeMegabytes, costMegabytesAfterFree,
                        costRoamingMegabytes, numberFreeMegabytesSocialNetworks);
                tariff = temp;
            }
            case CALLING_TARIFF -> {
                int preferredNumber = Integer.parseInt(getElementTextContent(element, tariffXmlTag.PREFERRED_NUMBER.getValue()));
                int costInNetworkCalls = Integer.parseInt(getElementTextContent(element, tariffXmlTag.COST_IN_NETWORK_CALLS.getValue()));
                int costOffNetworkCalls = Integer.parseInt(getElementTextContent(element, tariffXmlTag.COST_OFF_NETWORK_CALLS.getValue()));
                int costLandlinePhoneCalls = Integer.parseInt(getElementTextContent(element, tariffXmlTag.COST_LANDLINE_PHONE_CALLS.getValue()));
                CallingTariff temp = CallingTariff.setNewCallingTariff(tariff, preferredNumber, costInNetworkCalls,
                        costOffNetworkCalls, costLandlinePhoneCalls);
                tariff = temp;
            }
        }
        tariff.setId(id);
        tariff.setTariffName(tariffName);
        tariff.setOperatorName(operatorName);
        tariff.setMonthPayRoll(monthPayRoll);
        tariff.setSmsPrise(smsPrise);
        tariff.setCostConnect(costConnect);
        tariff.setDateСonnectingTariff(dateСonnectingTariff);
        return tariff;

    }
    
    
}
