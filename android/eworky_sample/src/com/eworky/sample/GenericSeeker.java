package com.eworky.sample;

import java.util.ArrayList;

public abstract class GenericSeeker<E> {
    protected static final String BASE_URL = "http://www.eworky.com/api/";    
    protected static final String LANGUAGE_PATH = "";//"en/";
    protected static final String XML_FORMAT = "";//"xml/";
    protected static final String API_KEY = "";//"<YOUR_API_KEY_HERE>";
    protected static final String SLASH = "/";
    
    protected HttpRetriever httpRetriever = new HttpRetriever();
    protected XmlParser xmlParser = new XmlParser();
    
    public abstract ArrayList<E> find(String query);
    public abstract ArrayList<E> find(String query, int maxResults);

    public abstract String retrieveSearchMethodPath();
    
    protected String constructSearchUrl(String query) {
        StringBuffer sb = new StringBuffer();
        sb.append(BASE_URL);
        sb.append(retrieveSearchMethodPath());
        sb.append(LANGUAGE_PATH);
        sb.append(XML_FORMAT);
        sb.append(API_KEY);
        sb.append(SLASH);
        //sb.append(URLEncoder.encode(query));
        sb.append(query);
        return sb.toString();
    }
    
    public ArrayList<E> retrieveFirstResults(ArrayList<E> list, int maxResults) {
        ArrayList<E> newList = new ArrayList<E>();
        int count = Math.min(list.size(), maxResults);
        for (int i=0; i<count; i++) {
            newList.add(list.get(i));
        }
        return newList;
    }
}
