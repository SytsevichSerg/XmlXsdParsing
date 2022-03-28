
package com.epam.task2.builder;
import com.epam.task2.entity.AccumulationDeposit;
import com.epam.task2.entity.CheckinDeposit;
import com.epam.task2.entity.Deposit;
import com.epam.task2.entity.OnDemandDeposit;
import com.epam.task2.handler.DepositXmlTag;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
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
    private XMLStreamReader reader;
    
    
    public DepositsSTAXBuilder(){
        
        super();
        inputFactory = XMLInputFactory.newInstance();
        loader = getClass().getClassLoader();
    }
   
    @Override
    public void buildSetDeposits(String fileName) {
        URL resource = loader.getResource(fileName);
        String name;
        
        try (FileInputStream inputStream = new FileInputStream(resource.getFile())) {
            reader = inputFactory.createXMLStreamReader(inputStream);
            while (reader.hasNext()) {
                int type = reader.next();
                if (type == XMLStreamConstants.START_ELEMENT) {
                    name = reader.getLocalName();
                    if (name.equals(DepositXmlTag.ON_DEMAND_DEPOSIT.getName())
                            || name.equals(DepositXmlTag.EXPRESS_DEPOSIT.getName())
                            || name.equals(DepositXmlTag.CHECKIN_DEPOSIT.getName())
                            || name.equals(DepositXmlTag.ACCUMULATION_DEPOSIT.getName())
                            || name.equals(DepositXmlTag.SAVING_DEPOSIT.getName())
                            || name.equals(DepositXmlTag.METAL_DEPOSIT.getName())) {
                        Deposit deposit = buildDeposit();
                        deposits.add(deposit);
                    }
                }

            }
        } catch (IOException | XMLStreamException e) {
            LOG.error("stax error", e);
        }
    }
    
    private Deposit buildDeposit() throws XMLStreamException {
        String currentTag = reader.getLocalName();
        Deposit currentDeposit = null;
        
         if(currentTag.equals(DepositXmlTag.ON_DEMAND_DEPOSIT.getName())){
            currentDeposit = new OnDemandDeposit();
        } else if (currentTag.equals(DepositXmlTag.ACCUMULATION_DEPOSIT.getName())){
            currentDeposit = new AccumulationDeposit();
        } else if (currentTag.equals(DepositXmlTag.CHECKIN_DEPOSIT.getName())){
             currentDeposit = new CheckinDeposit();
        } else if (ELEMENT_EXPRESS_DEPOSIT.equals(qName)) {
            currentExpressDeposit = new ExpressDeposit();
            currentDeposit = currentExpressDeposit;//fixme warning
        } else if (ELEMENT_SAVING_DEPOSIT.equals(qName)) {
            currentSavingDeposit = new SavingDeposit();
            currentDeposit = currentSavingDeposit;//fixme warning
        }  else if (ELEMENT_METAL_DEPOSIT.equals(qName)) {
            currentMetalDeposit = new MetalDeposit();
            currentDeposit = currentMetalDeposit;//fixme warning
        } else {
            DepositXmlTag temp = DepositXmlTag.getDepositXmlTag(qName);
            if (withText.contains(temp)) {
                currentXmlTag = temp;
            }
        }
        final String onDenamdDepositName = DepositXmlTag.ON_DEMAND_DEPOSIT.getName();
               
         switch (currentTag){
             case onDenamdDepositName :
                 currenDtDeposit= new OnDemandDeposit();
                 break;
                 case DepositXmlTag.CHECKIN_DEPOSIT.getName() :
                 currentDeposit= new CheckinDeposit();
                 break;
                 
            }   
            
         throw new XMLStreamException("unknown tag");   
    }
        
        
    
}
