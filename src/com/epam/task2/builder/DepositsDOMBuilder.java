/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epam.task2.builder;

import java.net.URL;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 *
 * @author Lena
 */
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
            createTariffs(root, DeopsitXmlTag.CALLING_TARIFF);
            createTariffs(root, DepositXmlTag.INTERNET_TARIFF);
        } catch (IOException | SAXException e) {
            logger.error("Error while reading", e);
        }
    }
    
}
