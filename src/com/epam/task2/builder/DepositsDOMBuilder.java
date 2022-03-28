package com.epam.task2.builder;

import com.epam.task2.entity.AccumulationDeposit;
import com.epam.task2.entity.Bank;
import com.epam.task2.entity.CheckinDeposit;
import com.epam.task2.entity.Country;
import com.epam.task2.entity.Deposit;
import com.epam.task2.entity.ExpressDeposit;
import com.epam.task2.entity.Metal;
import com.epam.task2.entity.MetalDeposit;
import com.epam.task2.entity.MetalNomenclature;
import com.epam.task2.entity.OnDemandDeposit;
import com.epam.task2.exception.ParsingXMLException;
import com.epam.task2.handler.DepositXmlAttribute;
import com.epam.task2.handler.DepositXmlTag;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
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
        
        super();
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            documentBuilder = factory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            LOG.error("Dom configuration encounter error", e);
        }
    }
    
    @Override
    public void buildSetDeposits(String fileName) throws ParsingXMLException {
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
    
    private void createDeposits(Element root, DepositXmlTag depositXmlTag) throws ParsingXMLException {
        NodeList depositList = root.getElementsByTagName(depositXmlTag.getName());
        for (int i = 0; i < depositList.getLength(); i++) {
            Element depositElement = (Element) depositList.item(i);
            Deposit deposit = buildDeposit(depositElement, depositXmlTag);
            deposits.add(deposit);
        }
    }
    
    private Deposit buildDeposit(Element element, DepositXmlTag depositXmlTag) throws ParsingXMLException {
         
        Deposit deposit = new OnDemandDeposit();
        
        Bank bank = getElementBank(element);
        Country country = getElementCountry(element);
        String depositor = getElementTextContent(element, DepositXmlTag.DEPOSITOR.getName());
        String accountId = element.getAttribute(DepositXmlAttribute.ACCOUNT_ID.getName());
        double amount = getElementDoubleContent(element, DepositXmlTag.AMOUNT_ON_DEPOSIT.getName());
        float profitability = getElementFloatContent(element, DepositXmlTag.PROFITABILITY.getName());
        boolean depositCallable = Boolean.parseBoolean(element.getAttribute(DepositXmlAttribute.DEPOSIT_CALLABLE.getName()));
        boolean withdrawalCallable = Boolean.parseBoolean(element.getAttribute(DepositXmlAttribute.WITHDRAWAL_CALLABLE.getName()));
        
     
        
        LocalDate timeConstraints = LocalDate.parse(getElementTextContent(element, DepositXmlTag.TIME_CONSTRAINTS.getName()));
        LocalDate dateUntil = LocalDate.parse(getElementTextContent(element, DepositXmlTag.DATE_UNTIL_REPLENISHMENT_ALLOWED.getName()));
        double irreducibleBalance =  getElementDoubleContent(element, DepositXmlTag.IRREDUCIBLE_BALANCE.getName());
        
        
        switch (depositXmlTag) {
            
            case SAVING_DEPOSIT:
            case EXPRESS_DEPOSIT: {
                ExpressDeposit temp = ExpressDeposit.setNewExpressDeposit(deposit, timeConstraints);
                deposit = temp;
            }break;
            
            case CHECKIN_DEPOSIT: {
                CheckinDeposit temp = CheckinDeposit.setNewCheckinDeposit(deposit, timeConstraints, irreducibleBalance);
                deposit = temp;
            }break;
            
            case ACCUMULATION_DEPOSIT: {
                AccumulationDeposit temp = AccumulationDeposit.setNewAccumulationDeposit(deposit, timeConstraints, dateUntil);
                deposit = temp;
            }break;
            
            case METAL_DEPOSIT: {
                List<Metal> metals = buildMetalsList(element);
                MetalDeposit temp = MetalDeposit.setNewMetalDeposit(deposit, metals);
                deposit = temp;
            }break;
        }
        
        deposit.setBank(bank);
        deposit.setCountry(country);
        deposit.setDepositor(depositor);
        deposit.setAccountId(accountId);
        deposit.setAmount(amount);
        deposit.setProfitability(profitability);
        deposit.setDepositCallable(depositCallable);
        deposit.setWithdrawalCallable(withdrawalCallable);

        return deposit;

    }
    
    private float getElementFloatContent(Element element, String tagName) {
        String stringFloat = getElementTextContent(element, tagName);
        return Float.parseFloat(stringFloat);
    }
    
    private double getElementDoubleContent(Element element, String tagName) {
        String stringDouble = getElementTextContent(element, tagName);
        return Double.parseDouble(stringDouble);
    }
     
    private String getElementTextContent(Element element, String tagName) {
        NodeList nodeList = element.getElementsByTagName(tagName);
        Node node = nodeList.item(0);
        return node.getTextContent();
    }

    private Bank getElementBank(Element element) {
        String bankName = getElementTextContent(element, DepositXmlTag.BANK_NAME.getName());
        return Bank.getBank(bankName);
    }

    private Country getElementCountry(Element element) {
        String country = getElementTextContent(element, DepositXmlTag.COUNTRY.getName());
        return Country.getCounty(country);
    }
    
    private MetalNomenclature getElementMetalNomenclature(Element element) throws ParsingXMLException{
        String name = getElementTextContent(element, DepositXmlTag.METAL_NOMENCLATURE.getName());
        return MetalNomenclature.getMetalNomenclature(name);
    }
    
    private List<Metal> buildMetalsList(Element element) throws ParsingXMLException {
        NodeList nodeList = element.getElementsByTagName(DepositXmlTag.METALS.getName());
        Element metalsNode = (Element) nodeList.item(0);
        NodeList metalNodeList = metalsNode.getElementsByTagName(DepositXmlTag.METAL.getName());
        List<Metal> metals = new ArrayList<>();
        for (int i = 0; i < metalNodeList.getLength(); i++) {
            metals.add(buildMetal(metalNodeList.item(i)));
        }
        return metals;
    }

    private Metal buildMetal(Node node) throws ParsingXMLException {
        Metal metal = new Metal();
        Element element = (Element) node;
        MetalNomenclature name = getElementMetalNomenclature(element);
        double weight = getElementDoubleContent(element, DepositXmlTag.WEIGHT.getName());
        double rate = getElementDoubleContent(element, DepositXmlTag.METAL_PURCHASE_RATE.getName());
        metal.setNomencalture(name);
        metal.setWeight(weight);
        metal.setRate(rate);
        return metal;
    }
       
}
