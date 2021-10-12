package com.example.mypets.Sql;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.mypets.ModelClass.HoaDonCtClass;

import java.util.ArrayList;

public class HoaDonCtSql {
    Sqlite sqlite;
    public HoaDonCtSql(Sqlite sqlite){
        this.sqlite = sqlite;
    }
    public long AddHdct(HoaDonCtClass hdct){
        SQLiteDatabase sqLiteDatabase = sqlite.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("mahd",hdct.getMahd());
        contentValues.put("mapet",hdct.getMapet());
        return sqLiteDatabase.insert("hoadonct",null,contentValues);
    }

    public ArrayList<HoaDonCtClass> GetAllHdct(){
        SQLiteDatabase sqLiteDatabase = sqlite.getReadableDatabase();
        ArrayList<HoaDonCtClass> arrHdct = new ArrayList<>();
        String select = "SELECT * FROM hoadonct";
        Cursor cursor = sqLiteDatabase.rawQuery(select,null);
        if(cursor.getCount()>0){
            cursor.moveToFirst();
            while (cursor.isAfterLast()==false){
                HoaDonCtClass hdct = new HoaDonCtClass();
                hdct.setMahdct(cursor.getInt(0));
                hdct.setMahd(cursor.getString(1));
                hdct.setMapet(cursor.getString(2));
                arrHdct.add(hdct);
                cursor.moveToNext();
            }
        }
        cursor.close();
        return arrHdct;
    }

    public ArrayList<HoaDonCtClass> GetAllHdctTheoHd(String mahd){
        SQLiteDatabase sqLiteDatabase = sqlite.getReadableDatabase();
        ArrayList<HoaDonCtClass> arrHdct = new ArrayList<>();
        String select = "SELECT * FROM hoadonct WHERE mahd=?";
        Cursor cursor = sqLiteDatabase.rawQuery(select,new String[]{mahd});
        if(cursor.getCount()>0){
            cursor.moveToFirst();
            while (cursor.isAfterLast()==false){
                HoaDonCtClass hdct = new HoaDonCtClass();
                hdct.setMahdct(cursor.getInt(0));
                hdct.setMahd(cursor.getString(1));
                hdct.setMapet(cursor.getString(2));
                arrHdct.add(hdct);
                cursor.moveToNext();
            }
        }
        cursor.close();
        return arrHdct;
    }

    public void DeleteHdct(String mahdct){
        SQLiteDatabase sqLiteDatabase = sqlite.getReadableDatabase();
        sqLiteDatabase.delete("hoadonct","mahdct=?",new String[]{mahdct});
    }
}
