package com.example.test;

import java.util.ArrayList;
import java.util.List;

import com.example.test.Comment;

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
	//      MySQLiteHelper.Manufacturer,
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
	    values.put("manufacturer", "samsung");
	    
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
          
	try{
	    Cursor cursor = database.query("smartphone",
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
	}
	catch(Exception e){
		System.out.println("fail");
		
	}
	  
	    
	  
	    return comments;
	  }

	  private Comment cursorToComment(Cursor cursor) {
	    Comment comment = new Comment();
	   comment.setId(cursor.getLong(0));
	    comment.setManufacturer(cursor.getString(1));
	   // comment.setModel(cursor.getString(2));
	   // comment.setYear(cursor.getString(2));
	    return comment;
	  }
	} 
