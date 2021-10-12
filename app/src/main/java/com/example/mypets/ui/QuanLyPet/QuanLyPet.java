package com.example.mypets.ui.QuanLyPet;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mypets.Adapter.PetAdapter;
import com.example.mypets.ItemClickListener;
import com.example.mypets.ModelClass.PetClass;
import com.example.mypets.R;
import com.example.mypets.Sql.PetSql;
import com.example.mypets.Sql.Sqlite;
import com.example.mypets.ThemPetAct;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.ParseException;
import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link QuanLyPet#newInstance} factory method to
 * create an instance of this fragment.
 */
public class QuanLyPet extends Fragment {
    RecyclerView rvPet;
    FloatingActionButton fabAddPet;
    ArrayList<PetClass> arrPet = new ArrayList<>();
    Sqlite sqlite;
    public QuanLyPet() {
        // Required empty public constructor
    }

    public static QuanLyPet newInstance(String param1, String param2) {
        QuanLyPet fragment = new QuanLyPet();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_quan_ly_pet2,container,false);
        rvPet =view.findViewById(R.id.rvPet);
        fabAddPet = view.findViewById(R.id.fabAddPet);
        sqlite = new Sqlite(getContext());
        String taikhoan = getActivity().getIntent().getExtras().getString("TAIKHOAN");
        PetSql petSql = new PetSql(sqlite);
        try {
            arrPet = petSql.GetAllPet();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        PetAdapter petAdapter =new PetAdapter(arrPet,taikhoan);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(container.getContext());
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        rvPet.setLayoutManager(linearLayoutManager);
        rvPet.setAdapter(petAdapter);
        fabAddPet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(container.getContext(), ThemPetAct.class);
                startActivity(intent);
            }
        });
        petAdapter.ItemClickListener(new ItemClickListener() {
            @Override
            public void OnClick(Context context, int position) {

            }

            @Override
            public void OnLongClick(Context context, int position) {
                AlertDialog.Builder builder = new AlertDialog.Builder(container.getContext());
                builder.setTitle("Xóa Pet ?");
                builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        petSql.XoaPet(arrPet.get(position).getMaPet());
                        arrPet.remove(position);
                        petAdapter.notifyDataSetChanged();
                    }
                });
                builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                AlertDialog alert = builder.create();
                alert.show();
            }

        });
        return view;
    }
}