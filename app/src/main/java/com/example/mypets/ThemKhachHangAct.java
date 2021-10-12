package com.example.mypets;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mypets.ModelClass.KhachHangClass;
import com.example.mypets.Sql.KhachHangSql;
import com.example.mypets.Sql.Sqlite;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;

public class ThemKhachHangAct extends AppCompatActivity {
    EditText edtMakh, edtTenKh, edtSdt;
    TextInputLayout tilMaKhachHang, tilTenKhachHang, tilSoDienThoai;
    Sqlite sqlite;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them_khach_hang);
        edtMakh = findViewById(R.id.edtMaKh);
        edtTenKh = findViewById(R.id.edtTenKhachHang);
        edtSdt = findViewById(R.id.edtSdt);
        tilMaKhachHang = findViewById(R.id.tilMaKhachHang);
        tilTenKhachHang = findViewById(R.id.tilTenKhachHang);
        tilSoDienThoai = findViewById(R.id.tilSoDienThoai);
        sqlite = new Sqlite(ThemKhachHangAct.this);
    }

    public void HuyKhachHang(View view) {
        finish();
    }

    public void ThemHoaDon(View view) {
        String maPet = getIntent().getExtras().getString("MAPET");
        String taiKhoan = getIntent().getExtras().getString("TAIKHOAN");
        KhachHangSql khachHangSql = new KhachHangSql(sqlite);
        KhachHangClass khachHang = new KhachHangClass();
        khachHang.setMaKh(tilMaKhachHang.getEditText().getText().toString());
        khachHang.setTenKh(tilTenKhachHang.getEditText().getText().toString());
        khachHang.setSdt(tilSoDienThoai.getEditText().getText().toString());

        if (validate() > 0) {
            if (khachHangSql.AddKhachHang(khachHang) > 0) {
                Toast.makeText(getApplicationContext(), "Thêm thành công",
                        Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(ThemKhachHangAct.this, ThemHoaDonAct.class);
                Bundle bundle = new Bundle();
                bundle.putString("TAIKHOAN", taiKhoan);
                bundle.putString("MAPET", maPet);
                bundle.putString("MAKH", khachHang.getMaKh());
                intent.putExtras(bundle);
                startActivity(intent);
            } else {
                Toast.makeText(getApplicationContext(), "Thêm thất bại",
                        Toast.LENGTH_SHORT).show();
            }
        }
    }

    private int validate(){
        int check = 1;
        boolean checkMakh = true;
        KhachHangSql khachHangSql = new KhachHangSql(sqlite);
        ArrayList<KhachHangClass> arrKh = khachHangSql.GetAllKhachHang();
        for (int i=0;i<arrKh.size();i++){
            if(edtMakh.getText().toString().equalsIgnoreCase(arrKh.get(i).getMaKh())){
                checkMakh=false;
            }
        }
        if (tilMaKhachHang.getEditText().getText().toString().isEmpty()) {
            tilMaKhachHang.setError("Không được để trống mã khách hàng");
            tilMaKhachHang.setErrorEnabled(true);
            check = -1;
        } else {
            if(checkMakh==false){
                tilMaKhachHang.setError("Mã khách hàng đã tồn tại");
                tilMaKhachHang.setErrorEnabled(true);
                check = -1;
            } else {
                tilMaKhachHang.setErrorEnabled(false);
            }
        }
        if (tilTenKhachHang.getEditText().getText().toString().isEmpty()) {
            tilTenKhachHang.setError("Không được để trống tên khách hàng");
            tilTenKhachHang.setErrorEnabled(true);
            check = -1;
        } else {
            tilTenKhachHang.setErrorEnabled(false);
        }

        if (tilSoDienThoai.getEditText().getText().toString().isEmpty()) {
            tilSoDienThoai.setError("Không được để trống số điện thoại");
            tilSoDienThoai.setErrorEnabled(true);
            check = -1;
        } else {
            tilSoDienThoai.setErrorEnabled(false);
        }
        return check;
    }
}