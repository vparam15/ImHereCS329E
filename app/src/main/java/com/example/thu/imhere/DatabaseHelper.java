package com.example.thu.imhere;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Varun Parameswaran on 4/13/2016.
 */
public class DatabaseHelper extends SQLiteOpenHelper {

    // declare the DB name
    public static final String DATABASE_NAME = "Users.db";

    // declare the TABLE name that will be part of the DB
    public static final String TABLE_NAME = "user_table";

    // declare the COLUMNS of the TABLE
    public static final String COL_1 = "USER_ID";
    public static final String COL_2 = "USERNAME";
    public static final String COL_3 = "PHONE";
    public static final String COL_4 = "PASSWORD";

    // this is referencing the java class that will manage the SQL DB
    public DatabaseHelper(Context context) {

        // whenever the constructor below is called, our DB will now be created
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        // this is the execute sql query method that takes a string sql query and executes this query
        db.execSQL("create table " + TABLE_NAME + " (USER_ID INTEGER PRIMARY KEY AUTOINCREMENT, USERNAME TEXT PRIMARY KEY, PHONE TEXT, PASSWORD TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        // upgrade the table if version number is increased and call onCreate to create a new DB
        db.execSQL("DROP TABLE IF EXISTS" + TABLE_NAME);
        onCreate(db);

    }

    public boolean insertData(String username, String phone, String password) {

        // Open the database for reading and writing
        SQLiteDatabase db = this.getWritableDatabase();

        // This class is used to store a set of values that a ContentResolver can process.
        ContentValues contentValues = new ContentValues();

        // you need to specify the column and the data for that column
        contentValues.put(COL_2, username);
        contentValues.put(COL_3, phone);
        contentValues.put(COL_4, password);

        // need to give this the table name and the content values
        long result = db.insert(TABLE_NAME,null,contentValues);

        // method will return -1 if the insert did not work
        if (result == -1)
            return false;
        else
            return true;
    }

    public String checkLogin(String username)
    {
        // Open the database for reading and writing
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.query(TABLE_NAME, null, "USERNAME=?", new String[]{username}, null, null, null);
        if(cursor.getCount()<1) // username does not exist
        {
            cursor.close();
            return "Password not found";
        }
        cursor.moveToFirst();
        String password= cursor.getString(cursor.getColumnIndex("PASSWORD"));
        cursor.close();
        return password;
    }

    public String IdFromUsername(String username)
    {
        // Open the database for reading and writing
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.query(TABLE_NAME, null, "USERNAME=?", new String[]{username}, null, null, null);
        if(cursor.getCount()<1) // username does not exist
        {
            cursor.close();
            return "n/a";
        }
        cursor.moveToFirst();
        String user_id= cursor.getString(cursor.getColumnIndex("USER_ID"));
        cursor.close();
        return user_id;
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

    public Cursor getOneData(String Id) {
        // Open the database for reading and writing
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor res = db.rawQuery("select * from " + TABLE_NAME + " where USER_ID = " + Id, null);
        return res;
    }

    public boolean updateData(String Id, String username, String phone, String password){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1, Id);
        contentValues.put(COL_2, username);
        contentValues.put(COL_3, phone);
        contentValues.put(COL_4, password);
        db.update(TABLE_NAME, contentValues, "USER_ID = ?", new String[]{Id});
        return true;
    }

    public Integer deleteData(String Id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME, "USER_ID = ?", new String[] {Id});
    }
}
