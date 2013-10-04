package com.example.umbustracking;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

//import com.example.umbustracking.Test2.viewRoutes;

class CustomAdapter extends ArrayAdapter<Comment>{
	    private List<Comment> entries;
		viewRoutes holder;
		ArrayList<String> s;
	  //  private Activity activity;
	 
	   CustomAdapter(Context context, int textViewResourceId, List<Comment> entries, ArrayList<String> s) {
	        super(context, textViewResourceId, entries);
	        this.entries = entries;
	        this.s=s;
	      //  this.activity = a;
	    }
	   
	  public long getItemId (int position){
		  
		  return entries.get(position).getId();
		  
	  }
	  
	 
	
	  
	 public View getView(int position, View convertView, ViewGroup parent) {

		//View convertView;
		View vi= convertView;
	
		//holder = new viewRoutes();
		if(convertView==null){
			LayoutInflater inflater;
			inflater = LayoutInflater.from(getContext()); 
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
	   // holder.routeInfo.setText(c.getId().toString());
	   // LatLng a= new LatLng(c.getlatitude(),c.getlongitude());
	    
	   
	    try{
	    holder.ETA.setText(s.get(position));}
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
	  
	 public class viewRoutes {
		    public TextView RouteName;
		    public TextView ETA;
		    public ImageView icon;
		    public ImageView arrow;
		    public TextView routeInfo;
		}
	  
	}