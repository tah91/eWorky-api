package com.eworky.sample;

import java.io.StringReader;
import java.util.ArrayList;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

public class XmlParser {
    private XMLReader initializeReader() throws ParserConfigurationException, SAXException {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        // create a parser
        SAXParser parser = factory.newSAXParser();
        // create the reader (scanner)
        XMLReader xmlreader = parser.getXMLReader();
        return xmlreader;
    }
    
    public ArrayList<Localisation> parseLocalisationResponse(String xml) {
        
        try {
            
            XMLReader xmlreader = initializeReader();
            
            LocalisationHandler localisationHandler = new LocalisationHandler();

            // assign our handler
            xmlreader.setContentHandler(localisationHandler);
            // perform the synchronous parse
            xmlreader.parse(new InputSource(new StringReader(xml)));
            
            return localisationHandler.retrieveLocalisationList();
            
        } 
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        
    }
}
