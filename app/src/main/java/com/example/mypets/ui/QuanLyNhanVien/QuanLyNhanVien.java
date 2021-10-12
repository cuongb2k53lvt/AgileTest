package com.example.mypets.ui.QuanLyNhanVien;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.mypets.Adapter.NhanVienAdapter;
import com.example.mypets.ItemClickListener;
import com.example.mypets.ModelClass.NhanVienClass;
import com.example.mypets.R;
import com.example.mypets.Sql.NhanVienSql;
import com.example.mypets.Sql.Sqlite;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link QuanLyNhanVien#newInstance} factory method to
 * create an instance of this fragment.
 */
public class QuanLyNhanVien extends Fragment {
    FloatingActionButton fabAddNv;
    Sqlite sqlite;
    ArrayList<NhanVienClass> arrNv;
    RecyclerView rvNv;

    public QuanLyNhanVien() {
        // Required empty public constructor
    }

    public static QuanLyNhanVien newInstance(String param1, String param2) {
        QuanLyNhanVien fragment = new QuanLyNhanVien();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_quan_ly_nv,container,false);
        fabAddNv = view.findViewById(R.id.fabAddNv);
        rvNv = view.findViewById(R.id.rvNv);
        sqlite = new Sqlite(container.getContext());
        NhanVienSql nhanVienSql = new NhanVienSql(sqlite);
        arrNv = nhanVienSql.GetAllNhanVien();
        NhanVienAdapter nhanVienAdapter = new NhanVienAdapter(arrNv);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(container.getContext());
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        rvNv.setLayoutManager(linearLayoutManager);
        rvNv.setAdapter(nhanVienAdapter);
        nhanVienAdapter.ItemClickListener(new ItemClickListener() {
            @Override
            public void OnClick(Context context, int position) {
                AlertDialog.Builder builder = new AlertDialog.Builder(container.getContext());
                builder.setTitle("Đổi trạng thái");
                builder.setPositiveButton("Đổi", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if(arrNv.get(position).getTrangthai().equals("active")){
                            nhanVienSql.TrangThaiInactive(arrNv.get(position));
                            Toast.makeText(context, "Ok1", Toast.LENGTH_SHORT).show();
                        }
                        if(arrNv.get(position).getTrangthai().equals("inactive")){
                            nhanVienSql.TrangThaiActive(arrNv.get(position));
                            Toast.makeText(context, "Ok2", Toast.LENGTH_SHORT).show();
                        }
                        arrNv.removeAll(arrNv);
                        arrNv.addAll(nhanVienSql.GetAllNhanVien());
                        nhanVienAdapter.notifyDataSetChanged();
                    }
                });
                builder.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                AlertDialog alert = builder.create();
                alert.show();
            }

            @Override
            public void OnLongClick(Context context, int position) {

            }
        });
        fabAddNv.setOnClickListener(new View.OnClickListener() {
            private NhanVienSql nhanVienSql = new NhanVienSql(sqlite);
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(container.getContext());
                builder.setTitle("Thêm nhân viên");
                View view = LayoutInflater.from(container.getContext()).inflate(R.layout.add_nhanvien_dialog,null);
                builder.setView(view);
                TextInputEditText tieTenTk = view.findViewById(R.id.tieTaiKhoan);
                TextInputEditText tieHoTen = view.findViewById(R.id.tieHoTen);
                TextInputEditText tieMk = view.findViewById(R.id.tieMatKhau);
                TextInputEditText tieSdt = view.findViewById(R.id.tieSdt);
                TextInputEditText tieDiaChi = view.findViewById(R.id.tieDiaChi);
                Button btnAddnv = view.findViewById(R.id.btnAddnv);
                btnAddnv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int checkTk = 0;
                        arrNv = nhanVienSql.GetAllNhanVien();
                        for (int i = 0; i<arrNv.size(); i++){
                            if(tieTenTk.getText().toString().equals(arrNv.get(i).getTentk())){
                                checkTk=1;
                            }
                        }
                        if(checkTk == 1){
                            Toast.makeText(container.getContext(), "Tài khoản đã tồn tại", Toast.LENGTH_SHORT).show();
                        } else {
                            String Tk = tieTenTk.getText().toString();
                            String hoTen = tieHoTen.getText().toString();
                            String Mk = tieMk.getText().toString();
                            String sdt = tieSdt.getText().toString();
                            String diachi = tieDiaChi.getText().toString();
                            NhanVienClass nhanVienClass = new NhanVienClass(Tk,hoTen,Mk,sdt,diachi,"");
                            nhanVienSql.AddNhanVien(nhanVienClass);
                            Toast.makeText(container.getContext(), "Thành công", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });
        // Inflate the layout for this fragment
        return view;
    }
}