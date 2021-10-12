package com.example.mypets.ui.BanHang;

import android.os.Bundle;

import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.mypets.Adapter.BanHangAdapter;
import com.example.mypets.ModelClass.PetClass;
import com.example.mypets.R;
import com.example.mypets.Sql.PetSql;
import com.example.mypets.Sql.Sqlite;

import java.text.ParseException;
import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BanHang#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BanHang extends Fragment {
    SearchView searchView;
    Spinner spnLoai;
    RecyclerView rvBanHang;
    Sqlite sqlite;
    ArrayList<String> loai = new ArrayList<>();
    ArrayList<PetClass> arrPet = new ArrayList<>();
    public BanHang() {
        // Required empty public constructor
    }

    public static BanHang newInstance(String param1, String param2) {
        BanHang fragment = new BanHang();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ban_hang,container,false);
        searchView = view.findViewById(R.id.svBanHang);
        spnLoai = view.findViewById(R.id.spnBanHang);
        rvBanHang = view.findViewById(R.id.rvBanHang);
        sqlite = new Sqlite(getActivity());
        PetSql petSql = new PetSql(sqlite);
        try {
            arrPet = petSql.GetPetChuaBan();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String taikhoan = getActivity().getIntent().getExtras().getString("TAIKHOAN");
        try {
            arrPet = petSql.GetPetChuaBan();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        loai.add("Chó");
        loai.add("Mèo");
        ArrayAdapter arrayAdapter = new ArrayAdapter(getContext(), android.R.layout.simple_spinner_dropdown_item,loai);
        spnLoai.setAdapter(arrayAdapter);
        BanHangAdapter banHangAdapter = new BanHangAdapter(arrPet,taikhoan);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        rvBanHang.setAdapter(banHangAdapter);
        rvBanHang.setLayoutManager(linearLayoutManager);
        spnLoai.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(loai.get(position).equalsIgnoreCase("Chó")){
                    ArrayList<PetClass> arrCho = new ArrayList<>();
                    ArrayList<PetClass> arrPetChuaBan = new ArrayList<>();
                    try {
                        arrPetChuaBan = petSql.GetPetChuaBan();
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    for (int i=0; i <arrPetChuaBan.size();i++){
                        if(arrPetChuaBan.get(i).getTrangThai().equalsIgnoreCase("Chưa bán")&&arrPetChuaBan.get(i).getTenLoai().equalsIgnoreCase("Chó")){
                            arrCho.add(arrPetChuaBan.get(i));
                        }
                    }
                    arrPet.removeAll(arrPet);
                    arrPet.addAll(arrCho);
                    banHangAdapter.notifyDataSetChanged();
                }
                if(loai.get(position).equalsIgnoreCase("Mèo")){
                    ArrayList<PetClass> arrMeo = new ArrayList<>();
                    ArrayList<PetClass> arrPetChuaBan = new ArrayList<>();
                    try {
                        arrPetChuaBan = petSql.GetPetChuaBan();
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    for (int i=0; i <arrPetChuaBan.size();i++){
                        if(arrPetChuaBan.get(i).getTrangThai().equalsIgnoreCase("Chưa bán")&&arrPetChuaBan.get(i).getTenLoai().equalsIgnoreCase("Mèo")){
                            arrMeo.add(arrPetChuaBan.get(i));
                        }
                    }
                    arrPet.removeAll(arrPet);
                    arrPet.addAll(arrMeo);
                    banHangAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        // Inflate the layout for this fragment
        return view;
    }
}