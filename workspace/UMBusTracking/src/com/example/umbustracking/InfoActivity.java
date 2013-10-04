package com.example.umbustracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.zip.Inflater;





import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;


import android.os.AsyncTask;

import android.app.Activity;
import android.app.ListActivity;
import android.app.ProgressDialog;
import android.widget.TextView;
import android.database.SQLException;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.SimpleAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class InfoActivity extends ListActivity {
private DBaccess access;
loaddata loaddata1=new loaddata();
ProgressDialog pDialog;
List<Comment> values;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_info);
		
		
		
		//loaddata1.execute();
		
		
	access = new DBaccess();
		//access.open();
		
		

        MySQLiteHelper myDbHelper = new MySQLiteHelper(this);
      //  myDbHelper = new MySQLiteHelper(this);
 
        
 	
 	
 	class ListAdapter extends ArrayAdapter<Comment>{
 	    public List<Comment> entries;
 	   // private Activity activity;
 	 
 	  public ListAdapter(Context context, int textViewResourceId, List<Comment> entries) {
 	        super(context, textViewResourceId, entries);
 	        this.entries = entries;
 	      //  this.activity = a;
 	    }
 	   
 	  public long getItemId (int position){
 		  
 		  return entries.get(position).getId();
 		  
 	  }
 	  
 	  
 	 public View getView(int position, View convertView, ViewGroup parent) {

 		//View convertView;
 		View vi= convertView;
 		viewroutes holder;
 		//holder = new viewroutes();
 		if(convertView==null){
 			LayoutInflater inflater;
 			inflater = LayoutInflater.from(getBaseContext()); 
 			vi = inflater.inflate(R.layout.list_row, null);
 			
 		    
 			holder = new viewroutes();
 		    holder.routeroute = (TextView)vi.findViewById(R.id.RouteName); // city route
 		    holder.ETA = (TextView)vi.findViewById(R.id.ETA); // weather
 		    holder.routeInfo = (TextView)vi.findViewById(R.id.routeInfo);
 		   // holder.tvTemperature =  (TextView)vi.findViewById(R.id.tvTemp); // city temperature
 		    holder.icon =(ImageView)vi.findViewById(R.id.list_image); // thumb image
 		   holder.below =(TextView)vi.findViewById(R.id.textView1);
 		  vi.setTag(holder);
 		}
 		else{
 		  holder = (viewroutes)vi.getTag();
 		}
 		
 		Comment c = entries.get(position);
 		// Setting all values in listview
 		      
 		holder.routeroute.setText(c.getname());
 	    holder.routeInfo.setText(c.getId().toString());
 	    
 	    holder.ETA.setText(entries.get(position).getId().toString());
 	    holder.below.setText("");
 	//	holder.ETA.setText(weatherDataCollection.get(position).get(KEY_TEMP_C));
 		      
 		//Setting an image
 		//String uri = "drawable/"+ weatherDataCollection.get(position).get(KEY_ICON);
 		//int imageResource = vi.getContext().getApplicationContext().getResources().getIdentifier(
 		//   uri, null, vi.getContext().getApplicationContext().getPackageroute());
 	//	Drawable image = vi.getContext().getResources().getDrawable(imageResource);
 		//holder.icon.setImageDrawable(image);
 	
 	return vi;
 	
 	}
 	 
 	 
 	}
 	
 	/*Comment comment=new Comment();
		List<Comment> test= new ArrayList<Comment>();
		
		comment.setroute("hello world");
		comment.setId(Long.parseLong("0"));
		test.add(comment);
		Comment comment1=new Comment();
		
		comment1.setroute("hi");
	    comment1.setId(Long.parseLong("1"));
		test.add(comment1);
		Comment comment2=new Comment();
		comment2.setroute("fuck");
		comment2.setId(Long.parseLong("2"));
		test.add(comment2);
		
	*/
		
		//Toast.makeText(getBaseContext(), test.get(2).getroute() ,Toast.LENGTH_SHORT).show();
		
		List<Comment> values =access.getAllComments(myDbHelper.getReadableDatabase());
            access.close();
		ListAdapter adapter = new ListAdapter(this,R.layout.list_row, values);
		
		   
	setListAdapter(adapter);
		   //setListAdapter(new ArrayAdapter<Comment>(this, R.layout.list_row,values)); 
		    ListView lv = getListView();
		    
		    lv.setOnItemClickListener(new OnItemClickListener() {
		          public void onItemClick(AdapterView<?> parent, View view,
		              int position, long id) {
		            
		              // selected item 
		             // String product = ((TextView) view).getText().toString();
		               
		              // Launching new Activity on selecting single List Item
		              try{
		        	  Intent i = new Intent(getApplicationContext(), Test2.class);
		              // sending data to new activity
		        	 // Toast.makeText(getBaseContext(), (int)id ,Toast.LENGTH_SHORT).show();
		              i.putExtra("id", id);
		              startActivity(i);
		              }
		              catch(Exception e){
		            	  
		              }
		          }
		        });
		    
		//System.out.println("test");
	//	Toast.makeText(getBaseContext(), "failedx2" ,Toast.LENGTH_SHORT).show();
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
	    	
	    	Intent intent1 = new Intent(this,MainActivity.class);
            intent1.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
            startActivity(intent1);
            return true;
	    	 
	    case R.id.info:
	    	Toast.makeText(getBaseContext(), "You are already at the Info Activity" ,Toast.LENGTH_SHORT).show();

	    default:
	      break;
	    }

	    return true;
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
		        map.put("route", values.get(i).getroute());
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
			                        "route","year"},
			                new int[] { R.id.pid, R.id.route,R.id.year});
			        // updating listview
			        setListAdapter(adapter);
				*/
				
			//	ArrayAdapter<Comment> adapter = new ArrayAdapter<Comment>(this,android.R.layout.simple_list_item_2, values);
				//    setListAdapter(adapter);
				    
			       // System.out.println("success");
	       			}
			catch(Exception e){
				//Toast.makeText(getBaseContext(), "failedx2" ,Toast.LENGTH_SHORT).show();
				//System.out.println(e.getMessage());
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
		
	
	
	public class viewroutes {
	    public TextView routeroute;
	    public TextView ETA;
	    public ImageView icon;
	    public ImageView arrow;
	    public TextView routeInfo;
	    public TextView below;
	}
	
	
	
		

}