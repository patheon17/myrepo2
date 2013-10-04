package com.example.umbustracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.database.SQLException;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.SimpleAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class InfoActivity2 extends Activity {
private DBaccess access;

ProgressDialog pDialog;
List<Comment> values;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_test);
		
		
		//ListView lv = getListView();
		//loaddata1.execute();
		
		
	access = new DBaccess();
		//access.open();
		
		

        MySQLiteHelper myDbHelper = new MySQLiteHelper(this);
      //  myDbHelper = new MySQLiteHelper(this);
 
      /*  try {
 
        	myDbHelper.createDataBase();
 
 	} catch (IOException ioe) {
 
 		throw new Error("Unable to create database");
 
 	}
 
 	try {
 
 		myDbHelper.openDataBase();
 
 	}catch(SQLException sqle){
 
 		throw sqle;
 
 	}
 	
 	
 	
		try{
        Intent i = getIntent();
        // getting attached intent data
       long id = i.getLongExtra("id", 0);
        		
		List<Comment> values =access.getAllstops(myDbHelper.getReadableDatabase(), id);

		ArrayAdapter<Comment> adapter = new ArrayAdapter<Comment>(this,android.R.layout.simple_list_item_1, values);
		    setListAdapter(adapter);
		}
		catch(Exception e){
			
		}
		//System.out.println("test");
	//	Toast.makeText(getBaseContext(), "failedx2" ,Toast.LENGTH_SHORT).show();
	}

//	@Override
/*	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.mainmenu, menu);
		return true;
	}
	
	
	public boolean onOptionsItemSelected(MenuItem item) {
	    switch (item.getItemId()) {
	    case R.id.map:
	    	Intent intent1 = new Intent(this,MainActivity.class);
            intent1.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent1);
            return true;
	    	 
	    case R.id.info:
	    	Toast.makeText(getBaseContext(), "You are already at the Info Activity" ,Toast.LENGTH_SHORT).show();

	    default:
	      break;
	    }

	    return true;
	  }
	*/
	}
}