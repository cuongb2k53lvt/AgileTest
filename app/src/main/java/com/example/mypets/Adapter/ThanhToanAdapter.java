package com.example.mypets.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mypets.ItemClickListener;
import com.example.mypets.ModelClass.HoaDonCtClass;
import com.example.mypets.ModelClass.PetClass;
import com.example.mypets.R;
import com.example.mypets.Sql.PetSql;
import com.example.mypets.Sql.Sqlite;

import java.text.ParseException;
import java.util.ArrayList;

public class ThanhToanAdapter extends RecyclerView.Adapter<ThanhToanAdapter.ThanhToanHolder> {
    ArrayList<HoaDonCtClass> arrHdct;
    Sqlite sqlite;
    public ThanhToanAdapter(ArrayList<HoaDonCtClass> arrHdct){
        this.arrHdct = arrHdct;
    }
    @NonNull
    @Override
    public ThanhToanHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.rv_pet_layout,null);
        return new ThanhToanHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ThanhToanHolder holder, int position) {
        PetSql petSql = new PetSql(sqlite);
        PetClass pet = new PetClass();
        try {
            pet = petSql.GetPet(arrHdct.get(position).getMapet());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        if (pet == null) {
            return;
        } else {
            byte[] anh = pet.getAnh();
            Bitmap bitmap = BitmapFactory.decodeByteArray(anh, 0, anh.length);
            holder.imgPet.setImageBitmap(bitmap);

            holder.tvMaPet.setText("Mã: " + pet.getMaPet());
            holder.tvTenPet.setText("Giống: " + pet.getTenPet());
            holder.tvGioiTinh.setText("Giới tính: " + pet.getGioiTinh());
            holder.tvTuoi.setText("Tuổi: "+pet.getTuoi());
        }
    }

    @Override
    public int getItemCount() {
        return arrHdct.size();
    }

    public class ThanhToanHolder extends RecyclerView.ViewHolder {
        ImageView imgPet;
        TextView tvMaPet, tvTenPet, tvGioiTinh, tvTuoi, tvTrangthai;
        View view;
        public ThanhToanHolder(@NonNull View itemView) {
            super(itemView);
            this.view = itemView;
            sqlite = new Sqlite(itemView.getContext());
            imgPet = itemView.findViewById(R.id.imgPet);
            tvMaPet = itemView.findViewById(R.id.tvMaPet);
            tvTenPet = itemView.findViewById(R.id.tvTenPet);
            tvGioiTinh = itemView.findViewById(R.id.tvGioiTinhPet);
            tvTuoi = itemView.findViewById(R.id.tvTuoi);
        }
    }
}
