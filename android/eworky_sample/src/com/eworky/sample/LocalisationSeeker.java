package com.eworky.sample;

import java.util.ArrayList;
import android.util.Log;

public class LocalisationSeeker extends GenericSeeker<Localisation> {

	private static final String LOCALISATION_SEARCH_PATH = "localisation/search";

	
	public LocalisationSeeker() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public ArrayList<Localisation> find(String query) {
		ArrayList<Localisation> localisationsList = retrieveLocalisationsList(query);
        return localisationsList;
	}

	@Override
	public ArrayList<Localisation> find(String query, int maxResults) {
		ArrayList<Localisation> localisationsList = retrieveLocalisationsList(query);
        return retrieveFirstResults(localisationsList, maxResults);
	}

	@Override
	public String retrieveSearchMethodPath() {
		return LOCALISATION_SEARCH_PATH;
	}
	
	private ArrayList<Localisation> retrieveLocalisationsList(String query) {
        String url = constructSearchUrl(query);
        String response = httpRetriever.retrieve(url);
        Log.d(getClass().getSimpleName(), response);
        return xmlParser.parseLocalisationResponse(response);
    }

}
