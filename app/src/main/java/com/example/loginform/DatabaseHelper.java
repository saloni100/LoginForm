package com.example.loginform;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DatabaseHelper extends SQLiteOpenHelper {


    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "register.db";
    private static final String TABLE_NAME = "user";

    // User Table Columns names
    private static final String COLUMN_USER_ID = "user_id";
    private static final String COLUMN_USER_NAME = "user_name";
    private static final String COLUMN_USER_TYPE = "user_type";
    private static final String COLUMN_USER_PASSWORD = "user_password";

    // create table sql query
    private String CREATE_USER_TABLE = "CREATE TABLE " + TABLE_NAME + "("
            + COLUMN_USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_USER_NAME + " TEXT,"
            + COLUMN_USER_TYPE + " TEXT," + COLUMN_USER_PASSWORD + " TEXT" + ")";

    // drop table sql query
    private String DROP_USER_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_USER_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //Drop User Table if exist
        db.execSQL(DROP_USER_TABLE);
        // Create tables again
        onCreate(db);

    }

    public void addUser(String username, String password, String type) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_USER_NAME, username);
        values.put(COLUMN_USER_TYPE, type);
        values.put(COLUMN_USER_PASSWORD, password);
        // Inserting Row
        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    public boolean checkUserExist(String username, String password) {
        String[] columns = {COLUMN_USER_ID};
        SQLiteDatabase db = this.getReadableDatabase();
        String selection = COLUMN_USER_NAME + "=?" + " and " + COLUMN_USER_PASSWORD + "=?";
        String[] selectionArgs = {username, password};

        Cursor cursor = db.query(TABLE_NAME, columns, selection, selectionArgs, null, null, null);
        int count = cursor.getCount();
        cursor.close();
        db.close();

        if (count > 0) {
           return true;
        } else {
            return false;
        }
    }

    public Cursor fetch() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(DatabaseHelper.TABLE_NAME, new String[]{DatabaseHelper.COLUMN_USER_ID, DatabaseHelper.COLUMN_USER_NAME, DatabaseHelper.COLUMN_USER_TYPE, DatabaseHelper.COLUMN_USER_PASSWORD}, null, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }




}
