
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
import com.epam.task2.entity.SavingDeposit;
import com.epam.task2.exception.ParsingXMLException;
import com.epam.task2.handler.DepositXmlAttribute;
import com.epam.task2.handler.DepositXmlTag;
import com.epam.task2.util.ResourcePathUtil;
import com.epam.task2.validator.XmlFileValidator;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

        
public class DepositsSTAXBuilder extends AbstractDepositBuilder{
    
    private static final Logger LOG = LogManager.getLogger();

    private ClassLoader loader;
    private XMLInputFactory inputFactory;
    
    
    public DepositsSTAXBuilder(){
        
        super();
        inputFactory = XMLInputFactory.newInstance();
        loader = getClass().getClassLoader();
    }
   
    @Override
    public void buildSetDeposits(String fileName) throws ParsingXMLException {
        String schemaFileName = ResourcePathUtil.getResourcePath(AbstractDepositBuilder.SCHEMA_RESOURCE_NAME);
        XmlFileValidator validator = XmlFileValidator.getInstance();
        if (validator.isCorrect(fileName, schemaFileName)) {
            XMLStreamReader reader;
            String name;
            
            try (FileInputStream inputStream = new FileInputStream(new File(fileName))) {
                reader = inputFactory.createXMLStreamReader(inputStream);
                while (reader.hasNext()) {
                    int type = reader.next();
                    if (type == XMLStreamConstants.START_ELEMENT) {
                        name = reader.getLocalName();
                        DepositXmlTag tag = DepositXmlTag.getDepositXmlTag(name);
                        if (DepositXmlTag.ON_DEMAND_DEPOSIT == tag) {
                            Deposit deposit = buildDeposit(new OnDemandDeposit(), reader);
                            deposits.add(deposit);
                        } else if (DepositXmlTag.EXPRESS_DEPOSIT == tag) {
                            Deposit deposit = buildDeposit(new ExpressDeposit(), reader);
                            deposits.add(deposit);
                        } else if (DepositXmlTag.SAVING_DEPOSIT == tag) {
                            Deposit deposit = buildDeposit(new SavingDeposit(), reader);
                            deposits.add(deposit);
                        } else if (DepositXmlTag.CHECKIN_DEPOSIT == tag) {
                            Deposit deposit = buildDeposit(new CheckinDeposit(), reader);
                            deposits.add(deposit);
                        } else if (DepositXmlTag.ACCUMULATION_DEPOSIT == tag) {
                            Deposit deposit = buildDeposit(new CheckinDeposit(), reader);
                            deposits.add(deposit);
                        } else if (DepositXmlTag.METAL_DEPOSIT == tag) {
                            Deposit deposit = buildDeposit(new MetalDeposit(), reader);
                            deposits.add(deposit);
                        }
                    }
                }
            }catch (XMLStreamException | IOException | ParsingXMLException e) {
                LOG.error("Exception during build the set of deposits", e);
                throw new ParsingXMLException(e);
            }
        }else {
            LOG.info("File '" + fileName + "' does not match schema '" + schemaFileName + "'");
            throw new ParsingXMLException("File '" + fileName + "' does not match schema '" + schemaFileName + "'");
        }
    }
    
    private Deposit buildDeposit(Deposit deposit, XMLStreamReader reader) throws XMLStreamException, ParsingXMLException  {
        deposit.setAccountId(reader.getAttributeValue(null, DepositXmlAttribute.ACCOUNT_ID.getName()));
        deposit.setDepositCallable( Boolean.parseBoolean( reader.getAttributeValue(null, DepositXmlAttribute.DEPOSIT_CALLABLE.getName())));
        deposit.setWithdrawalCallable( Boolean.parseBoolean(reader.getAttributeValue(null, DepositXmlAttribute.WITHDRAWAL_CALLABLE.getName())));
 
        String name;
        while (reader.hasNext()) {
            int type = reader.next();
            switch (type) {
                case XMLStreamConstants.START_ELEMENT : {
                    name = reader.getLocalName();
                    DepositXmlTag tag = DepositXmlTag.getDepositXmlTag(name);
                    switch (tag) {
                        case BANK_NAME : {
                           deposit.setBank(Bank.getBank(getXMLText(reader)));
                        } break;
                        case COUNTRY : {
                           deposit.setCountry(Country.getCounty(getXMLText(reader)));
                        } break;
                        case DEPOSITOR : {
                           deposit.setDepositor(getXMLText(reader));
                        } break;
                        case AMOUNT_ON_DEPOSIT : {
                           deposit.setAmount(Double.parseDouble(getXMLText(reader)));
                        } break;
                        case PROFITABILITY : {
                           deposit.setProfitability(Float.parseFloat(getXMLText(reader)));
                        } break;
                        case IRREDUCIBLE_BALANCE : {
                           CheckinDeposit checkinDeposit = (CheckinDeposit)deposit;
                           checkinDeposit.setIrreducibleBalance(Double.parseDouble(getXMLText(reader)));
                        } break;
                        case DATE_UNTIL_REPLENISHMENT_ALLOWED : {
                           AccumulationDeposit accumulationDeposit = (AccumulationDeposit)deposit;
                           accumulationDeposit.setDateUntilReplenishmentAllowed(LocalDate.parse(getXMLText(reader)));
                        } break;
                        case TIME_CONSTRAINTS : {
                           AccumulationDeposit accumulationDeposit = (AccumulationDeposit)deposit;
                           accumulationDeposit.setTimeConstraints(LocalDate.parse(getXMLText(reader)));
                        } break;
                        case METALS : {
                           MetalDeposit metalDeposit = (MetalDeposit)deposit;
                           metalDeposit.setMetals(getXMLMetals(reader));
                        } break;
                    }
                } break;
                case XMLStreamConstants.END_ELEMENT : {
                    name = reader.getLocalName();
                    DepositXmlTag tag = DepositXmlTag.getDepositXmlTag(name);
                    if (DepositXmlTag.ACCUMULATION_DEPOSIT == tag || 
                        DepositXmlTag.CHECKIN_DEPOSIT      == tag || 
                        DepositXmlTag.EXPRESS_DEPOSIT      == tag ||
                        DepositXmlTag.ON_DEMAND_DEPOSIT    == tag ||
                        DepositXmlTag.SAVING_DEPOSIT       == tag ||
                        DepositXmlTag.METAL_DEPOSIT        == tag) {
                        return deposit;
                    }
                }
            }
        }
        throw new ParsingXMLException("End tag of " + deposit.getClass().getSimpleName() + " is not found.");     
    }
  
    private List<Metal> getXMLMetals(XMLStreamReader reader) throws XMLStreamException, ParsingXMLException {
        List<Metal> metals = new ArrayList<>();
        int type;
        String name;
        while (reader.hasNext()) {
            type = reader.next();
            switch (type) {
                case XMLStreamConstants.START_ELEMENT : {
                    name = reader.getLocalName();
                    DepositXmlTag tag = DepositXmlTag.getDepositXmlTag(name);
                    if (DepositXmlTag.METAL == tag) {
                        Metal ingredient = getXMLMetal(reader);
                        metals.add(ingredient);
                    }
                } break;
                case XMLStreamConstants.END_ELEMENT : {
                    name = reader.getLocalName();
                    DepositXmlTag tag = DepositXmlTag.getDepositXmlTag(name);
                    if (DepositXmlTag.METALS == tag) {
                        return metals;
                    }
                } break;
            }
        }
        throw new XMLStreamException("End tag of the Metals is not found");
    }
    
    private Metal getXMLMetal(XMLStreamReader reader) throws XMLStreamException, ParsingXMLException {
        Metal metal = new Metal();
        int type;
        String name;
        while (reader.hasNext()) {
            type = reader.next();
            switch (type) {
                case XMLStreamConstants.START_ELEMENT : {
                    name = reader.getLocalName();
                    DepositXmlTag tag = DepositXmlTag.getDepositXmlTag(name);
                    switch (tag) {
                        case METAL_NOMENCLATURE : {
                            metal.setNomencalture(MetalNomenclature.getMetalNomenclature(getXMLText(reader)));
                        } break;
                        
                        case WEIGHT : {
                            metal.setWeight(Double.parseDouble(getXMLText(reader)));
                        } break;
                        
                        case METAL_PURCHASE_RATE : {
                            metal.setRate(Double.parseDouble(getXMLText(reader)));
                        } break;
                    }
                } break;
                case XMLStreamConstants.END_ELEMENT : {
                    name = reader.getLocalName();
                    DepositXmlTag tag = DepositXmlTag.getDepositXmlTag(name);
                    if (DepositXmlTag.METAL == tag) {
                        return metal;
                    }
                } break;
            }
        }
        throw new XMLStreamException("End tag of a Metal is not found");
    }
    
    private String getXMLText(XMLStreamReader reader) throws XMLStreamException {
        String text = null;
        if (reader.hasNext()) {
            reader.next();
            text = reader.getText();
        }
        return text;
    }
    
    
}

