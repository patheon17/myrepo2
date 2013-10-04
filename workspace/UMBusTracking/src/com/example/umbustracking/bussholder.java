package com.example.umbustracking;

import com.google.android.gms.maps.model.LatLng;

public class bussholder {

	private LatLng coordinates;
	private boolean emergency;
	private int routeid;
	private String direction;
	private String plate;
	private float distance;
	
	public void setCoordinates(LatLng coordinates){
		this.coordinates=coordinates;
	}
	public LatLng getCoordinates(){
		return coordinates;
	}
	
	public void setEmergency(boolean emergency){
		this.emergency=emergency;
	}
	
	public boolean getEmergency(){
		return emergency;
	}
	
	public void setRouteId(int routeid){
		this.routeid=routeid;
	}
	
	public int getRouteId(){
		return routeid;
	}
	
	public void setDirection(String direction){
		this.direction=direction;
	}
	public String getDirection(){
		return direction;
	}
	
	public void setPlate(String plate){
		this.plate=plate;
	}
	public String getPlate(){
		return plate;
	}
	public void setDistance(float distance){
	this.distance=distance;
	}
	public float getDistance(){
		return distance;
	}
}
