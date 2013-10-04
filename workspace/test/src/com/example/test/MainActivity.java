package com.example.test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

//import com.example.umbustracking.Comment;
//import com.example.umbustracking.DBaccess;
//import com.example.umbustracking.MainActivity;
//import com.example.umbustracking.MySQLiteHelper;
//import com.example.umbustracking.R;



import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.database.SQLException;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;
import android.view.View;

public class MainActivity extends ListActivity {
private DBaccess access;
loaddata loaddata1=new loaddata();
ProgressDialog pDialog;
List<Comment> values;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		
		ListView lv = getListView();
		//loaddata1.execute();
		
		
	access = new DBaccess();
		//access.open();
		
		

        MySQLiteHelper myDbHelper = new MySQLiteHelper(this);
      //  myDbHelper = new MySQLiteHelper(this);
 
        try {
 
        	myDbHelper.createDataBase();
 
 	} catch (IOException ioe) {
 
 		throw new Error("Unable to create database");
 
 	}
 
 	try {
 
 		myDbHelper.openDataBase();
 
 	}catch(SQLException sqle){
 
 		throw sqle;
 
 	}
 	
 	
 	
		
		
		List<Comment> values =access.getAllComments(myDbHelper.getReadableDatabase());

		ArrayAdapter<Comment> adapter = new ArrayAdapter<Comment>(this,android.R.layout.simple_list_item_1, values);
		    setListAdapter(adapter);
		//System.out.println("test");
	//	Toast.makeText(getBaseContext(), "failedx2" ,Toast.LENGTH_SHORT).show();
	}


	
	
	
	class loaddata extends AsyncTask<String, String,String>{

		ArrayList<HashMap<String, String>> productsList=new ArrayList<HashMap<String, String>>();
		
		
		/*protected void onPreExecute(){

		super.onPreExecute();
		
		pDialog=new ProgressDialog(MainActivity.this);
		pDialog.setMessage("Loading GPS coordinates. Please wait.....");
		pDialog.setIndeterminate(false);
		pDialog.setCancelable(false);
		pDialog.show();
		}
*/
		protected String doInBackground(String... Args){
         
			try{
			//access = new DBaccess(getBaseContext());
			//access.open();
			
			//values =access.getAllComments();
			
		/*	ArrayList<HashMap<String, String>> productsList;
		productsList = new ArrayList<HashMap<String, String>>();
			
			
			 
	        // adding each child node to HashMap key => value
			//String[] products = new String[]{"samsung", "sony", "htc","LG","Motorola","asus","acer"};
			
			if(values.isEmpty()){
				System.out.println("valuesempty");
				//Toast.makeText(getBaseContext(), "failedx2" ,Toast.LENGTH_SHORT).show();
			}
			//else{
			for (int i = 0; i < values.size(); i++) {
				HashMap<String, String> map = new HashMap<String, String>();
				map.put("id", values.get(i).getId());
		        map.put("manufacturer", values.get(i).getManufacturer());
		       // map.put("model", values.get(i).getModel());
		        map.put("year", values.get(i).getYear());
		        productsList.add(map);
						
			}
			if(productsList.isEmpty()){
				System.out.println("prductlistempty");
				//Toast.makeText(getBaseContext(), "failedx2" ,Toast.LENGTH_SHORT).show();
			}
			else{
				System.out.println("prductlistisnotempty");
			}
			
			System.out.println(productsList.size());
			//System.out.println("empty");
			 * 
			 * */
			 
		//	}
			//Toast.makeText(getBaseContext(), "failedx2" ,Toast.LENGTH_LONG).show();
			
			try{
				

				
				/*	ListAdapter adapter = new SimpleAdapter(
			                MainActivity.this, productsList,
			                R.layout.list_item, new String[] { "id",
			                        "manufacturer","year"},
			                new int[] { R.id.pid, R.id.manufacturer,R.id.year});
			        // updating listview
			        setListAdapter(adapter);
				*/
				
			//	ArrayAdapter<Comment> adapter = new ArrayAdapter<Comment>(this,android.R.layout.simple_list_item_2, values);
				//    setListAdapter(adapter);
				    
			        System.out.println("success");
	       			}
			catch(Exception e){
				//Toast.makeText(getBaseContext(), "failedx2" ,Toast.LENGTH_SHORT).show();
				System.out.println(e.getMessage());
			}
			
	        
			}
			catch(Exception e){
			//Toast.makeText(getBaseContext(), "failed" ,Toast.LENGTH_SHORT).show();
			}

	        // adding HashList to ArrayList
	       
			
			
	
		
		return null;
		
		}

		protected void onPostExecute(String file_url){
		//	ArrayAdapter<Comment> adapter = new ArrayAdapter<Comment>(this,android.R.layout.simple_list_item_2, values);
		//	 pDialog.dismiss();

			
		//set marker to longitude and latitude;

		}
	
	
	
	}

}