package com.example.mypets;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.mypets.ModelClass.PetClass;
import com.example.mypets.Sql.PetSql;
import com.example.mypets.Sql.Sqlite;
import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.io.ByteArrayOutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

public class ThemPetAct extends AppCompatActivity {
    ImageView imgThemPet, imgNgayNhap;
    EditText edtMaPet, edtTenpet, edtTuoi, edtNgayNhap, edtGiaTien, edtMota;
    Spinner spLoai, spGioiTinh;
    Sqlite sqlite;
    String loai, gioiTinh;
    ArrayList<String> arrPet = new ArrayList<>();
    ArrayList<String> arrGioiTinh = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_pet_layout);
        imgThemPet = findViewById(R.id.imgThem);
        imgNgayNhap = findViewById(R.id.imgThemNgayNhapPet);
        edtMaPet = findViewById(R.id.edtMaPetThem);
        edtTenpet = findViewById(R.id.edtTenPetThem);
        edtTuoi = findViewById(R.id.edtTuoiThem);
        edtNgayNhap = findViewById(R.id.edtNgayNhapThem);
        edtGiaTien = findViewById(R.id.edtGiaTienThem);
        edtMota = findViewById(R.id.edtMoTaThem);
        spLoai = findViewById(R.id.spLoaiPet);
        spGioiTinh = findViewById(R.id.spGioiTinh);
        sqlite = new Sqlite(ThemPetAct.this);
        arrPet.add("Chó");
        arrPet.add("Mèo");
        ArrayAdapter arrayAdapter = new ArrayAdapter(ThemPetAct.this, android.R.layout.simple_spinner_dropdown_item,arrPet);
        spLoai.setAdapter(arrayAdapter);
        spLoai.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                loai = arrPet.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        arrGioiTinh.add("Đực");
        arrGioiTinh.add("Cái");
        ArrayAdapter arrayAdapter1 = new ArrayAdapter(ThemPetAct.this, android.R.layout.simple_spinner_dropdown_item,arrGioiTinh);
        spGioiTinh.setAdapter(arrayAdapter1);
        spGioiTinh.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                gioiTinh = arrGioiTinh.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        imgNgayNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                int day = calendar.get(Calendar.DAY_OF_MONTH);
                int month = calendar.get(Calendar.MONTH);
                int year = calendar.get(Calendar.YEAR);
                DatePickerDialog datePickerDialog = new DatePickerDialog(ThemPetAct.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd");
                        Calendar calendar1 = new GregorianCalendar(year, month, dayOfMonth);
                        edtNgayNhap.setText(simpleDateFormat.format(calendar1.getTime()));
                    }
                },year,month,day);
                datePickerDialog.show();
            }
        });
        imgThemPet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requestPermission();
            }
        });

    }

    private void requestPermission(){
        PermissionListener permissionlistener = new PermissionListener() {
            @Override
            public void onPermissionGranted() {
                openImagePicker();
            }

            @Override
            public void onPermissionDenied(List<String> deniedPermissions) {
                Toast.makeText(ThemPetAct.this, "Permission Denied\n" + deniedPermissions.toString(), Toast.LENGTH_SHORT).show();
            }
        };
        TedPermission.with(this)
                .setPermissionListener(permissionlistener)
                .setDeniedMessage("If you reject permission,you can not use this service\n\nPlease turn on permissions at [Setting] > [Permission]")
                .setPermissions(Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .check();
    }

    private void openImagePicker() {
        CropImage.activity(null).setGuidelines(CropImageView.Guidelines.ON).start(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == RESULT_OK) {
                Uri resultUri = result.getUri();
                imgThemPet.setImageURI(resultUri);
            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                Exception error = result.getError();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    public void Huy(View view) {
        finish();
    }

    public void Them(View view) throws ParseException {
        BitmapDrawable bitmapDrawable =(BitmapDrawable) imgThemPet.getDrawable();
        Bitmap bitmap = bitmapDrawable.getBitmap();
        ByteArrayOutputStream byteArray = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,40,byteArray);
        byte[] anh = byteArray.toByteArray();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd");
        PetSql petSql = new PetSql(sqlite);
        PetClass pet = new PetClass();
        pet.setMaPet(edtMaPet.getText().toString());
        pet.setTenPet(edtTenpet.getText().toString());
        pet.setTenLoai(loai);
        pet.setGioiTinh(gioiTinh);
        pet.setTuoi(edtTuoi.getText().toString());
        pet.setMoTa(edtMota.getText().toString());
        pet.setNgayNhap(simpleDateFormat.parse(edtNgayNhap.getText().toString()));
        pet.setGiaTien(Double.parseDouble(edtGiaTien.getText().toString()));
        pet.setTrangThai("Chưa bán");
        pet.setAnh(anh);
        petSql.AddPet(pet);
    }
}