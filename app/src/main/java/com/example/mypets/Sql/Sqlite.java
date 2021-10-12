package com.example.mypets.Sql;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Sqlite extends SQLiteOpenHelper {
    Context context;
    public Sqlite(Context context){
        super(context,"mypet4.sql",null,1);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String create_nhanvien = "CREATE TABLE nhanvien "+
                "(tentk varchar(50) primary key not null,"+
                "tennv nvarchar(200),"+
                "mk varchar(50),"+
                "sdt varchar(50),"+
                "diachi nvarchar(250),"+
                "trangthai nvarchar(100))";
        db.execSQL(create_nhanvien);
        String create_pet = "CREATE TABLE pet "+
                "(mapet varchar(50) primary key not null,"+
                "tenpet nvarchar(250) not null,"+
                "tenloai nvarchar(200) not null,"+
                "gioitinh nvarchar(50),"+
                "tuoi nvarchar(200),"+
                "mota nvarchar(250),"+
                "ngaynhap date not null,"+
                "giatien double not null,"+
                "anh blob not null,"+
                "trangthai nvarchar not null)";
        db.execSQL(create_pet);
        String create_khachhang = "CREATE TABLE khachhang "+
                "(makh varchar(50) primary key not null,"+
                "tenkh nvarchar(250) not null,"+
                "sdt varchar(50) not null)";
        db.execSQL(create_khachhang);
        String create_hoadon = "CREATE TABLE hoadon "+
                "(mahoadon varchar(50) primary key,"+
                "tentk varchar(50) not null,"+
                "makh varchar(50) not null,"+
                "ngaymua date not null,"+
                "tongtien double not null,"+
                "foreign key(tentk) references nhanvien(tentk),"+
                "foreign key(makh) references khachhang(makh))";
        db.execSQL(create_hoadon);
        String create_hdct = "CREATE TABLE hoadonct "+
                "(mahdct INTEGER primary key autoincrement,"+
                "mahd varchar(50) not null,"+
                "mapet varchar(50) not null,"+
                "foreign key(mahd) references hoadon(mahoadon),"+
                "foreign key(mapet) references pet(mapet))";
        db.execSQL(create_hdct);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
