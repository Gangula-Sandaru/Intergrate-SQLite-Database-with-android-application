package com.gangula.crudoperation;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.google.android.material.tabs.TabLayout;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "students.db";
    public static final String TABLE_NAME= "student_table";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "NAME";
    public static final String COL_3 = "COURSE";
    public static final String COL_4 = "MARK";

    public DatabaseHelper(@Nullable Context context) {
        super(context,DATABASE_NAME, null, 1);

        // created a object  for created the database
        SQLiteDatabase db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        // SQL query for the database
        sqLiteDatabase.execSQL("create table " + TABLE_NAME + "(ID INTEGER primary key autoincrement, " + "NAME TEXT, COURSE TEXT, MARK INTERGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);

    }

    public boolean insertData(String name, String course, String marks){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, name);
        contentValues.put(COL_3, course);
        contentValues.put(COL_4, marks);

        long result  = db.insert(TABLE_NAME, null, contentValues);
        if(result == -1){
            return false;
        }else{
            return true;
        }
    }
}
