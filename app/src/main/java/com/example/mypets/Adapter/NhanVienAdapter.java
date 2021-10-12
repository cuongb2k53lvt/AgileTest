package com.example.mypets.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mypets.ItemClickListener;
import com.example.mypets.ModelClass.NhanVienClass;
import com.example.mypets.R;

import java.util.ArrayList;

public class NhanVienAdapter extends RecyclerView.Adapter<NhanVienAdapter.NhanVienViewHolder> {
    ArrayList<NhanVienClass> arrNv;
    ItemClickListener itemClickListener;
    public NhanVienAdapter(ArrayList<NhanVienClass> arrNv){
        this.arrNv = arrNv;
    }
    public void ItemClickListener(ItemClickListener itemClickListener){
        this.itemClickListener = itemClickListener;
    }
    @NonNull
    @Override
    public NhanVienViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.rv_nv_layout,null);
        return new NhanVienViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NhanVienViewHolder holder, int position) {
        if(arrNv.get(position).getTrangthai().equals("active")){
            holder.tvTt.setTextColor(holder.view.getResources().getColor(R.color.teal_700));
        }
        if(arrNv.get(position).getTrangthai().equals("inactive")){
            holder.tvTt.setTextColor(holder.view.getResources().getColor(R.color.red));
        }
        holder.tvTaiKhoan.setText("Tên tài khoản: "+arrNv.get(position).getTentk());
        holder.tvSdt.setText("Sdt: "+arrNv.get(position).getSdt());
        holder.tvTen.setText("Tên nhân viên: "+arrNv.get(position).getTennv());
        holder.tvTt.setText("Trạng thái: "+arrNv.get(position).getTrangthai());
        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemClickListener.OnClick(holder.view.getContext(),position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrNv.size();
    }

    public class NhanVienViewHolder extends RecyclerView.ViewHolder {
        TextView tvTaiKhoan, tvTen, tvSdt, tvTt;
        View view;
        public NhanVienViewHolder(@NonNull View itemView) {
            super(itemView);
            this.view = itemView;
            tvTaiKhoan = itemView.findViewById(R.id.tvTenTaiKhoan);
            tvTen = itemView.findViewById(R.id.tvTenNhanVien);
            tvSdt = itemView.findViewById(R.id.tvSoDienThoai);
            tvTt = itemView.findViewById(R.id.tvTrangThai);
        }
    }
}
