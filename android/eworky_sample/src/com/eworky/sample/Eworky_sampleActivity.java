package com.eworky.sample;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.util.Log;
import android.view.Gravity;
import android.os.AsyncTask;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.Intent;


public class Eworky_sampleActivity extends Activity {
	
	private static final String EMPTY_STRING = "";
    
    private EditText searchEditText;
    private TextView searchTypeTextView;
    private Button searchButton;
    
	private  GenericSeeker<Localisation> localisationSeeker = new LocalisationSeeker();
	
	private ProgressDialog progressDialog;
    
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        try {
	        this.findAllViewsById();
	        
	        searchButton.setOnClickListener(buttonListener);	        
	        searchEditText.setOnFocusChangeListener(new DftTextOnFocusListener(getString(R.string.search)));
        }
        catch(Exception e ){
        	Log.w(getClass().getSimpleName(), "Error for URL ", e);
        }
    }
    
    private OnClickListener buttonListener = new OnClickListener() {
        public void onClick(View v) {
        	String query = searchEditText.getText().toString();
            //longToast("search for : " + query);
        	performSearch("?place="+query);
        }
    };
    
    private void findAllViewsById() {
        searchEditText = (EditText) findViewById(R.id.search_edit_text);
        searchTypeTextView = (TextView) findViewById(R.id.search_type_text_view);
        searchButton = (Button) findViewById(R.id.search_button);
    }
    
    public void longToast(CharSequence message) {
    	Toast msg = Toast.makeText(this, message, Toast.LENGTH_LONG);
    	msg.setGravity(Gravity.TOP|Gravity.LEFT, 0, 0);
    	msg.show();
    }
    
    
    private class DftTextOnFocusListener implements OnFocusChangeListener {
        
        private String defaultText;

        public DftTextOnFocusListener(String defaultText) {
            this.defaultText = defaultText;
        }

        public void onFocusChange(View v, boolean hasFocus) {
            if (v instanceof EditText) {
                EditText focusedEditText = (EditText) v;
                // handle obtaining focus
                if (hasFocus) {
                    if (focusedEditText.getText().toString().equals(defaultText)) {
                        focusedEditText.setText(EMPTY_STRING);
                    }
                }
                // handle losing focus
                else {
                    if (focusedEditText.getText().toString().equals(EMPTY_STRING)) {
                        focusedEditText.setText(defaultText);
                    }
                }
            }
        }
        
    }
    
    private class CancelTaskOnCancelListener implements OnCancelListener {
    	private AsyncTask<?, ?, ?> task;
    	public CancelTaskOnCancelListener(AsyncTask<?, ?, ?> task) {
    		this.task = task;
    	}
    	//@Override
		public void onCancel(DialogInterface dialog) {
    		if (task!=null) {
        		task.cancel(true);
        	}
		}
    }
    
    private void performSearch(String query) {
        
    	progressDialog = ProgressDialog.show(Eworky_sampleActivity.this,
        		"Please wait...", "Retrieving data...", true, true);
    	
    	PerformLocalisationSearchTask task = new PerformLocalisationSearchTask();
    	task.execute(query);
    	progressDialog.setOnCancelListener(new CancelTaskOnCancelListener(task));
        
    }

    private class PerformLocalisationSearchTask extends AsyncTask<String, Void, ArrayList<Localisation>> {

		@Override
		protected ArrayList<Localisation> doInBackground(String... params) {
			String query = params[0];
			return localisationSeeker.find(query);
		}
		
		@Override
		protected void onPostExecute(final ArrayList<Localisation> result) {			
			runOnUiThread(new Runnable() {
		    	//@Override
		    	public void run() {
		    		if (progressDialog!=null) {
		    			progressDialog.dismiss();
		    			progressDialog = null;
		    		}
		    		Intent intent = new Intent(Eworky_sampleActivity.this, LocalisationListActivity.class);
    				intent.putExtra("localisations",result);
		    		startActivity(intent);
		    	}
		    });
		}
		
	}
}