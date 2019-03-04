package com.example.nguyenngoclinh.sqlitedatabase2demomanagerstudent.database;

/*Lop nay se quan ly toan bo database*/

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseManager {
    //b3 : Dinh nghia cac thong tin lien quan toi database
    public final String DB_NAME = "StudentManagement";
    public final String TB_NAME = "Student";
    public final int DB_VERSION = 1;
    private SQLiteDatabase database;

    //buoc 4 : xay dung 1 lop de tao cac bang trong database, update database khi nang cap version

    public class OpenHelper extends SQLiteOpenHelper{

        public OpenHelper(Context context) {
            super(context, DB_NAME, null, DB_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            String createDatabase = "CREATE TABLE IF NOT EXISTS Student(_id INTEGER PRIMARY KEY AUTOINCREMENT, Name TEXT, Phone TEXT)";
            db.execSQL(createDatabase);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            String dropTable = "DROP TABLE IF EXISTS Student";
            db.execSQL(dropTable);
            onCreate(db);
        }
    }

    //buoc 5 : xay dung san cac phuong thuc de lam viec voi database

    //Insert
    public void insertStudent(String name, String phone){
        ContentValues insertValues = new ContentValues();
        insertValues.put("Name", name);
        insertValues.put("Phone", phone);
        database.insertOrThrow(TB_NAME, null, insertValues);
        // nullColumnHack tuc la ngoai thang id ma ta ko truyen thi nhung thang ta khong truyen vao thi mac dinh la null

    }


    //Update
    public void updateStudent(String name, String phone, int id){
        ContentValues updateValues = new ContentValues();
        updateValues.put("Name", name);
        updateValues.put("Phone", phone);
        database.update(TB_NAME, updateValues, "_id = " + id, null);
    }

    //Select
    public Cursor getInforStudent(){
        return database.query(TB_NAME,
                null,
                null,
                null,
                null,
                null,
                null);
    }


    //Delete
    public void deleteStudent(int id){
        database.delete(TB_NAME, "_id = " + id, null);
    }

    // buoc 6 : xay dung phuong thuc khoi tao database
    public DatabaseManager(Context context){
        OpenHelper helper = new OpenHelper(context);
        database = helper.getWritableDatabase();
    }
}
