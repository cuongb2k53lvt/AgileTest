package com.example.mypets;

import android.content.Context;

public interface ItemClickListener {
    void OnClick(Context context,int position);
    void OnLongClick(Context context, int position);
}
