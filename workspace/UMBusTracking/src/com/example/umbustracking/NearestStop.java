package com.example.umbustracking;

import java.io.BufferedReader;
import java.io.InputStream;

import java.io.InputStreamReader;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import android.location.Location;
import android.net.ParseException;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class NearestStop extends Activity {

	LatLng a= new LatLng(3.12147, 101.65349);
	LatLng b= new LatLng(3.11804, 101.65855);
	LatLng c= new LatLng(3.11879, 101.65109);
	nearestBus near = new nearestBus();
	List<LatLng> test = new ArrayList<LatLng>();
	
	private DBaccess access;
	MySQLiteHelper myDbHelper;
	
	JSONArray jArray;
	String result = null;
	InputStream is = null;
	ProgressDialog pDialog;
	StringBuilder sb=null;
	ArrayList<Marker> markers = new ArrayList<Marker>();
	List<LatLng> busstops= new ArrayList<LatLng>();
	List<LatLng> buslist2 = new ArrayList<LatLng>();
	//Loadallbus load= new Loadallbus();
	LatLng currentlocation;
	String d="error";
	int latlongpost=0;
	int currentbusloc=0;
	String t="error";
	calcDistance calcstop;
	calcDistance calcbus;
	LatLng nearestbus;
	LatLng currentbusstop;
	List<Comment> allstops;
	Button button1;
	int stationid;
	int routeid;
	Comment currstop;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_nearest_stop);
		
		Intent i = getIntent();
        // getting attached intent data
       
       currentlocation=new LatLng(i.getDoubleExtra("lat",0),i.getDoubleExtra("long", 0));
       
       //load.execute();
      try
      {access = new DBaccess();
		//access.open();
      
	     myDbHelper = new MySQLiteHelper(this);
      }
      catch(Exception e){
    	  
      }
      
      
      try{
		 allstops =access.getAllstops(myDbHelper.getReadableDatabase(),-1);
  		access.close();
  		LatLng stoplat;
  		for(int j=0;j<allstops.size();j++){
  			stoplat=new LatLng(allstops.get(j).getlatitude(),allstops.get(j).getlongitude());
  			busstops.add(stoplat);
  		}
		}
		
		catch(Exception e){
			
		}
       buslist2=MainActivity.buslist3;
      
       test.add(a);
       test.add(b);
       test.add(c);
       
       TextView display = (TextView)findViewById(R.id.displaystop);
       calcstop = new calcDistance();
       display.setText(Float.toString(calcstop.distance(currentlocation,busstops))+" m away");
       
       //near.execute();
       
       if(busstops.isEmpty()){
       }
       else{
      // System.out.println(busstops.size());
       }
       
       
       
      
       try{
    	   
    		TextView displayroute = (TextView)findViewById(R.id.routed);
    	    TextView displaystop = (TextView)findViewById(R.id.stops);
    	   // TextView displaykm = (TextView)findViewById(R.id.kmaway);
    	   // TextView displaymin = (TextView)findViewById(R.id.minaway);
    	  button1= (Button)findViewById(R.id.button1);
          // displaykm.setText(d);
        //   displaymin.setText(t);
    	  currstop=allstops.get(calcstop.getLocator());
    	    double d=currstop.getrouteid();
    	    routeid=(int)d;
    	    if(d==1){
    	    	 displayroute.setText("Route A");
    	    }
    	    else if(d==2){
    	    	displayroute.setText("Route B");
    	    }
    	    else if(d==2){
    	    	displayroute.setText("Route C");
    	    }
    	    else if(d==2){
    	    	displayroute.setText("Route D");
    	    }
    	    else if(d==2){
    	    	displayroute.setText("Route E");
    	    }
    	    else {
    	    	displayroute.setText("No Route");
    	    }
    	   
    	    
    	    button1.setOnClickListener(new View.OnClickListener() {
    			
    			
    			
    			
    			
    			@Override
    			public void onClick(View v) {
    				
    				Intent intent3 = new Intent(getBaseContext(),ViewStopsDetails.class);
    	            intent3.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
    	            //intent2.putExtra("coord", UM);
    	           // this.getIntent()
    	            intent3.putExtra("routeid", String.valueOf(routeid));
    	           // intent3.putExtra("dir", "last");
    	            intent3.putExtra("id1",String.valueOf(currstop.getId().intValue()));
    	            startActivity(intent3);
    	           // return true;
    				
    			}
    			}
    		);
    	    
    	    
    	
            displaystop.setText(currstop.getname());
            currentbusstop= new LatLng(currstop.getlatitude(),allstops.get(calcstop.getLocator()).getlongitude());
           // calcbus= new calcDistance();
          //  calcbus.distance(currentbusstop,buslist2);
           // nearestbus =new LatLng(buslist2.get(calcbus.getLocator()).latitude,buslist2.get(calcbus.getLocator()).longitude);
           // near.execute();
           
       
       }
       catch(Exception e){
    	   
       }
       
       
       
       if(buslist2.isEmpty()){
    	   Toast.makeText(getBaseContext(), "bus list is empty" ,Toast.LENGTH_SHORT).show();
       }
       else{
    	   Toast.makeText(getBaseContext(), "YESSSSS" ,Toast.LENGTH_SHORT).show();
       }
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.mainmenu, menu);
		return true;
	}
	
	
	
	
	
	public boolean onOptionsItemSelected(MenuItem item) {
	    switch (item.getItemId()) {
	    case R.id.map:
	    	Intent intent2 = new Intent(this,MainActivity.class);
            intent2.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
            //intent2.putExtra("coord", UM);
           // this.getIntent()
            startActivity(intent2);
            return true;
            
	    	
	    case R.id.info:
	    	Intent intent1 = new Intent(this,InfoActivity.class);
            intent1.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
           // this.getIntent()
            startActivity(intent1);
            return true;
	    case R.id.nearest:
	    	 Toast.makeText(getBaseContext(), "You are already at the Nearest Stop Activity" ,Toast.LENGTH_SHORT).show();
		    	break;

	    default:
	      break;
	    }

	    return true;
	  }
	
	
	
	
	
	/*class Loadallbus extends AsyncTask<String,String,String>{

		protected void onPreExecute(){

	super.onPreExecute();
		
		pDialog=new ProgressDialog(NearestStop.this);
		pDialog.setMessage("Loading Nearest Bus Station coordinates. Please wait.....");
		pDialog.setIndeterminate(false);
		pDialog.setCancelable(false);
		pDialog.show();
		// mMap.clear();
		
		}

		protected String doInBackground(String... Args){
			 
			ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
		try{
			
			
			 final HttpParams httpParams = new BasicHttpParams();
             HttpConnectionParams.setConnectionTimeout(httpParams, 3000);
            // HttpConnectionParams.setSoTimeout(httpParams, 3000);
		     HttpClient httpclient = new DefaultHttpClient(httpParams);
		    
		     HttpPost httppost = new HttpPost("http://testingecomum.site50.net/test.php");
		     httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
		     
		     HttpResponse response = httpclient.execute(httppost);
		     HttpEntity entity = response.getEntity();
		     is = entity.getContent();
		     
		     }
		
		
		catch(ConnectException e){
			
		        // Log.e("log_tag", "Error in http connection"+e.toString());
		        Toast.makeText(getBaseContext(), "Cant connect to database 1" ,Toast.LENGTH_LONG).show();
		        pDialog.dismiss();
			
			cancel(true);
		    }
		catch(ConnectTimeoutException e){
			
		       // Log.e("log_tag", "Error in http connection"+e.toString());
		        Toast.makeText(getBaseContext(), "Cant connect to database 2" ,Toast.LENGTH_LONG).show();
		        pDialog.dismiss();
			
			cancel(true);
		}
		
		
		catch (Exception e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
			Toast.makeText(getBaseContext(), "Cant connect to database 3" ,Toast.LENGTH_LONG).show();
			pDialog.dismiss();
			cancel(true);
			}
		
		 
		
		try{
		      BufferedReader reader = new BufferedReader(new InputStreamReader(is,"iso-8859-1"),8);
		       sb = new StringBuilder();
		       sb.append(reader.readLine() + "\n");

		       String line="0";
		       while ((line = reader.readLine()) != null) {
		                      sb.append(line + "\n");
		        }
		        is.close();
		        result=sb.toString();
		        }catch(Exception e){
		              Log.e("log_tag", "Error converting result "+e.toString());
		             Toast.makeText(getBaseContext(), "Cannot retrieve results" ,Toast.LENGTH_SHORT).show();
		        }
		
	//if(result==null){
		//	Toast.makeText(getBaseContext(), "empty" ,Toast.LENGTH_LONG).show();
		//}
		
		
		
		return null;
		
		}

		protected void onPostExecute(String file_url){
		
			 
			
			
			
			
		double lati;
		double longi;
		try{
		      jArray = new JSONArray(result);
		      if(jArray==null){
		    	  Toast.makeText(getBaseContext(), "empty" ,Toast.LENGTH_SHORT).show();
		      }
		      else{
		    	//  for(int j=0;j<markers.size();j++){
			   // 	  markers.get(j).remove();
			   //   }
			      
			    //  markers.clear();
		      
		      JSONObject json_data=null;
		      buslist.clear();
		      for(int i=0;i<jArray.length();i++){
		             json_data = jArray.getJSONObject(i);
		             lati=json_data.getDouble("lat");
		             longi=json_data.getDouble("long");
		             LatLng l= new LatLng(lati,longi);
		             buslist.add(l);
		           //  Marker m = mMap.addMarker(new MarkerOptions().position(new LatLng(lati, longi)));
		             //markers.add(m);
		             
		            
		             
		         }
		      
		      
		     
		      }
		      }
		      catch(JSONException e1){
		    	 Toast.makeText(getBaseContext(), "cannot connect to server" ,Toast.LENGTH_SHORT).show();
		      } catch (ParseException e1) {
					e1.printStackTrace();
			}
		pDialog.dismiss();
		
		//set marker to longitude and latitude;

		}
	
	
	
	}
	
	
	
	*/
	

	
	
	
	/*
	public float calcDistance(LatLng source, List<LatLng> stops ,int condition){
		float distance1=0;
		float [] dist = new float[1];
		float temp=0;
		try{
		
		Location.distanceBetween(source.latitude, source.longitude, stops.get(0).latitude, stops.get(0).longitude, dist);
		distance1=dist[0];
		if(condition==0)
			   latlongpost=1;
				   if(condition==1)
					   currentbusloc=1;
		for(int i=1;i<stops.size();i++){
			Location.distanceBetween(source.latitude, source.longitude, stops.get(i).latitude, stops.get(i).longitude, dist);
		   temp=dist[0];
		   if(temp<distance1){
			   distance1=temp;
			   if(condition==0)
		   latlongpost=i+1;
			   if(condition==1)
				   currentbusloc=i+1;
		   }
		}
		}
		catch(Exception e){
			
		}
		
		
		return distance1;
		
	}
	
	*/
	
	 public class nearestBus extends AsyncTask<String, String, String>{
		 
		 //protected void onPreExecute() {
	       //      super.onPreExecute();
	           //  pDialog = new ProgressDialog(MainActivity.this);
	           //pDialog.setMessage("Loading route. Please wait...");
	            // pDialog.setIndeterminate(false);
	            // pDialog.setCancelable(false);
	            // pDialog.show();
	         //}
		 
		 String distance="";
         String time="";
         
		 protected String doInBackground(String... args) {
			 String stringUrl;
			 
		 
			 stringUrl = "http://maps.googleapis.com/maps/api/distancematrix/json?origins="+nearestbus.latitude+"+"+nearestbus.longitude+"&destinations="+currentbusstop.latitude+"+"+currentbusstop.longitude+"&mode=driving&sensor=false";
         StringBuilder response = new StringBuilder();
		
        // Toast.makeText(getBaseContext(), stringUrl ,Toast.LENGTH_LONG).show();
         
         String jsonOutput=null;
         try {
             URL url = new URL(stringUrl);
             HttpURLConnection httpconn = (HttpURLConnection) url
                     .openConnection();
             if (httpconn.getResponseCode() == HttpURLConnection.HTTP_OK) {
                 BufferedReader input = new BufferedReader(
                         new InputStreamReader(httpconn.getInputStream()),
                         8192);
                 String strLine = null;

                 while ((strLine = input.readLine()) != null) {
                     response.append(strLine);
                 }
                 input.close();
             }

             jsonOutput = response.toString();
             
         }catch(Exception e){
        	// Toast.makeText(getBaseContext(), "Problem" ,Toast.LENGTH_SHORT).show();
         }
         
         if(jsonOutput==null){
        	 Toast.makeText(getBaseContext(), "Problem" ,Toast.LENGTH_SHORT).show();
         }
        try{
             JSONObject jsonObject = new JSONObject(jsonOutput);

             // routesArray contains ALL routes
           JSONArray rows = jsonObject.getJSONArray("rows");
             // Grab the first route
          JSONObject elements = rows.getJSONObject(0);
            
       //JSONObject elements1 = elements.getJSONObject("elements");
            JSONArray rows1 = elements.getJSONArray("elements");
          JSONObject dist = rows1.getJSONObject(0);
          // JSONArray rows2 = elements1.getJSONArray("duration");
          // JSONObject time2 = dist.getJSONObject("distance");
           //JSONArray rows3 = rows1.getJSONArray(0);
             
      //    JSONObject getelements = rows1.getJSONObject(0);
//
           //  JSONArray rows2 = jsonObject.getJSONArray("getelements");
       JSONObject getdistance = dist.getJSONObject("distance");
       JSONObject gettime = dist.getJSONObject("duration");
         distance= getdistance.getString("text");
         time= gettime.getString("text");
            // System.out.println(getdistance.getString("text"));
            // JSONObject gettime = rows.getJSONObject(1);
         //  t = gettime.getString("text");
             
             

         } catch (Exception e) {
        	Toast.makeText(getBaseContext(), "Problem" ,Toast.LENGTH_SHORT).show();
       }
         
         return null;
		 
	}
		 
		 protected void onPostExecute(String file_url) {
			// TextView displaykm = (TextView)findViewById(R.id.kmaway);
	         //displaykm.setText(distance);
	        // TextView displaymin = (TextView)findViewById(R.id.minaway);
	        // displaymin.setText(time);
		 }


		}
	 
	 
}
