package com.example.mypets;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mypets.Adapter.PetAdapter;
import com.example.mypets.Adapter.ThanhToanAdapter;
import com.example.mypets.ModelClass.HoaDonClass;
import com.example.mypets.ModelClass.HoaDonCtClass;
import com.example.mypets.ModelClass.PetClass;
import com.example.mypets.Sql.HoaDonCtSql;
import com.example.mypets.Sql.HoaDonSql;
import com.example.mypets.Sql.KhachHangSql;
import com.example.mypets.Sql.PetSql;
import com.example.mypets.Sql.Sqlite;

import java.text.ParseException;
import java.util.ArrayList;

public class ThemHoaDonChiTietAct extends AppCompatActivity {
    RecyclerView rvThanhToan;
    TextView tvTongTien;
    Sqlite sqlite;
    String mahd = "";
    ArrayList<HoaDonCtClass> arrHdct = new ArrayList<>();
    ArrayList<String> arrMaPetSelected = new ArrayList<>();
    int checkThanhToan = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them_hoa_don_chi_tiet);
        rvThanhToan = findViewById(R.id.rvThanhToan);
        tvTongTien = findViewById(R.id.tvTongTien);
        sqlite = new Sqlite(ThemHoaDonChiTietAct.this);
        String mapet = getIntent().getExtras().getString("MAPET");
        mahd = getIntent().getExtras().getString("MAHOADON");
        HoaDonCtClass hdct = new HoaDonCtClass(0,mahd,mapet);
        HoaDonCtSql hoaDonCtSql = new HoaDonCtSql(sqlite);
        PetSql petSql = new PetSql(sqlite);
        hoaDonCtSql.AddHdct(hdct);
        arrHdct.add(hdct);
        try {
            tvTongTien.setText("Tổng tiền :"+petSql.GetPet(mapet).getGiaTien());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        arrMaPetSelected.add(mapet);
        ThanhToanAdapter thanhToanAdapter = new ThanhToanAdapter(arrHdct);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(ThemHoaDonChiTietAct.this);
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        rvThanhToan.setLayoutManager(linearLayoutManager);
        rvThanhToan.setAdapter(thanhToanAdapter);
    }

    public void ThanhToan(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(ThemHoaDonChiTietAct.this);
        builder.setTitle("Xác nhận");
        builder.setMessage("Bạn có muốn mua thêm không??");
        AlertDialog alertDialog = builder.create();
        builder.setNegativeButton("Có", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                alertDialog.dismiss();
            }
        });
        builder.setPositiveButton("Không", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                double tongtien = 0;
                PetSql petSql = new PetSql(sqlite);
                for (int i = 0; i<arrHdct.size();i++){
                    petSql.UpdateTrangThai(arrHdct.get(i).getMapet());
                }
                HoaDonSql hoaDonSql = new HoaDonSql(sqlite);
                HoaDonClass hd = new HoaDonClass();
                String taikhoan = new String();
                try {
                    hd = hoaDonSql.GetHoaDon(mahd);
                    taikhoan = hd.getTentk();
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                for (int i = 0; i<arrHdct.size();i++){
                    PetClass pet = new PetClass();
                    try {
                        pet = petSql.GetPet(arrHdct.get(i).getMapet());
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    tongtien += pet.getGiaTien();
                }
                hoaDonSql.UpdateTongtien(tongtien,hd);
                Intent intent = new Intent(ThemHoaDonChiTietAct.this,HomeAct.class);
                Bundle bundle = new Bundle();
                bundle.putString("TAIKHOAN",taikhoan);
                intent.putExtras(bundle);
                startActivity(intent);
                Toast.makeText(ThemHoaDonChiTietAct.this, "Bạn đã thanh toàn thành công", Toast.LENGTH_SHORT).show();
            }
        });
        builder.create();
        builder.show();
        checkThanhToan = 1;
    }

    public void muaThem(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(ThemHoaDonChiTietAct.this);
        View view1 = LayoutInflater.from(ThemHoaDonChiTietAct.this).inflate(R.layout.rv_hdct_layout,null);
        builder.setView(view1);
        builder.setTitle("Danh sách pet");
        AlertDialog alert = builder.create();
        RecyclerView recyclerView = view1.findViewById(R.id.rvHdctDialog);
        PetSql petSql = new PetSql(sqlite);
        ArrayList<String> arrMaPet = new ArrayList<>();
        ArrayList<String> arrMaPetUnselected = new ArrayList<>();
        int check = 0;
        arrMaPet = petSql.GetAllMaPet();
        for (int i = 0; i < arrMaPet.size(); i++) {
            for (int j = 0; j < arrMaPetSelected.size(); j++) {
                if (arrMaPet.get(i).equalsIgnoreCase(arrMaPetSelected.get(j))) {
                    check++;
                }
            }
            if (check == 0) {
                arrMaPetUnselected.add(arrMaPet.get(i));
            }
            check = 0;
        }
        ArrayList<PetClass> arrPetUnselected = new ArrayList<>();
        ArrayList<PetClass> arrThemPet = new ArrayList<>();
        for (int i = 0; i<arrMaPetUnselected.size();i++){
            PetClass pet = new PetClass();
            try {
                pet = petSql.GetPet(arrMaPetUnselected.get(i));
            } catch (ParseException e) {
                e.printStackTrace();
            }
                arrPetUnselected.add(pet);
        }
        for (int i = 0; i<arrPetUnselected.size();i++){
            if(arrPetUnselected.get(i).getTrangThai().equalsIgnoreCase("Chưa bán")){
                arrThemPet.add(arrPetUnselected.get(i));
            }
        }

        if(arrThemPet.size()>0){
            PetAdapter petAdapter = new PetAdapter(arrThemPet,"thanhtoan");
            recyclerView.setAdapter(petAdapter);
            LinearLayoutManager linearLayoutManager =new LinearLayoutManager(ThemHoaDonChiTietAct.this);
            linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
            recyclerView.setLayoutManager(linearLayoutManager);
            petAdapter.ItemClickListener(new ItemClickListener() {
                @Override
                public void OnClick(Context context, int position) {
                    double tongtien = 0;
                    arrMaPetSelected.add(arrThemPet.get(position).getMaPet());
                    HoaDonCtClass hdct = new HoaDonCtClass(0,mahd,arrThemPet.get(position).getMaPet());
                    HoaDonCtSql hoaDonCtSql = new HoaDonCtSql(sqlite);
                    hoaDonCtSql.AddHdct(hdct);
                    arrHdct.add(hdct);
                    for (int i = 0; i<arrHdct.size();i++){
                        try {
                            PetClass pet = petSql.GetPet(arrHdct.get(i).getMapet());
                            tongtien += pet.getGiaTien();
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                    }
                    tvTongTien.setText("Tổng tiền: "+ tongtien);
                    ThanhToanAdapter thanhToanAdapter = new ThanhToanAdapter(arrHdct);
                    rvThanhToan.setAdapter(thanhToanAdapter);
                    LinearLayoutManager linearLayoutManager1=new LinearLayoutManager(ThemHoaDonChiTietAct.this);
                    linearLayoutManager1.setOrientation(RecyclerView.VERTICAL);
                    rvThanhToan.setLayoutManager(linearLayoutManager1);
                    alert.dismiss();
                }

                @Override
                public void OnLongClick(Context context, int position) {

                }
            });
            alert.show();
        } else {
            Toast.makeText(this, "Kho không còn hàng", Toast.LENGTH_SHORT).show();
        }
    }

    public void Huy(View view) {
        if (checkThanhToan == 0) {
            HoaDonCtSql hoaDonCtSql = new HoaDonCtSql(sqlite);
            ArrayList<HoaDonCtClass> arrHdct = hoaDonCtSql.GetAllHdctTheoHd(mahd);
            for (int i = 0; i < arrHdct.size(); i++) {
                hoaDonCtSql.DeleteHdct(Integer.toString(arrHdct.get(i).getMahdct()));
            }
            HoaDonSql hoaDonSql = new HoaDonSql(sqlite);
            HoaDonClass hoaDon = new HoaDonClass();
            String taikhoan = "";
            try {
                hoaDon = hoaDonSql.GetHoaDon(mahd);
                taikhoan = hoaDon.getTentk();
            } catch (ParseException e) {
                e.printStackTrace();
            }
            hoaDonSql.DeleteHd(mahd);
            KhachHangSql khachHangSql = new KhachHangSql(sqlite);
            khachHangSql.XoaKhachHang(hoaDon.getMakh());
            Intent intent = new Intent(ThemHoaDonChiTietAct.this, HomeAct.class);
            Bundle bundle = new Bundle();
            bundle.putString("TAIKHOAN", taikhoan);
            intent.putExtras(bundle);
            startActivity(intent);
        } else {
            HoaDonSql hoaDonSql = new HoaDonSql(sqlite);
            HoaDonClass hoaDon = new HoaDonClass();
            String taikhoan = "";
            try {
                hoaDon = hoaDonSql.GetHoaDon(mahd);
                taikhoan = hoaDon.getTentk();
            } catch (ParseException e) {
                e.printStackTrace();
            }
            Intent intent = new Intent(ThemHoaDonChiTietAct.this, HomeAct.class);
            Bundle bundle = new Bundle();
            bundle.putString("TAIKHOAN", taikhoan);
            intent.putExtras(bundle);
            startActivity(intent);
        }
    }
}