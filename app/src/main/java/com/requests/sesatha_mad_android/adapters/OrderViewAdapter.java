package com.requests.sesatha_mad_android.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.requests.sesatha_mad_android.R;
import com.requests.sesatha_mad_android.models.OrderDetails;
import java.util.ArrayList;
public class OrderViewAdapter extends RecyclerView.Adapter<OrderViewAdapter.MyViewHolder> {


    Context context;
    ArrayList <OrderDetails> arrayList;

    public OrderViewAdapter(Context context, ArrayList<OrderDetails> arrayList ) {
        this.context = context;
        this.arrayList = arrayList;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.activity_my_orders,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position)   {
        OrderDetails orderDetails = arrayList.get(position);  // create object  from OrderDetails model
        holder.productname.setText(orderDetails.getTitle()); // pass values from model class
        holder.quantitiy.setText(String.valueOf(orderDetails.getNoOfItems()));  // pass values from model class
        holder.total.setText(String.valueOf(orderDetails.getNetAmount()));  // pass values from model class

    }

    @Override
    public int getItemCount() {

        return arrayList.size(); //get Array size
    }

    public class MyViewHolder extends  RecyclerView.ViewHolder  {

        TextView productname, quantitiy , total;    // crate object from TextView class
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            productname =itemView.findViewById(R.id.product); // assigning values with xml variables
            quantitiy= itemView.findViewById(R.id.quantity1); // assigning values with xml variables
            total =itemView.findViewById(R.id.price); // assigning values with xml variables

        }

    }
}



