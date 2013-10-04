package com.example.umbustracking;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import com.google.android.gms.maps.model.LatLng;

//import com.example.umbustracking.InfoActivity.viewRoutes;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class Test2 extends ListActivity {

	private DBaccess access;

	ProgressDialog pDialog;
	List<Comment> values;
	List<LatLng> busses = new ArrayList<LatLng>();
	calcDistance calculate;
	LatLng source;
	LatLng destination;
	nearestBus1 n;
	ArrayList<String> minutes = new ArrayList<String>();
	CustomAdapter adapter;
	ArrayList<LatLng> latholder= new ArrayList<LatLng>();
	List<bussholder> allbusses= new ArrayList<bussholder>();
	long id2;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_info2);
	//   busses= MainActivity.buslist3;
		access = new DBaccess();
		allbusses=MainActivity.busses;
		
		//access.open();
		
		
		
		MySQLiteHelper myDbHelper = new MySQLiteHelper(this);
	
        try{
            Intent i = getIntent();
            // getting attached intent data
          id2 = i.getLongExtra("id", 0);
           //id++;
        //   Toast.makeText(getBaseContext(), (int)id ,Toast.LENGTH_SHORT).show();
            		
    		List<Comment> values =access.getAllstops(myDbHelper.getReadableDatabase(), id2);
    		access.close();
    		
    		for(int ii=0;ii<allbusses.size();ii++){
    			if(allbusses.get(ii).getRouteId()==id2)
    				busses.add(allbusses.get(ii).getCoordinates());
    		}
    		
    		
    		minutes.clear();
    		if(busses.isEmpty()){
    			
    		}
    		else{
    	for(int i1=0;i1<values.size();i1++){
    			calculate= new calcDistance();
    			LatLng aa= new LatLng(values.get(i1).getlatitude(),values.get(i1).getlongitude());
    			calculate.distance(aa, busses);
    			destination = busses.get(calculate.getLocator());
    			
    			source=aa;
    		//System.out.println(source);
    		//	System.out.println(destination);
    		//	n = new nearestBus1();
    			latholder.add(source);
    			latholder.add(destination);
    		//	n.execute();
    			
    		} 
    	n = new nearestBus1();
    	n.execute();
    		}
    		//Toast.makeText(getBaseContext(), minutes.size() ,Toast.LENGTH_SHORT).show();
    		//System.out.println(minutes.size());
    		
    		/*	class CustomAdapter extends ArrayAdapter<Comment>{
    	 	    private List<Comment> entries;
    	 	  //  private Activity activity;
    	 	 
    	 	   CustomAdapter(Context context, int textViewResourceId, List<Comment> entries) {
    	 	        super(context, textViewResourceId, entries);
    	 	        this.entries = entries;
    	 	      //  this.activity = a;
    	 	    }
    	 	   
    	 	  public long getItemId (int position){
    	 		  
    	 		  return entries.get(position).getId();
    	 		  
    	 	  }
    	 	  
    	 	 // public void changeETA(ArrayList<String> a){
    	 		  
    	 	  //}
    	 	  
    	 	 public View getView(int position, View convertView, ViewGroup parent) {

    	  		//View convertView;
    	  		View vi= convertView;
    	  		viewRoutes holder;
    	  		//holder = new viewRoutes();
    	  		if(convertView==null){
    	  			LayoutInflater inflater;
    	  			inflater = LayoutInflater.from(getBaseContext()); 
    	  			vi = inflater.inflate(R.layout.list_row, null);
    	  			
    	  		    
    	  			holder = new viewRoutes();
    	  		    holder.RouteName = (TextView)vi.findViewById(R.id.RouteName); // city name
    	  		    holder.ETA = (TextView)vi.findViewById(R.id.ETA); // weather
    	  		    holder.routeInfo = (TextView)vi.findViewById(R.id.routeInfo);
    	  		   // holder.tvTemperature =  (TextView)vi.findViewById(R.id.tvTemp); // city temperature
    	  		    holder.icon =(ImageView)vi.findViewById(R.id.list_image); // thumb image
    	  		    
    	  		  vi.setTag(holder);
    	  		}
    	  		else{
    	  		  holder = (viewRoutes)vi.getTag();
    	  		}
    	  		
    	  		Comment c = entries.get(position);
    	  		// Setting all values in listview
    	  		
    	  		
    	  		holder.RouteName.setText(c.getname());
    	  	    holder.routeInfo.setText(c.getId().toString());
    	  	   // LatLng a= new LatLng(c.getlatitude(),c.getlongitude());
    	  	    
    	  	    if(!busses.isEmpty()){
    	  	    	//Toast.makeText(getBaseContext(), (int) Math.ceil(calculate.distance(a, busses)) ,Toast.LENGTH_SHORT).show();
    	  	    }
    	  	    try{
    	  	    holder.ETA.setText("a");}
    	  	    catch(Exception e){
    	  	    	
    	  	    }
    	  	    
    	  	    
    	  	 
    	  	    
    	  	    
    	  	    
    	  	//	holder.ETA.setText(weatherDataCollection.get(position).get(KEY_TEMP_C));
    	  		      
    	  		//Setting an image
    	  		//String uri = "drawable/"+ weatherDataCollection.get(position).get(KEY_ICON);
    	  		//int imageResource = vi.getContext().getApplicationContext().getResources().getIdentifier(
    	  		//   uri, null, vi.getContext().getApplicationContext().getPackageName());
    	  	//	Drawable image = vi.getContext().getResources().getDrawable(imageResource);
    	  		//holder.icon.setImageDrawable(image);
    	  	
    	  	return vi;
    	  	
    	  	}
    	 	  
    	 	  
    	 	  
    	 	}
    	 	 
    			
    	*/		

    			adapter = new CustomAdapter(this,R.layout.list_row, values, minutes);
    			
    			    setListAdapter(adapter);
    			    
    			    ListView lv = getListView();
    			    
    			    lv.setOnItemClickListener(new OnItemClickListener() {
    			          public void onItemClick(AdapterView<?> parent, View view,
    			              int position, long id) {
    			           String s=String.valueOf(id);
    			           
    			           
    			              // selected item 
    			             // String product = ((TextView) view).getText().toString();
    			               
    			              // Launching new Activity on selecting single List Item
    			              try{
    			        	  Intent i = new Intent(getApplicationContext(), ViewStopsDetails.class);
    			              // sending data to new activity
    			        	 // Toast.makeText(getBaseContext(), (int)id ,Toast.LENGTH_SHORT).show();
    			              i.putExtra("id1", s);
    			              
    			              i.putExtra("routeid", String.valueOf(id2));
    			              
    			             
    			              startActivity(i);
    			              }
    			              catch(Exception e){
    			            	  
    			              }
    			          }
    			        });
    			    
    			//System.out.println("test");
    		//	Toast.makeText(getBaseContext(), "failedx2" ,Toast.LENGTH_SHORT).show();
    		
    		}
    		catch(Exception e){
    			
    		}
	
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.test2, menu);
		return true;
	}
	
	public class viewRoutes {
	    public TextView RouteName;
	    public TextView ETA;
	    public ImageView icon;
	    public ImageView arrow;
	    public TextView routeInfo;
	}
	
	
	
	public class nearestBus1 extends AsyncTask<String, String, String>{
		 
		 
		 
		 String distance="";
        String time="";
        String jsonOutput=null;
        	
		 protected String doInBackground(String... args) {
			 
			 
			 
				 
				 
				 for(int j=0;j<latholder.size();j=j+2){
				 String stringUrl;
				 
		 
			
			 stringUrl = "http://maps.googleapis.com/maps/api/distancematrix/json?origins="+latholder.get(j).latitude+"+"+latholder.get(j).longitude+"&destinations="+latholder.get(j+1).latitude+"+"+latholder.get(j+1).longitude+"&mode=driving&sensor=false";
			// System.out.println(stringUrl);
			 StringBuilder response = new StringBuilder();
		
       // Toast.makeText(getBaseContext(), stringUrl ,Toast.LENGTH_LONG).show();
			 try{
         jsonOutput=null;
        
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
        
      JSONObject getdistance = dist.getJSONObject("distance");
      JSONObject gettime = dist.getJSONObject("duration");
        distance= getdistance.getString("text");
        time= gettime.getString("text");
           // System.out.println(gettime.getString("text"));
          //  System.out.println(getdistance.getString("text"));
            time= time.replace("mins", "");
            time= time.replace("min", "");
            minutes.add(time);
            
            

        } catch (Exception e) {
       	Toast.makeText(getBaseContext(), "Problem" ,Toast.LENGTH_SHORT).show();
      }
				 }
        return null;
		 
	}
		 
		 protected void onPostExecute(String file_url) {
			 adapter.notifyDataSetChanged();
			
		//	System.out.println(time);
			//time=time.substring(0, 2);
			 
			
			
		 }


		}
	
	 
	

}
