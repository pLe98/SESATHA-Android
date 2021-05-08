package com.requests.sesatha_mad_android;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    Context context;
    ArrayList <orderDetails> arrayList;

    public MyAdapter(Context context, ArrayList<orderDetails> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.activity_demo,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        orderDetails orderDetails = arrayList.get(position);
        holder.productname.setText(orderDetails.getProductName());
        holder.quantitiy.setText(orderDetails.getProductName());
        holder.total.setText(orderDetails.getAmount());

    }

    @Override
    public int getItemCount() {

        return arrayList.size();
    }

    public static class MyViewHolder extends  RecyclerView.ViewHolder {

        TextView productname, quantitiy , total;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            productname =itemView.findViewById(R.id.product);
            quantitiy= itemView.findViewById(R.id.quantity);
            total =itemView.findViewById(R.id.totPrice);
        }
    }
}
