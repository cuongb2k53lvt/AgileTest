package com.example.mypets.Sql;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.widget.ArrayAdapter;

import com.example.mypets.ModelClass.PetClass;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class PetSql {
    Sqlite sqlite;
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
    public PetSql(Sqlite sqlite){
        this.sqlite = sqlite;
    }
    public void AddPet(PetClass pet){
        SQLiteDatabase sqLiteDatabase = sqlite.getWritableDatabase();
        String insert ="INSERT INTO pet VALUES (?,?,?,?,?,?,?,?,?,?)";
        SQLiteStatement sqLiteStatement = sqLiteDatabase.compileStatement(insert);
        sqLiteStatement.clearBindings();
        sqLiteStatement.bindString(1,pet.getMaPet());
        sqLiteStatement.bindString(2,pet.getTenPet());
        sqLiteStatement.bindString(3,pet.getTenLoai());
        sqLiteStatement.bindString(4,pet.getGioiTinh());
        sqLiteStatement.bindString(5,pet.getTuoi());
        sqLiteStatement.bindString(6,pet.getMoTa());
        sqLiteStatement.bindString(7,simpleDateFormat.format(pet.getNgayNhap()));
        sqLiteStatement.bindDouble(8,pet.getGiaTien());
        sqLiteStatement.bindBlob(9,pet.getAnh());
        sqLiteStatement.bindString(10,pet.getTrangThai());
        sqLiteStatement.executeInsert();
    }

    public ArrayList<PetClass> GetAllPet() throws ParseException {
        SQLiteDatabase sqLiteDatabase=sqlite.getReadableDatabase();
        ArrayList<PetClass> arrPet = new ArrayList<>();
        String select = "SELECT * FROM pet";
        Cursor cursor= sqLiteDatabase.rawQuery(select,null);
        if(cursor.getCount()>0){
            cursor.moveToFirst();
            while (cursor.isAfterLast()==false){
                PetClass pet = new PetClass();
                pet.setMaPet(cursor.getString(0));
                pet.setTenPet(cursor.getString(1));
                pet.setTenLoai(cursor.getString(2));
                pet.setGioiTinh(cursor.getString(3));
                pet.setTuoi(cursor.getString(4));
                pet.setMoTa(cursor.getString(5));
                pet.setNgayNhap(simpleDateFormat.parse(cursor.getString(6)));
                pet.setGiaTien(cursor.getDouble(7));
                pet.setAnh(cursor.getBlob(8));
                pet.setTrangThai(cursor.getString(9));
                arrPet.add(pet);
                cursor.moveToNext();
            }
        }
        cursor.close();
        return arrPet;
    }

    public ArrayList<String> GetAllMaPet(){
        SQLiteDatabase sqLiteDatabase = sqlite.getWritableDatabase();
        String select = "SELECT mapet FROM pet";
        ArrayList<String> arrMapet = new ArrayList<>();
        Cursor cursor = sqLiteDatabase.rawQuery(select,null);
        if(cursor.getCount()>0){
            cursor.moveToFirst();
            while (cursor.isAfterLast()== false){
                arrMapet.add(cursor.getString(0));
                cursor.moveToNext();
            }
        }
        cursor.close();
        return arrMapet;
    }

    public ArrayList<PetClass> GetPetChuaBan() throws ParseException {
        SQLiteDatabase sqLiteDatabase = sqlite.getReadableDatabase();
        String select = "SELECT * FROM pet WHERE trangthai = 'Chưa bán'";
        ArrayList<PetClass> arrPet = new ArrayList<>();
        Cursor cursor = sqLiteDatabase.rawQuery(select,null);
        if(cursor.getCount()>0){
            cursor.moveToFirst();
            while (cursor.isAfterLast()==false){
                PetClass pet = new PetClass();
                pet.setMaPet(cursor.getString(0));
                pet.setTenPet(cursor.getString(1));
                pet.setTenLoai(cursor.getString(2));
                pet.setGioiTinh(cursor.getString(3));
                pet.setTuoi(cursor.getString(4));
                pet.setMoTa(cursor.getString(5));
                pet.setNgayNhap(simpleDateFormat.parse(cursor.getString(6)));
                pet.setGiaTien(cursor.getDouble(7));
                pet.setAnh(cursor.getBlob(8));
                pet.setTrangThai(cursor.getString(9));
                arrPet.add(pet);
                cursor.moveToNext();
            }
        }
        cursor.close();
        return arrPet;
    }

    public void XoaPet(String mapet){
        SQLiteDatabase sqLiteDatabase = sqlite.getReadableDatabase();
        sqLiteDatabase.delete("pet","mapet=?",new String[]{mapet});
    }

    public ArrayList<PetClass> GetCho() throws ParseException {
        SQLiteDatabase sqLiteDatabase = sqlite.getReadableDatabase();
        String select = "SELECT * FROM pet WHERE tenloai = 'Chó'";
        ArrayList<PetClass> arrPet = new ArrayList<>();
        Cursor cursor = sqLiteDatabase.rawQuery(select,null);
        if(cursor.getCount()>0){
            cursor.moveToFirst();
            while (cursor.isAfterLast()==false){
                PetClass pet = new PetClass();
                pet.setMaPet(cursor.getString(0));
                pet.setTenPet(cursor.getString(1));
                pet.setTenLoai(cursor.getString(2));
                pet.setGioiTinh(cursor.getString(3));
                pet.setTuoi(cursor.getString(4));
                pet.setMoTa(cursor.getString(5));
                pet.setNgayNhap(simpleDateFormat.parse(cursor.getString(6)));
                pet.setGiaTien(cursor.getDouble(7));
                pet.setAnh(cursor.getBlob(8));
                pet.setTrangThai(cursor.getString(9));
                arrPet.add(pet);
                cursor.moveToNext();
            }
        }
        cursor.close();
        return arrPet;
    }
    public ArrayList<PetClass> GetMeo() throws ParseException {
        SQLiteDatabase sqLiteDatabase = sqlite.getReadableDatabase();
        String select = "SELECT * FROM pet WHERE tenloai = 'Mèo'";
        ArrayList<PetClass> arrPet = new ArrayList<>();
        Cursor cursor = sqLiteDatabase.rawQuery(select,null);
        if(cursor.getCount()>0){
            cursor.moveToFirst();
            while (cursor.isAfterLast()==false){
                PetClass pet = new PetClass();
                pet.setMaPet(cursor.getString(0));
                pet.setTenPet(cursor.getString(1));
                pet.setTenLoai(cursor.getString(2));
                pet.setGioiTinh(cursor.getString(3));
                pet.setTuoi(cursor.getString(4));
                pet.setMoTa(cursor.getString(5));
                pet.setNgayNhap(simpleDateFormat.parse(cursor.getString(6)));
                pet.setGiaTien(cursor.getDouble(7));
                pet.setAnh(cursor.getBlob(8));
                pet.setTrangThai(cursor.getString(9));
                arrPet.add(pet);
                cursor.moveToNext();
            }
        }
        cursor.close();
        return arrPet;
    }

    public PetClass GetPet(String mapet) throws ParseException {
        SQLiteDatabase sqLiteDatabase = sqlite.getReadableDatabase();
        String select = "SELECT * FROM pet WHERE mapet = ?";
        PetClass pet = new PetClass();
        Cursor cursor = sqLiteDatabase.rawQuery(select,new String[]{mapet});
        if(cursor.getCount()>0){
            cursor.moveToFirst();
            pet.setMaPet(cursor.getString(0));
            pet.setTenPet(cursor.getString(1));
            pet.setTenLoai(cursor.getString(2));
            pet.setGioiTinh(cursor.getString(3));
            pet.setTuoi(cursor.getString(4));
            pet.setMoTa(cursor.getString(5));
            pet.setNgayNhap(simpleDateFormat.parse(cursor.getString(6)));
            pet.setGiaTien(cursor.getDouble(7));
            pet.setAnh(cursor.getBlob(8));
            pet.setTrangThai(cursor.getString(9));
        }
        cursor.close();
        return pet;
    }

    public void UpdateTrangThai(String mapet){
        SQLiteDatabase sqLiteDatabase = sqlite.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("trangthai","Đã bán");
        sqLiteDatabase.update("pet",contentValues,"mapet=?",new String[]{mapet});
    }
}
