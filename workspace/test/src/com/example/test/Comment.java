package com.example.test;



public class Comment {
	  private long id;
	  private String manufacturer;
	  private String model;
	  private String year;

	  public Long getId() {
	    return id;
	  }

	  public void setId(Long id) {
	    this.id = id;
	  }

	  public String getManufacturer() {
	    return manufacturer;
	  }

	  public void setManufacturer(String manufacturer) {
	    this.manufacturer = manufacturer;
	  }

	  public String getModel() {
		    return model;
		  }

		  public void setModel(String model) {
		    this.model = model;
		  }
		  
		  public String getYear() {
			    return year;
			  }

			  public void setYear(String year) {
			    this.year =year;
			  }
	  // Will be used by the ArrayAdapter in the ListView
	//  @Override
	 public String toString() {
	    return manufacturer;
	  }
	} 