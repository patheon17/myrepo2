package com.example.umbustracking;

public class Comment {
	  private long id;
	  private String name;
	  private Double routeid;
	  private Double latitude;
	  private Double longitude;
	  private String desc;
	  private String image;
	  
	  public Long getId() {
	    return id;
	  }

	  public void setId(Long id) {
	    this.id = id;
	  }

	  public String getname() {
	    return name;
	  }

	  public void setname(String name) {
	    this.name = name;
	  }

	  public Double getrouteid() {
		    return routeid;
		  }

		  public void setrouteid(Double routeid) {
		    this.routeid = routeid;
		  }
		  
		  public Double getlatitude() {
			    return latitude;
			  }
		  
		  public void setlongitude(Double longitude){
			  this.longitude=longitude;
		  }
		  public Double getlongitude() {
			    return longitude;
			  }

			  public void setlatitude(Double latitude) {
			    this.latitude =latitude;
			  }
			  
			  
			  public void setdesc(String desc){
				  this.desc=desc;
			  }
			  public String getdesc() {
				    return desc;
				  }
			  public void setimage(String image){
				  this.image=image;
			  }
			  public String getImage(){
				  return image;
				  }
	  // Will be used by the ArrayAdapter in the ListView
	//  @Override
	 public String toString() {
	    return name;
	  }
	} 