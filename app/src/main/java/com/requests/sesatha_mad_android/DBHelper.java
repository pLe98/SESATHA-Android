package com.requests.sesatha_mad_android;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

import java.util.List;

public class DBHelper extends SQLiteOpenHelper {

    //inner class for defining column names
    public static class Items implements BaseColumns{
        public static final String TABLE_NAME = "items";
        public static final String COL_ITEM_NAME = "name";
        public static final String COL_DESC = "description";
        public static final String PRICE = "price";
    }
    //end of inner class Items


    public DBHelper( Context context) {
        super(context, "ItemInfo.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String COMMAND = "CREATE TABLE "+ Items.TABLE_NAME + " ("+
                Items._ID+ " INTEGER PRIMARY KEY,"+
                Items.COL_ITEM_NAME+" TEXT,"+
                Items.COL_DESC+" TEXT,"+
                Items.PRICE+ " REAL )";
        //CREATE TABLE items(_id INTEGER PRIMARY KEY, name TEXT, desc TEXT, price REAL)

        db.execSQL(COMMAND); //create items table
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE if exists items");
    }

    //Create
    public void createItem(String name, String desc, float price){
        SQLiteDatabase db = getWritableDatabase(); //get db in write mode

        ContentValues values = new ContentValues();
        values.put(Items.COL_ITEM_NAME,name);
        values.put(Items.COL_DESC,desc);
        values.put(Items.PRICE,price);

        long rowId = db.insert(Items.TABLE_NAME,null,values); //insert data into table while returning the pk value

    }

    //Update
    public void updateItem(String name,String desc,float price){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Items.COL_DESC,desc);
        values.put(Items.PRICE,price);

        String selection = Items.COL_ITEM_NAME + " LIKE ?";
        String[] selArgs = {name}; //replaces selection string's  ? with name var

        int count = db.update(Items.TABLE_NAME,values,selection,selArgs);
    }

    //Delete
    public void deleteItem(String name){
        SQLiteDatabase db = getReadableDatabase();
        String selection = Items.COL_ITEM_NAME + " LIKE ?";
        String[] selArgs = {name};

        db.delete(Items.TABLE_NAME,selection,selArgs);
    }

    //Read
    public Cursor getAllItems(){
        SQLiteDatabase db = getReadableDatabase();

        Cursor cursor = db.query(Items.TABLE_NAME,null,null,null,null,null,null);
        //To sort by name, replace the above orderBy parameter with String "name DESC"

        return cursor;

    }
}
