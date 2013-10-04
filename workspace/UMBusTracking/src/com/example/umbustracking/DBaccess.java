package com.example.umbustracking;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

public class DBaccess {
	
	  // Database fields
	 // private SQLiteDatabase database;
	  //private MySQLiteHelper dbHelper;
//	  private String[] allColumns = { MySQLiteHelper.Id,
	//      MySQLiteHelper.name,
	  //    MySQLiteHelper.Model,
	    //  MySQLiteHelper.Year};

	  public DBaccess() {
	  //  dbHelper = new MySQLiteHelper(context);
	  }

	/*  public void open() throws SQLException {
	    database = dbHelper.getWritableDatabase();
	    ContentValues values = new ContentValues();
	    //values.put("id", null);
	    values.put("year", "2011");
	    
	  //values.put("model", "Galaxy S 2");
	    values.put("name", "samsung");
	    
	     database.insert("smartphone", null, values);
	   
	    
	  }
*/
	  public void close() {
	   // dbHelper.close();
	  }

	 /* public Comment createComment(String comment) {
	    ContentValues values = new ContentValues();
	    values.put(MySQLiteHelper.COLUMN_COMMENT, comment);
	    long insertId = database.insert(MySQLiteHelper.TABLE_COMMENTS, null,
	        values);
	    Cursor cursor = database.query(MySQLiteHelper.TABLE_COMMENTS,
	        allColumns, MySQLiteHelper.COLUMN_ID + " = " + insertId, null,
	        null, null, null);
	    cursor.moveToFirst();
	    Comment newComment = cursorToComment(cursor);
	    cursor.close();
	    return newComment;
	  }

	  public void deleteComment(Comment comment) {
	    long id = comment.getId();
	    System.out.println("Comment deleted with id: " + id);
	    database.delete(MySQLiteHelper.TABLE_COMMENTS, MySQLiteHelper.COLUMN_ID
	        + " = " + id, null);
	  }
	*/
	  public List<Comment> getAllComments(SQLiteDatabase database) {
	    List<Comment> comments = new ArrayList<Comment>();

	    Cursor cursor = database.query("routes",
	        null, null, null, null, null, null);

	  
	    
	   if(cursor.getCount()==0){
		System.out.println("databaseempty");
			//Toast.makeText(null, "failedx2" ,Toast.LENGTH_SHORT).show();
		}
	    
	    cursor.moveToFirst();
	    System.out.println(cursor.getString(0));
	   //if(cursor!=null){
	    while (!cursor.isAfterLast()) {
	      Comment comment = cursorToComment(cursor);
	      comments.add(comment);
	      cursor.moveToNext();
	    }
	    
	  //  }
	    // Make sure to close the cursor
	    cursor.close();
	    return comments;
	  }

	  
	  
	  
	  
	  public List<Comment> view1stop(SQLiteDatabase database, long id) {
		    List<Comment> comments = new ArrayList<Comment>();
		    Cursor cursor;


		     cursor = database.query("stops",
		        null, "id="+id, null, null, null, null);


		    
		   if(cursor.getCount()==0){
			System.out.println("databaseempty");
				//Toast.makeText(null, "failedx2" ,Toast.LENGTH_SHORT).show();
			}
		    
		    cursor.moveToFirst();
		    System.out.println(cursor.getString(0));
		   //if(cursor!=null){
		    while (!cursor.isAfterLast()) {
		      Comment comment = cursorToComment1(cursor);
		      comments.add(comment);
		      cursor.moveToNext();
		    }
		    
		  //  }
		    // Make sure to close the cursor
		    cursor.close();
		    return comments;
		  }
	  
	  
	  
	  
	  
	  public List<Comment> getAllstops(SQLiteDatabase database, long id) {
		    List<Comment> comments = new ArrayList<Comment>();
		    Cursor cursor;
if(id==-1){
 cursor = database.query("stops",
	        null, null, null, null, null, null);
}
else{
		     cursor = database.query("stops",
		        null, "route_id="+id, null, null, null, null);

}
		    
		   if(cursor.getCount()==0){
			System.out.println("databaseempty");
				//Toast.makeText(null, "failedx2" ,Toast.LENGTH_SHORT).show();
			}
		    
		    cursor.moveToFirst();
		    System.out.println(cursor.getString(0));
		   //if(cursor!=null){
		    while (!cursor.isAfterLast()) {
		      Comment comment = cursorToComment1(cursor);
		      comments.add(comment);
		      cursor.moveToNext();
		    }
		    
		  //  }
		    // Make sure to close the cursor
		    cursor.close();
		    return comments;
		  }

	  
	  private Comment cursorToComment(Cursor cursor) {
	    Comment comment = new Comment();
	   comment.setId(cursor.getLong(0));
	    comment.setname(cursor.getString(1));
	   // comment.setModel(cursor.getString(2));
	   // comment.setYear(cursor.getString(2));
	    return comment;
	  }
	  
	  
	  private Comment cursorToComment1(Cursor cursor) {
		    Comment comment = new Comment();
		   comment.setId(cursor.getLong(0));
		    comment.setname(cursor.getString(1));
		    comment.setrouteid(Double.parseDouble(cursor.getString(2)));
		    comment.setlatitude((double)cursor.getFloat(3));
		    comment.setlongitude((double)cursor.getFloat(4));
		    comment.setdesc(cursor.getString(5));
		    comment.setimage(cursor.getString(6));
		    System.out.println(cursor.getString(5));
		    //System.out.println((double)cursor.getFloat(4));
		   // comment.setYear(cursor.getString(2));
		    return comment;
		  }
	  
	} 
