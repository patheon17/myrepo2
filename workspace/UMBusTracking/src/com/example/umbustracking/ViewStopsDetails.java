package com.example.umbustracking;

import java.io.IOException;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.database.SQLException;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.view.Menu;
import android.view.View;

public class ViewStopsDetails extends Activity {
	private DBaccess access;
	Long id;
	Long routeid;
	MySQLiteHelper myDbHelper;
	Comment thisstation;
	String nextStation;
	String prevStation;
	
	TextView txtProduct;
	 TextView routename ;
	 TextView desc;
	  ImageView Image ;
	  TextView nextstat;
		 TextView prevstat ;
		 TextView routefrom;
		 TextView routeto;
		 Button button1;
		 Button button2;
		 List<Comment> allstops;
		 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.single_list_item_view);
		
		try{
		access = new DBaccess();
		 myDbHelper = new MySQLiteHelper(this);
		}
		catch(Exception e){
			
		}
		//try {
			 
	 //      	myDbHelper.createDataBase();
	 
	 //	} catch (IOException ioe) {
	 
	 	//	throw new Error("Unable to create database");
	 
	// 	}
	 
	 //	try {
	 
	 //		myDbHelper.openDataBase();
	 
	 //	}catch(SQLException sqle){
	 
	 	//	throw sqle;
	 
	// }
		try{
	 txtProduct = (TextView)findViewById(R.id.viewstoptitle);
		 routename = (TextView)findViewById(R.id.routename);
		 desc = (TextView)findViewById(R.id.description);
		   Image =(ImageView)findViewById(R.id.imageView1);
		  nextstat = (TextView)findViewById(R.id.nextstation);
			 prevstat = (TextView)findViewById(R.id.nextstation1);
			  routefrom = (TextView)findViewById(R.id.routefrom);
			 routeto= (TextView)findViewById(R.id.routeto11);
			 button1= (Button)findViewById(R.id.button1);
			 button2= (Button)findViewById(R.id.button2);
		}
		catch(Exception e){
			
		}
			 
			 
		try{
	        Intent i = getIntent();
	        // getting attached intent data
	        String s=i.getStringExtra("id1");
	        id = Long.parseLong(s);
	        String s1=i.getStringExtra("routeid");
	        routeid = Long.parseLong(s1);
	        
	        // displaying selected product name
		}
		catch(Exception e){
			
		}
	        try{
	         allstops =access.getAllstops(myDbHelper.getReadableDatabase(),routeid);
    		access.close();
    		//System.out.println(allstops.size());
    		
    		if(id==allstops.get(0).getId()||id==allstops.get(allstops.size()-1).getId()){
    			if(id==allstops.get(0).getId()){
    				thisstation=allstops.get(0);
    				nextStation=allstops.get(1).getname();
    				prevStation=allstops.get(allstops.size()-1).getname();
    				
    			}
    			
    			if(id==allstops.get(allstops.size()-1).getId()){
    				thisstation=allstops.get(allstops.size()-1);
    				nextStation=allstops.get(0).getname();
    				prevStation=allstops.get(allstops.size()-2).getname();
    			}
    		}
    		
    		else{
    		
    		for(int i=0;i<allstops.size();i++){
    			if(allstops.get(i).getId()==id){
    				thisstation=allstops.get(i);
    				nextStation=allstops.get(i+1).getname();
    				prevStation=allstops.get(i-1).getname();
    				break;
    			}
    		}
    			
    		}
    		
    		txtProduct.setText(thisstation.getname());
    		routefrom.setText("To "+allstops.get(allstops.size()-1));
    		routeto.setText("To "+allstops.get(0));
    		
    		nextstat.append(nextStation);
    		prevstat.append(prevStation);
    		
    		
    		
               button1.setOnClickListener(new View.OnClickListener() {
    			
    			
    			
    			
    			
    			@Override
    			public void onClick(View v) {
    				
    				Intent intent3 = new Intent(getBaseContext(),DisplayBusses.class);
    	            intent3.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
    	            //intent2.putExtra("coord", UM);
    	           // this.getIntent()
    	            intent3.putExtra("routeid", Integer.parseInt(String.valueOf(routeid)));
    	            intent3.putExtra("dir", "last");
    	            intent3.putExtra("fromto", allstops.get(allstops.size()-1).getname());
    	            intent3.putExtra("stationid",Integer.parseInt(String.valueOf(id)));
    	            startActivity(intent3);
    	           // return true;
    				
    			}
    			}
    		);
    		
               
               
               button2.setOnClickListener(new View.OnClickListener() {
       			
       			
       			
       			
       			
       			@Override
       			public void onClick(View v) {
       				
       				Intent intent3 = new Intent(getBaseContext(),DisplayBusses.class);
       	            intent3.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
       	            //intent2.putExtra("coord", UM);
       	           // this.getIntent()
       	         intent3.putExtra("fromto", allstops.get(0).getname());
       	            intent3.putExtra("routeid", Integer.parseInt(String.valueOf(routeid)));
       	            intent3.putExtra("dir", "first");
       	            intent3.putExtra("stationid",Integer.parseInt(String.valueOf(id)));
       	            startActivity(intent3);
       	           // return true;
       				
       			}
       			}
       		);
    		
    		
    		
    		
    		
    		
    		switch(thisstation.getrouteid().intValue()){
    		case 1: routename.setText("Route A");
    		break;
    		case 2: routename.setText("Route B");
    		break;
    		case 3: routename.setText("Route C");
    		break;
    		case 4: routename.setText("Route D");
    		break;
    		case 5: routename.setText("Route E");
    		break;
    		default:routename.setText("Error");
    		break;
    		}
    		
    		try{
    		desc.setText(thisstation.getdesc());
    		String uri=thisstation.getImage();
    		int imgrsc= getApplicationContext().getResources().getIdentifier(uri, null, getApplicationContext().getPackageName());
    		Drawable image= getResources().getDrawable(imgrsc);
    		Image.setImageDrawable(image);
    		
    		}
    		catch(Exception e){
    			
    		}
    		
    		
    		}
	        catch(Exception e){
	        	
	        }
	        
	        
	
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.view_stops_details, menu);
		return true;
	
		
	
	}
	
	

}
