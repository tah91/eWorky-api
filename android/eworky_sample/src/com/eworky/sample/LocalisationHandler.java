package com.eworky.sample;

import org.xml.sax.helpers.DefaultHandler;
import java.util.ArrayList;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

public class LocalisationHandler extends DefaultHandler {
    private StringBuffer buffer = new StringBuffer();
    
    private ArrayList<Localisation> localisationList;
    private Localisation localisation;
    
    @Override
    public void startElement(String namespaceURI, String localName, String qName, Attributes atts) throws SAXException {
        
        buffer.setLength(0);
        
        if (localName.equals("response")) {
        	localisationList = new ArrayList<Localisation>();
        }
        else if (localName.equals("LocalisationJson")) {
        	localisation = new Localisation();
        }
    }
    
    @Override
    public void endElement(String uri, String localName, String qName)throws SAXException {
        
    	if (localName.equals("LocalisationJson")) {
    		localisationList.add(localisation);
        }
    	else if (localName.equals("id")) {
        	localisation.id = Integer.parseInt(buffer.toString());
        }
        else if (localName.equals("name")) {
        	localisation.name = buffer.toString();
        }
        else if (localName.equals("description")) {
        	localisation.description = buffer.toString();
        } 
        else if (localName.equals("image")) {
        	localisation.image = buffer.toString();
        } 
        else if (localName.equals("address")) {
        	localisation.address = buffer.toString();
        }
        else if (localName.equals("city")) {
        	localisation.city = buffer.toString();
        } 
        else if (localName.equals("type")) {
        	localisation.type = buffer.toString();
        } 
        else if (localName.equals("url")) {
        	localisation.url = buffer.toString();
        } 
        else if (localName.equals("latitude")) {
        	localisation.latitude = Double.parseDouble(buffer.toString());
        } 
        else if (localName.equals("longitude")) {
        	localisation.longitude = Double.parseDouble(buffer.toString());
        } 
        else if (localName.equals("rating")) {
        	localisation.rating = Double.parseDouble(buffer.toString());
        } 
    }
    
    @Override
    public void characters(char[] ch, int start, int length) {
        buffer.append(ch, start, length);
    }
        
    public ArrayList<Localisation> retrieveLocalisationList() {
        return localisationList;
    }
}
