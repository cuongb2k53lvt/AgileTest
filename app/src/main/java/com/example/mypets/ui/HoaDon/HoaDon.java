package com.example.mypets.ui.HoaDon;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mypets.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HoaDon#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HoaDon extends Fragment {

    public HoaDon() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static HoaDon newInstance(String param1, String param2) {
        HoaDon fragment = new HoaDon();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_hoa_don, container, false);
    }
}