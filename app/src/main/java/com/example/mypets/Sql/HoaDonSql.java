package com.example.mypets.Sql;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.mypets.ModelClass.HoaDonClass;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class HoaDonSql {
    Sqlite sqlite;
    public HoaDonSql(Sqlite sqlite){
        this.sqlite = sqlite;
    }
    public long AddHoaDon(HoaDonClass hoaDonClass){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd");
        SQLiteDatabase sqLiteDatabase = sqlite.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("mahoadon",hoaDonClass.getMahoadon());
        contentValues.put("tentk",hoaDonClass.getTentk());
        contentValues.put("makh",hoaDonClass.getMakh());
        contentValues.put("ngaymua",simpleDateFormat.format(hoaDonClass.getNgaymua()));
        contentValues.put("tongtien",0);
        return sqLiteDatabase.insert("hoadon",null,contentValues);
    }

    public ArrayList<HoaDonClass> GetAllHoaDon() throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd");
        SQLiteDatabase sqLiteDatabase = sqlite.getReadableDatabase();
        String select = "SELECT * FROM hoadon";
        ArrayList<HoaDonClass> arrHd = new ArrayList<>();
        Cursor cursor = sqLiteDatabase.rawQuery(select,null);
        if(cursor.getCount()>0){
            cursor.moveToFirst();
            while (cursor.isAfterLast()==false){
                HoaDonClass hd = new HoaDonClass();
                hd.setMahoadon(cursor.getString(0));
                hd.setTentk(cursor.getString(1));
                hd.setMakh(cursor.getString(2));
                hd.setNgaymua(simpleDateFormat.parse(cursor.getString(3)));
                hd.setTongtien(cursor.getDouble(4));
                arrHd.add(hd);
                cursor.moveToNext();
            }
        }
        cursor.close();
        return arrHd;
    }

    public HoaDonClass GetHoaDon(String mahd) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd");
        SQLiteDatabase sqLiteDatabase = sqlite.getWritableDatabase();
        String select = "SELECT * FROM hoadon WHERE mahoadon = ?";
        HoaDonClass hd = new HoaDonClass();
        Cursor cursor = sqLiteDatabase.rawQuery(select,new String[]{mahd});
        if(cursor.getCount()>0){
            cursor.moveToFirst();
            hd.setMahoadon(cursor.getString(0));
            hd.setTentk(cursor.getString(1));
            hd.setMakh(cursor.getString(2));
            hd.setNgaymua(simpleDateFormat.parse(cursor.getString(3)));
            hd.setTongtien(cursor.getDouble(4));
        }
        cursor.close();
        return hd;
    }

    public void UpdateTongtien(Double tongtien,HoaDonClass hoadon){
        SQLiteDatabase sqLiteDatabase = sqlite.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("tongtien",tongtien);
        sqLiteDatabase.update("hoadon",contentValues,"mahoadon=?",new String[]{hoadon.getMahoadon()});
    }

    public void DeleteHd(String mahd){
        SQLiteDatabase sqLiteDatabase = sqlite.getReadableDatabase();
        sqLiteDatabase.delete("hoadon","mahoadon=?",new String[]{mahd});
    }
}
