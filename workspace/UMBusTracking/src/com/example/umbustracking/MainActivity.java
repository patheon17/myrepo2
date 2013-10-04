package com.example.umbustracking;



import android.net.ParseException;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.database.SQLException;
import android.graphics.Color;
//import android.app.ListActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
//import com.google.android.gms.maps.GoogleMapOptions;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.conn.ConnectionPoolTimeoutException;
import org.apache.http.conn.HttpHostConnectException;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHttpResponse;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;



public class MainActivity extends Activity {
	
	static List<LatLng> buslist = new ArrayList<LatLng>();
	static List<LatLng> buslist3 = new ArrayList<LatLng>();
	LatLng a= new LatLng(3.12147, 101.65349);
	LatLng b= new LatLng(3.11804, 101.65855);
	LatLng c= new LatLng(3.11879, 101.65109);
	static  ArrayList<bussholder> busses = new ArrayList<bussholder>();
	
	
	private DBaccess access;
	
	Double[] routeA= new Double[]{3.1222,  101.65333,  3.12249,  101.65318,  3.12264,  101.65306,  3.12264,  101.6516,  3.12271,  101.6515,  3.12289,  101.65133,  3.12305, 
			101.65126,  3.12327,  101.65124,  3.1238,  101.6513,  3.12411,  101.65137,  3.12506,  101.65154,  3.12542,  101.65159,  3.12573,  101.65159,  3.12603,  101.65155, 
			3.12625,  101.65148,  3.12686,  101.65128,  3.12707,  101.65119,  3.1272,  101.65103,  3.12764,  101.6501,  3.1278,  101.64984,  3.128,  101.64966,  3.12816,  101.64959, 
			3.12841,  101.64958,  3.12872,  101.6497,  3.13019,  101.65061,  3.13032,  101.65076,  3.13034,  101.65099,  3.13033,  101.65123,  3.13037,  101.65152,  3.13048, 
			101.65205,  3.1306,  101.65231,  3.13084,  101.6526,  3.13146,  101.65323,  3.13186,  101.65365,  3.13206,  101.65391,  3.13216,  101.65411,  3.13227,  101.65429,  3.1324,  
			101.65444,  3.1327,  101.65461,  3.13291,  101.65471,  3.13317,  101.65478,  3.13368,  101.65489};


	
	Double[] routeB = new Double[]{3.12108,  101.65348,  3.12144,  101.65348,  3.12165, 
			101.65353,  3.12178,  101.65362,  3.12157,  101.65406,  3.12151,  101.65429, 
			3.12149,  101.65444,  3.12149,  101.65458,  3.12155,  101.65483,  3.12184, 
			101.65543,  3.12187,  101.65574,  3.12185,  101.65664,  3.12181,  101.65769, 
			3.12168,  101.65836,  3.12164,  101.65883,  3.12181,  101.65968,  3.12183, 
			101.65988,  3.12182,  101.66003,  3.12176,  101.66021,  3.12169,  101.66043, 
			3.12173,  101.6606,  3.12178,  101.66067,  3.12189,  101.66075,  3.12199,  101.6608,
			3.12211,  101.66081,  3.12253,  101.66081,  3.12342,  101.66073,  3.1236,  101.6607,
			3.12379,  101.66064,  3.12412,  101.66048,  3.12442,  101.6603,  3.12461,  101.66016, 
			3.12491,  101.65996,  3.12504,  101.65986,  3.12516,  101.65981,  3.12552,  101.65972, 
			3.12578,  101.65967,  3.12602,  101.65968,  3.12704,  101.65986,  3.12827,  101.66008,
			3.12848,  101.66012,  3.1289,  101.66016,  3.12985,  101.66022,  3.13118,  101.66028, 
			3.13131,  101.66028,  3.13143,  101.66018,  3.13192,  101.65946,  3.13195,  101.65827,
			3.13197,  101.65817,  3.13203,  101.65809,  3.1321,  101.65804,  3.13223,  101.65802, 
			3.13255,  101.65752,  3.13267,  101.65745,  3.13281,  101.65746,  3.13292,  101.65746,
			3.133,  101.65744};
	
	//List<LatLng> routeA;
	
	
	List<LatLng> polyz;
	Loadallgps loadallgps=new Loadallgps();
	Handler m_handler=new Handler();
	
	

	
	Runnable m_handlerTask = new Runnable()
	{
	     @Override 
	     public void run() {
	    	 try{
	          task();
	         // Toast.makeText(getBaseContext(), "awesome" ,Toast.LENGTH_SHORT).show();
	    	 }
	    	 catch(Exception e){
	    		 //Toast.makeText(getBaseContext(), "failfailfail" ,Toast.LENGTH_SHORT).show();
	    	 }
	          m_handler.postDelayed(m_handlerTask, 15000);
	     }
	};
	
		
	

	
	private GoogleMap mMap;
	
	//private ProgressDialog pDialog;
	 
    // Creating JSON Parser object
    JSONParser jParser = new JSONParser();
    
	static final LatLng UM = new LatLng(3.12275, 101.66027);
	
	
	JSONArray jArray;
	String result = null;
	InputStream is = null;
	ProgressDialog pDialog;
	StringBuilder sb=null;
	ArrayList<Marker> markers = new ArrayList<Marker>();
	//String url="http://42.152.204.112/location/test.php";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		access = new DBaccess();
		//access.open();
		
		
	    MySQLiteHelper myDbHelper = new MySQLiteHelper(this);
		
		//List<Comment> busstops=access.getAllComments(myDbHelper.getReadableDatabase());
		
		
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
 	
		
		
		
		
		
		try{
		mMap = ((MapFragment) getFragmentManager().findFragmentById(R.id.map)).getMap();
		mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(UM, 16));
		
		
		mMap.addMarker(new MarkerOptions().icon(BitmapDescriptorFactory.fromResource(R.drawable.icon)).position(UM).title("You Are Here"));
	//Button button1= (Button)findViewById(R.id.button1);
		LatLng lati= new LatLng(3.12164, 101.65868);
		mMap.addMarker(new MarkerOptions().icon(BitmapDescriptorFactory.fromResource(R.drawable.stops)).position(lati));
		LatLng lati1= new LatLng(3.12390, 101.66045);
		mMap.addMarker(new MarkerOptions().icon(BitmapDescriptorFactory.fromResource(R.drawable.stops)).position(lati1));
		
		
		}
		catch(Exception e){
			
		}
		
		try{
			List<Comment> allstops =access.getAllstops(myDbHelper.getReadableDatabase(),-1);
    		access.close();
    		LatLng stoplat;
    		for(int i=0;i<allstops.size();i++){
    			stoplat=new LatLng(allstops.get(i).getlatitude(),allstops.get(i).getlongitude());
    			mMap.addMarker(new MarkerOptions().icon(BitmapDescriptorFactory.fromResource(R.drawable.stops)).position(stoplat));
    		}
		}
		
		catch(Exception e){
			
		}
		
		try{
			drawlines(routeA,0);
			drawlines(routeB,1);
		}
		catch(Exception e){
			
		}
		
		
		buslist3.add(a);
		buslist3.add(b);
		buslist3.add(c);
		
		
	//	loadallgps.execute();
	//try{
	//	new GetDirection().execute();
	//}
		//catch(Exception e){
			
		//}
		

		startRepeatingTask();
		
		
	
		
	//edited code here
	
		//http post
		
		
/*button1.setOnClickListener(new View.OnClickListener() {
			
	
	//it ends here
*/	
	
	
	}
	
	
	
	 public void task() {
			 Loadallgps loadallgps=new Loadallgps();
			    loadallgps.execute();

			   }
	
	 void startRepeatingTask()
	 {
	     m_handlerTask.run(); 
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
	    	 Toast.makeText(getBaseContext(), "You are already at the Maps Activity" ,Toast.LENGTH_SHORT).show();
	    	break;
	    case R.id.info:
	    	Intent intent1 = new Intent(this,InfoActivity.class);
            intent1.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
           // this.getIntent()
            startActivity(intent1);
            return true;
	    case R.id.nearest:
	    	Intent intent2 = new Intent(this,NearestStop.class);
            intent2.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
            intent2.putExtra("lat", UM.latitude);
            intent2.putExtra("long", UM.longitude);
            
           // this.getIntent()
            startActivity(intent2);
            return true;

	    default:
	      break;
	    }

	    return true;
	  } 
	
	
	
	
	//public void task(){
		//loadallgps.doInBackground();
		//loadallgps.onPostExecute(null);
        //Toast.makeText(getBaseContext(),
          //         "test",
            //       Toast.LENGTH_SHORT).show();
        //handler.postDelayed(runnable, 5000);
   // }
	
	class Loadallgps extends AsyncTask<String, String,String>{

		protected void onPreExecute(){

	super.onPreExecute();
		
		pDialog=new ProgressDialog(MainActivity.this);
		pDialog.setMessage("Loading GPS coordinates. Please wait.....");
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
		    
		     HttpPost httppost = new HttpPost("http://testing1515.freeiz.com/test.php");
		     httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
		     
		     HttpResponse response = httpclient.execute(httppost);
		     HttpEntity entity = response.getEntity();
		     is = entity.getContent();
		     
		     }
		
		
		catch(ConnectException e){
		       //  Log.e("log_tag", "Error in http connection"+e.toString());
		        //Toast.makeText(getBaseContext(), "Cant connect to database" ,Toast.LENGTH_SHORT).show();
			pDialog.dismiss();
			cancel(true);
		    }
		catch(ConnectTimeoutException e){
		       //  Log.e("log_tag", "Error in http connection"+e.toString());
		        //Toast.makeText(getBaseContext(), "Cant connect to database" ,Toast.LENGTH_SHORT).show();
			pDialog.dismiss();
			cancel(true);
		}
		
		
		catch (Exception e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
			//Toast.makeText(getBaseContext(), "Cant connect to database" ,Toast.LENGTH_SHORT).show();
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
		
	//	if(result==null){
	//		Toast.makeText(getBaseContext(), "empty" ,Toast.LENGTH_LONG).show();
	//	}
		
		
		
		return null;
		
		}

		protected void onPostExecute(String file_url){
		
			 
			
			
			
			
		double lati;
		double longi;
		boolean condition;
		int routeid;
		String direction;
		int cond;
		String plate;
		try{
			//System.out.println(result);
			
		      jArray = new JSONArray(result);
		      if(jArray==null){
		    	//  Toast.makeText(getBaseContext(), "empty" ,Toast.LENGTH_SHORT).show();
		    	  
		      }
		     else{
		    	  for(int j=0;j<markers.size();j++){
			   	  markers.get(j).remove();
			   	  
			      }
			      
			      markers.clear();
		      JSONObject json_data=null;
		      buslist.clear();
		      busses.clear();
		     // System.out.println("success");
		      for(int i=0;i<jArray.length();i++){
		             json_data = jArray.getJSONObject(i);
		             lati=json_data.getDouble("lat");
		             longi=json_data.getDouble("long");
		             cond=json_data.getInt("condition");
		             routeid=json_data.getInt("routeid");
		             direction=json_data.getString("direction");
		             plate=json_data.getString("platenum");
		              Marker m= mMap.addMarker(new MarkerOptions().position(new LatLng(lati, longi)));
		            markers.add(m);
		             // System.out.println(lati+",,"+longi);
		             LatLng l= new LatLng(lati, longi);
		            buslist.add(l);
		           bussholder temp=new bussholder();
		           temp.setCoordinates(l);
		           temp.setDirection(direction);
		           temp.setRouteId(routeid);
		           temp.setPlate(plate);
		           if(cond==0){
		        	   temp.setEmergency(true);
		           }
		           else{
		        	   temp.setEmergency(false);
		           }
		            
		             busses.add(temp);
		             
		         }
		      
		  //  Toast.makeText(getBaseContext(), busses.size() ,Toast.LENGTH_LONG).show();
		      
		      if(busses.isEmpty()){
		    	  Toast.makeText(getBaseContext(), busses.size() ,Toast.LENGTH_LONG).show();
		    	  finish();
		    	  
		      }
		     
		      }
		      System.out.println(busses.size());
		      
		      }
		      catch(JSONException e1){
		    	  Toast.makeText(getBaseContext(), "FAILED" ,Toast.LENGTH_SHORT).show();
		      } catch (ParseException e1) {
					e1.printStackTrace();
			}
		pDialog.dismiss();
		
		//set marker to longitude and latitude;

		}
	
	
	
	}
	
	//decode poly 
	
	 private List<LatLng> decodePoly(String encoded) {

         List<LatLng> poly = new ArrayList<LatLng>();
         int index = 0, len = encoded.length();
         int lat = 0, lng = 0;

         while (index < len) {
             int b, shift = 0, result = 0;
             do {
                 b = encoded.charAt(index++) - 63;
                 result |= (b & 0x1f) << shift;
                 shift += 5;
             } while (b >= 0x20);
             int dlat = ((result & 1) != 0 ? ~(result >> 1) : (result >> 1));
             lat += dlat;

             shift = 0;
             result = 0;
             do {
                 b = encoded.charAt(index++) - 63;
                 result |= (b & 0x1f) << shift;
                 shift += 5;
             } while (b >= 0x20);
             int dlng = ((result & 1) != 0 ? ~(result >> 1) : (result >> 1));
             lng += dlng;

             LatLng p = new LatLng((((double) lat / 1E5)),
                     (((double) lng / 1E5)));
             poly.add(p);
         }

         return poly;
     }
	 
	 
	 
	 //get direction task
	 
	 class GetDirection extends AsyncTask<String, String, String> {

         @Override
         protected void onPreExecute() {
            // super.onPreExecute();
           //  pDialog = new ProgressDialog(MainActivity.this);
           //pDialog.setMessage("Loading route. Please wait...");
            // pDialog.setIndeterminate(false);
            // pDialog.setCancelable(false);
            // pDialog.show();
         }

         protected String doInBackground(String... args) {
            
             String stringUrl = "http://maps.googleapis.com/maps/api/directions/json?origin=3.1226+101.6535&destination=3.13461+101.65656&sensor=false";
             StringBuilder response = new StringBuilder();
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

                 String jsonOutput = response.toString();

                 JSONObject jsonObject = new JSONObject(jsonOutput);

                 // routesArray contains ALL routes
                 JSONArray routesArray = jsonObject.getJSONArray("routes");
                 // Grab the first route
                 JSONObject route = routesArray.getJSONObject(0);

                 JSONObject poly = route.getJSONObject("overview_polyline");
                 String polyline = poly.getString("points");
                 polyz = decodePoly(polyline);

             } catch (Exception e) {

             }

             return null;

         }

         protected void onPostExecute(String file_url) {
            try{
             for (int i = 0; i < polyz.size()-1; i++) {
                 LatLng src = polyz.get(i);
                 LatLng dest = polyz.get(i + 1);
                 Polyline line = mMap.addPolyline(new PolylineOptions()
                         .add(new LatLng(src.latitude, src.longitude),
                                 new LatLng(dest.latitude,dest.longitude))
                         .width(2).color(Color.RED).geodesic(true));
                 
               // System.out.println(src.latitude);
           //     System.out.println(src.longitude);
            //     System.out.println(dest.latitude);
             //   System.out.println(dest.longitude);
                 
                 

             }
            }
            catch(Exception e){
            	
            }
            // pDialog.dismiss();

         }
     }
	 
	 
	 
	 private void drawlines(Double[] s,int a) {

		 try{
             for (int i = 0; i < s.length; i=i+2) {
                 LatLng src =new LatLng(s[i],s[i+1]);
                 LatLng dest = new LatLng(s[i+2],s[i+3]);
                 if(a==0){
                 Polyline line = mMap.addPolyline(new PolylineOptions()
                         .add(new LatLng(src.latitude, src.longitude),
                                 new LatLng(dest.latitude,dest.longitude))
                         .width(2).color(Color.RED).geodesic(true));}
                 if(a==1){
                	 
                         Polyline line = mMap.addPolyline(new PolylineOptions()
                                 .add(new LatLng(src.latitude, src.longitude),
                                         new LatLng(dest.latitude,dest.longitude))
                                 .width(2).color(Color.GREEN).geodesic(true));
                         
                 }
                 
                 
               //  System.out.println(src.latitude);
             //    System.out.println(src.longitude);
             //    System.out.println(dest.latitude);
              //   System.out.println(dest.longitude);
                 
                 

             }
            }
            catch(Exception e){
            	
            }
		 
     }
	 
	 
	 public List<LatLng> getCoords(){
		 return buslist;
	 }
	 
	 
	 
 

}
