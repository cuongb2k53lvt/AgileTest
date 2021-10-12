package com.example.mypets.Sql;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.mypets.ModelClass.NhanVienClass;
import com.example.mypets.Sql.Sqlite;

import java.util.ArrayList;

public class NhanVienSql {
    Sqlite sqlite;
    public NhanVienSql(Sqlite sqlite){
        this.sqlite= sqlite;
    }

    public void AddNhanVien(NhanVienClass nhanVienClass){
        SQLiteDatabase sqLiteDatabase = sqlite.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("tentk",nhanVienClass.getTentk());
        contentValues.put("tennv",nhanVienClass.getTennv());
        contentValues.put("mk",nhanVienClass.getMk());
        contentValues.put("sdt",nhanVienClass.getSdt());
        contentValues.put("diachi",nhanVienClass.getDiachi());
        contentValues.put("trangthai","active");
        sqLiteDatabase.insert("nhanvien",null,contentValues);
    }

    public ArrayList<NhanVienClass> GetAllNhanVien(){
        SQLiteDatabase sqLiteDatabase = sqlite.getReadableDatabase();
        ArrayList<NhanVienClass> arrNv = new ArrayList<>();
        String select = "SELECT * FROM nhanvien";
        Cursor cursor = sqLiteDatabase.rawQuery(select,null);
        if(cursor.getCount()>0){
            cursor.moveToFirst();
            while (cursor.isAfterLast()==false){
                NhanVienClass nhanVienClass = new NhanVienClass();
                nhanVienClass.setTentk(cursor.getString(0));
                nhanVienClass.setTennv(cursor.getString(1));
                nhanVienClass.setMk(cursor.getString(2));
                nhanVienClass.setSdt(cursor.getString(3));
                nhanVienClass.setDiachi(cursor.getString(4));
                nhanVienClass.setTrangthai(cursor.getString(5));
                arrNv.add(nhanVienClass);
                cursor.moveToNext();
            }
        }
        cursor.close();
        return arrNv;
    }

    public void TrangThaiActive(NhanVienClass nhanVienClass){
        SQLiteDatabase sqLiteDatabase = sqlite.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("trangthai","active");
        sqLiteDatabase.update("nhanvien",contentValues,"tentk=?",new String[]{nhanVienClass.getTentk()});
    }
    public void TrangThaiInactive(NhanVienClass nhanVienClass){
        SQLiteDatabase sqLiteDatabase = sqlite.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("trangthai","inactive");
        sqLiteDatabase.update("nhanvien",contentValues,"tentk=?",new String[]{nhanVienClass.getTentk()});
    }

}
