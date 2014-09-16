package com.myapp.myapplication;



import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by sawyer on 9/15/14.
 */

public class HandlerDatabase {
    //Database Model
    private ModelDatabase model;

    //Database
    private SQLiteDatabase database;

    //All Fields
    private String[] allColumns = {
            ModelDatabase.MESSAGE_ID,
            ModelDatabase.MESSAGE_USER,
            ModelDatabase.MESSAGE_TEXT
    };

    //Public Constructor - create connection to Database
    public HandlerDatabase(Context context){
        model = new ModelDatabase(context);
    }

    /**
     * Add
     */
    public void addEntryToDatabase(String user, String text){
        ContentValues values = new ContentValues();
        values.put(ModelDatabase.MESSAGE_USER, user);
        values.put(ModelDatabase.MESSAGE_TEXT, text);
        database.insert(ModelDatabase.TABLE_NAME, null, values);
    }

    /**
     * Get
     */
    public ArrayList<chatEntry> getAllEntries(){
        return sweepCursor(database.query(ModelDatabase.TABLE_NAME, allColumns, "true" + " like '%true%'", null, null, null, null));
    }

    public ArrayList<chatEntry> getEntriesByUser(String user){
        return sweepCursor(database.query(
                ModelDatabase.TABLE_NAME,
                allColumns,
                ModelDatabase.MESSAGE_USER + " like '%" + user + "%' AND " + "true" + " like '%true%'",
                null, null, null, null, null
        ));
    }

    public chatEntry getEntryById(int id){
        return sweepCursor(database.query(
                ModelDatabase.TABLE_NAME,
                allColumns,
                ModelDatabase.MESSAGE_ID + " like '%" + id + "%'",
                null, null, null, null
        )).get(0);
    }

    /**
     * Delete
     */
    public void deleteEntryById(String id){
        database.delete(
                ModelDatabase.TABLE_NAME,
                ModelDatabase.MESSAGE_TEXT + " like '%" + id + "%'",
                null
        );
    }

    /**
     * Additional Helpers
     */
    //Sweep Through Cursor and return a List of Entries
    private ArrayList<chatEntry> sweepCursor(Cursor cursor){
        ArrayList<chatEntry> entries = new ArrayList<chatEntry>();

        //Get to the beginning of the cursor
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            //Get the Kitty
            chatEntry entry = new chatEntry(
                    cursor.getString(1),
                    cursor.getString(2)
            );
            //Add the Kitty
            entries.add(entry);
            //Go on to the next Kitty
            cursor.moveToNext();
        }
        return entries;
    }

    //Get Writable Database - open the database
    public void open(){
        database = model.getWritableDatabase();
    }
}