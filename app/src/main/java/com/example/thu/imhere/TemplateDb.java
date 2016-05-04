package com.example.thu.imhere;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Varun Parameswaran on 4/13/2016.
 */
public class TemplateDb extends SQLiteOpenHelper {

    // declare the DB name
    public static final String DATABASE_NAME = "Templates.db";

    // declare the TABLE name that will be part of the DB
    public static final String TABLE_NAME = "template_table";

    // declare the COLUMNS of the TABLE
    public static final String COL_1 = "TEMPLATE_ID";
    public static final String COL_2 = "NAME";
    public static final String COL_3 = "BEG";
    public static final String COL_4 = "END";
    public static final String COL_5 = "USERNAME";

    // this is referencing the java class that will manage the SQL DB
    public TemplateDb(Context context) {

        // whenever the constructor below is called, our DB will now be created
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        // this is the execute sql query method that takes a string sql query and executes this query
        db.execSQL("create table " + TABLE_NAME + " (TEMPLATE_ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "NAME TEXT, BEG TEXT, END TEXT, USERNAME TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        // upgrade the table if version number is increased and call onCreate to create a new DB
        db.execSQL("DROP TABLE IF EXISTS" + TABLE_NAME);
        onCreate(db);

    }

    public boolean insertData(String name, String beg, String end, String username) {

        // Open the database for reading and writing
        SQLiteDatabase db = this.getWritableDatabase();

        // This class is used to store a set of values that a ContentResolver can process.
        ContentValues contentValues = new ContentValues();

        // you need to specify the column and the data for that column
        contentValues.put(COL_2, name);
        contentValues.put(COL_3, beg);
        contentValues.put(COL_4, end);
        contentValues.put(COL_5, username);

        // need to give this the table name and the content values
        long result = db.insert(TABLE_NAME, null, contentValues);

        // method will return -1 if the insert did not work
        if (result == -1)
            return false;
        else
            return true;
    }

    public Cursor getAllData() {
        // Open the database for reading and writing
        SQLiteDatabase db = this.getWritableDatabase();

        // A Cursor represents the result of a query and basically points to one row of the query result.
        // This way Android can buffer the query results efficiently; as it does not have to load all data into memory.
        // the "*" means select "all"
        Cursor res = db.rawQuery("select * from " + TABLE_NAME,null);
        return res;
    }

    public ArrayList<String> getAllTemplates() {
        ArrayList<String> TemplateList = new ArrayList<String>();

        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_NAME;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                TemplateList.add(cursor.getString(0));
            } while (cursor.moveToNext());
        }

        // return contact list
        return TemplateList;
    }

    public boolean updateData(String Id, String name, String beg, String end, String username){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1, Id);
        contentValues.put(COL_2, name);
        contentValues.put(COL_3, beg);
        contentValues.put(COL_4, end);
        contentValues.put(COL_5, username);
        db.update(TABLE_NAME, contentValues, "TEMPLATE_ID = ?", new String[]{Id});
        return true;
    }

    public Integer deleteData(String Id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME, "TEMPLATE_ID = ?", new String[] {Id});
    }
}
