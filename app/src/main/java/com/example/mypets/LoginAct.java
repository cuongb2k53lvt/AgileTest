package com.example.mypets;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mypets.ModelClass.NhanVienClass;
import com.example.mypets.Sql.NhanVienSql;
import com.example.mypets.Sql.Sqlite;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;

public class LoginAct extends AppCompatActivity {
    TextView tvLogintitle;
    TextInputLayout inputLayoutTk, inputLayoutMk;
    TextInputEditText inputEdtTaikhoan, inputEdtMatkhau;
    CheckBox cb;
    Button btnLogin;
    Sqlite sqlite;
    ArrayList<NhanVienClass> arrNv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);
        tvLogintitle = findViewById(R.id.tvTitleLoginAct);
        inputEdtTaikhoan = findViewById(R.id.inputEdtTaiKhoan);
        inputEdtMatkhau = findViewById(R.id.inputEdtMatKhau);
        inputLayoutTk = findViewById(R.id.inputLayoutTk);
        inputLayoutMk = findViewById(R.id.inputLayoutMk);
        cb = findViewById(R.id.cbLogin);
        btnLogin = findViewById(R.id.btnLogin);
        sqlite = new Sqlite(LoginAct.this);
        Typeface myTypeface = Typeface.createFromAsset(getAssets(),"Pacifico.ttf");
        tvLogintitle.setTypeface(myTypeface);
        inputEdtTaikhoan.setTypeface(myTypeface);
        inputEdtMatkhau.setTypeface(myTypeface);
        inputLayoutTk.setTypeface(myTypeface);
        inputLayoutMk.setTypeface(myTypeface);
        cb.setTypeface(myTypeface);
        btnLogin.setTypeface(myTypeface);
        NhanVienSql nhanVienSql = new NhanVienSql(sqlite);
        arrNv = nhanVienSql.GetAllNhanVien();
        //add admin
        int k = 0;
        for (int i=0;i<arrNv.size(); i++){
            if(arrNv.get(i).getTentk().equalsIgnoreCase("admin")){
                k = 1;
            }
        }
        if(k==0){
            nhanVienSql.AddNhanVien(new NhanVienClass("admin","admin","admin","","",""));
        }
        //add admin
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int checkLogin = 0;
                arrNv = nhanVienSql.GetAllNhanVien();
                for (int i = 0;i<arrNv.size();i++){
                    if(inputEdtTaikhoan.getText().toString().equals(arrNv.get(i).getTentk())&&inputEdtMatkhau.getText().toString().equals(arrNv.get(i).getMk())&&arrNv.get(i).getTrangthai().equals("active")){
                        checkLogin = 1;
                    }
                }
                if (checkLogin==1){
                    Intent intent= new Intent(LoginAct.this, HomeAct.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("TAIKHOAN",inputEdtTaikhoan.getText().toString());
                    intent.putExtras(bundle);
                    startActivity(intent);
                } else {
                    Toast.makeText(LoginAct.this, "Sai tài khoản hoặc mật khẩu", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void Login(View view) {
    }
}