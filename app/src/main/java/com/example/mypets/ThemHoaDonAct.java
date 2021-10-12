package com.example.mypets;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.mypets.ModelClass.HoaDonClass;
import com.example.mypets.Sql.HoaDonSql;
import com.example.mypets.Sql.KhachHangSql;
import com.example.mypets.Sql.Sqlite;
import com.google.android.material.textfield.TextInputLayout;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class ThemHoaDonAct extends AppCompatActivity {
    TextInputLayout tilTenThuNgan, tilMaKh, tilMaHd;
    EditText edtNgayMua;
    ImageView imgThemNgayMua;
    Sqlite sqlite;
    String taikhoan, mapet, makh;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them_hoa_don);
        tilTenThuNgan = findViewById(R.id.tilTenThuNgan);
        tilMaKh = findViewById(R.id.tilMaKhachHangHD);
        tilMaHd = findViewById(R.id.tilMaHoaDon);
        edtNgayMua = findViewById(R.id.edtNgayMua);
        imgThemNgayMua = findViewById(R.id.imgThemNgayMua);
        sqlite = new Sqlite(ThemHoaDonAct.this);
        taikhoan = getIntent().getExtras().getString("TAIKHOAN");
        mapet = getIntent().getExtras().getString("MAPET");
        makh = getIntent().getExtras().getString("MAKH");
        tilTenThuNgan.getEditText().setText(taikhoan);
        tilMaKh.getEditText().setText(makh);
        imgThemNgayMua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                int day = calendar.get(Calendar.DAY_OF_MONTH);
                int month = calendar.get(Calendar.MONTH);
                int year = calendar.get(Calendar.YEAR);
                DatePickerDialog datePickerDialog = new DatePickerDialog(ThemHoaDonAct.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd");
                        Calendar calendar1 = new GregorianCalendar(year, month, dayOfMonth);
                        edtNgayMua.setText(simpleDateFormat.format(calendar1.getTime()));
                    }
                }, year, month, day);
                datePickerDialog.show();
            }
        });
    }

    public void huyHoaDon(View view) {
        KhachHangSql khachHangSql = new KhachHangSql(sqlite);
        khachHangSql.XoaKhachHang(makh);
        finish();
    }

    public void ThemHoaDonChiTiet(View view) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd");
        HoaDonSql hoaDonSql = new HoaDonSql(sqlite);
        HoaDonClass hoadon = new HoaDonClass();
        hoadon.setMahoadon(tilMaHd.getEditText().getText().toString());
        hoadon.setTentk(tilTenThuNgan.getEditText().getText().toString());
        hoadon.setMakh(tilMaKh.getEditText().getText().toString());
        hoadon.setNgaymua(simpleDateFormat.parse(edtNgayMua.getText().toString()));
        if (hoaDonSql.AddHoaDon(hoadon) > 0) {
            Intent intent = new Intent(ThemHoaDonAct.this, ThemHoaDonChiTietAct.class);
            Bundle bundle = new Bundle();
            bundle.putString("MAPET", mapet);
            bundle.putString("MAHOADON", hoadon.getMahoadon());
            intent.putExtras(bundle);
            startActivity(intent);
        }
    }
}