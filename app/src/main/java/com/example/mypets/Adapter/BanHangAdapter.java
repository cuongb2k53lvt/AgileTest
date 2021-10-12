package com.example.mypets.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mypets.ItemClickListener;
import com.example.mypets.ModelClass.PetClass;
import com.example.mypets.R;
import com.example.mypets.ThemKhachHangAct;

import java.util.ArrayList;

public class BanHangAdapter extends RecyclerView.Adapter<BanHangAdapter.BanHangHolder> {
    ArrayList<PetClass> arrPet;
    String taikhoan;
    ItemClickListener itemClickListener;
    public BanHangAdapter(ArrayList<PetClass> arrPet, String taikhoan){
        this.arrPet = arrPet;
        this.taikhoan = taikhoan;
    }
    public void SetItemClickListener(ItemClickListener itemClickListener){
        this.itemClickListener = itemClickListener;
    }
    @NonNull
    @Override
    public BanHangHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.rv_banhang_layout,null);
        return new  BanHangHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BanHangHolder holder, int position) {
        PetClass pet = arrPet.get(position);
        byte[] anh = pet.getAnh();
        Bitmap bitmap = BitmapFactory.decodeByteArray(anh,0,anh.length);
        holder.tvMaPet.setText(pet.getMaPet());
        holder.tvTenPet.setText(pet.getTenPet());
        holder.tvGioiTinh.setText(pet.getGioiTinh());
        holder.tvTuoi.setText(pet.getTuoi());
        holder.imgPet.setImageBitmap(bitmap);
        holder.imgCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(holder.view.getContext(), ThemKhachHangAct.class);
                Bundle bundle = new Bundle();
                bundle.putString("TAIKHOAN",taikhoan);
                bundle.putString("MAPET",pet.getMaPet());
                intent.putExtras(bundle);
                holder.view.getContext().startActivity(intent);
            }
        });
        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemClickListener.OnClick(holder.view.getContext(),position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrPet.size();
    }

    public class BanHangHolder extends RecyclerView.ViewHolder {
        ImageView imgPet, imgCart;
        TextView tvMaPet, tvTenPet, tvGioiTinh, tvTuoi;
        View view;
        public BanHangHolder(@NonNull View itemView) {
            super(itemView);
            this.view = itemView;
            imgPet = itemView.findViewById(R.id.imgPetBanHang);
            imgCart = itemView.findViewById(R.id.imgBanHang);
            tvMaPet = itemView.findViewById(R.id.tvMaPetBanHang);
            tvTenPet = itemView.findViewById(R.id.tvTenPetBanHang);
            tvGioiTinh = itemView.findViewById(R.id.tvGioiTinhPetBanHang);
            tvTuoi = itemView.findViewById(R.id.tvTuoiPetBanHang);
        }
    }
}
