package com.example.mypets.Sql;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.mypets.ModelClass.KhachHangClass;

import java.util.ArrayList;

public class KhachHangSql {
    Sqlite sqlite;
    public KhachHangSql(Sqlite sqlite){
        this.sqlite = sqlite;
    }
    public int AddKhachHang(KhachHangClass khachHangClass){
        SQLiteDatabase sqLiteDatabase = sqlite.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("makh",khachHangClass.getMaKh());
        contentValues.put("tenkh",khachHangClass.getTenKh());
        contentValues.put("sdt",khachHangClass.getSdt());
        sqLiteDatabase.insert("khachhang",null,contentValues);
        return  1;
    }

    public ArrayList<KhachHangClass> GetAllKhachHang(){
        SQLiteDatabase sqLiteDatabase = sqlite.getReadableDatabase();
        String select = "SELECT * FROM khachhang";
        ArrayList<KhachHangClass> arrKh = new ArrayList<>();
        Cursor cursor = sqLiteDatabase.rawQuery(select,null);
        if(cursor.getCount()>0){
            cursor.moveToFirst();
            while (cursor.isAfterLast()==false){
                KhachHangClass khachHangClass = new KhachHangClass();
                khachHangClass.setMaKh(cursor.getString(0));
                khachHangClass.setTenKh(cursor.getString(1));
                khachHangClass.setSdt(cursor.getString(2));
                arrKh.add(khachHangClass);
                cursor.moveToNext();
            }
        }
        cursor.close();
        return arrKh;
    }

    public void XoaKhachHang(String makh){
        SQLiteDatabase sqLiteDatabase = sqlite.getReadableDatabase();
        sqLiteDatabase.delete("khachhang","makh=?",new String[]{makh});
    }
}
