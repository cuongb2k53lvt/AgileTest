package com.example.mypets.Adapter;

import android.content.Context;
import android.content.Intent;
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
import com.example.mypets.ModelClass.PetClass;
import com.example.mypets.R;

import java.util.ArrayList;

public class PetAdapter extends RecyclerView.Adapter<PetAdapter.PetViewHolder> {
    ArrayList<PetClass> arrPet;
    ItemClickListener itemClickListener;
    String taikhoan;
    public PetAdapter (ArrayList<PetClass> arrPet, String taikhoan){
        this.arrPet = arrPet;
        this.taikhoan = taikhoan;
    }
    public void ItemClickListener(ItemClickListener itemClickListener){
        this.itemClickListener = itemClickListener;
    }
    @NonNull
    @Override
    public PetViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.rv_pet_layout,null);
        return new PetViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PetViewHolder holder, int position) {
        PetClass pet = arrPet.get(position);
        byte[] anh = pet.getAnh();
        Bitmap bitmap = BitmapFactory.decodeByteArray(anh,0,anh.length);
        holder.imgPet.setImageBitmap(bitmap);
        holder.tvMaPet.setText("Mã pet: "+pet.getMaPet());
        holder.tvTenPet.setText("Tên pet: "+pet.getTenPet());
        holder.tvGioiTinh.setText("Giới tính: "+pet.getGioiTinh());
        holder.tvTuoi.setText("Tuổi: "+pet.getTuoi());
        if(pet.getTrangThai().equals("Chưa bán")){
            holder.tvTrangthai.setText(pet.getTrangThai());
            holder.tvTrangthai.setTextColor(holder.view.getResources().getColor(R.color.teal_700));
        }
        if(pet.getTrangThai().equals("Đã bán")){
            holder.tvTrangthai.setText(pet.getTrangThai());
            holder.tvTrangthai.setTextColor(holder.view.getResources().getColor(R.color.red));
        }
        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(taikhoan.equalsIgnoreCase("thanhtoan") == false){
                    itemClickListener.OnClick(holder.view.getContext(),position);
                }
            }
        });
        if(taikhoan.equalsIgnoreCase("thanhtoan")){
            holder.tvTrangthai.setText("Thêm");
            holder.tvTrangthai.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    itemClickListener.OnClick(holder.view.getContext(),position);
                }
            });
        }
        holder.view.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                itemClickListener.OnLongClick(holder.view.getContext(),position);
                return false;
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrPet.size();
    }

    public class PetViewHolder extends RecyclerView.ViewHolder {
        ImageView imgPet;
        TextView tvMaPet, tvTenPet, tvGioiTinh, tvTuoi, tvTrangthai;
        View view;
        public PetViewHolder(@NonNull View itemView) {
            super(itemView);
            this.view = itemView;
            imgPet = itemView.findViewById(R.id.imgPet);
            tvTrangthai = itemView.findViewById(R.id.tvTrangThaiPet);
            tvMaPet = itemView.findViewById(R.id.tvMaPet);
            tvTenPet = itemView.findViewById(R.id.tvTenPet);
            tvGioiTinh = itemView.findViewById(R.id.tvGioiTinhPet);
            tvTuoi = itemView.findViewById(R.id.tvTuoi);
        }
    }
}
