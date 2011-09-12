package com.eworky.sample;

import android.app.ListActivity;
import java.util.ArrayList;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class LocalisationListActivity extends ListActivity {

	public LocalisationListActivity() {
		// TODO Auto-generated constructor stub
	}
    
    private ArrayList<Localisation> localisationList;
    private ArrayAdapter<Localisation> localisationAdapter;
    
    @SuppressWarnings("unchecked")
    @Override
    public void onCreate(Bundle savedInstanceState) {
        
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loc_list);
        
        localisationList = (ArrayList<Localisation>) getIntent().getSerializableExtra("localisations");
        
        localisationAdapter = new ArrayAdapter<Localisation>(this, android.R.layout.simple_list_item_1, localisationList);
        
        setListAdapter(localisationAdapter);
        
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        
        super.onListItemClick(l, v, position, id);
        Localisation localisation = localisationAdapter.getItem(position);
        
        String url = localisation.url;
        
        Intent locIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));                
        startActivity(locIntent);
        
    }
    
    public void longToast(CharSequence message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }
}
