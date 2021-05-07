package com.requests.sesatha_mad_android.interfaces;

import android.view.View;

import com.requests.sesatha_mad_android.models.Item;

public interface ItemClickListener {
    //needs to implement onClick event
    //void onClick(View view, int position, boolean isLongClick);
    void onItemClick(Item model, int position);

}
