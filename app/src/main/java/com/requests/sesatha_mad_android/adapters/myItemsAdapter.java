package com.requests.sesatha_mad_android.adapters;

import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.requests.sesatha_mad_android.Item;
import com.requests.sesatha_mad_android.R;

public class myItemsAdapter extends FirebaseRecyclerAdapter<Item, myItemsAdapter.itemsViewHolder> {
    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public myItemsAdapter(@NonNull FirebaseRecyclerOptions<Item> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myItemsAdapter.itemsViewHolder holder, int position, @NonNull Item model) {
        //Log.d("MyItems",model.getTitle());
        holder.title.setText(model.getTitle());
        holder.price.setText("Rs "+String.format("%.2f",model.getPrice()));
        if(model.isApproved()){
            holder.status.setText("Accepted");
            holder.status.setTextColor(Color.GREEN);
        }else{
            holder.status.setText("Pending");
            holder.status.setTextColor(Color.RED);
        }
        Glide.with(holder.itemView.getContext())
                .load(model.getImUrl()) // image url
                //.placeholder(R.drawable.placeholder) // any placeholder to load at start
                //.error(R.drawable.imagenotfound)  // any image in case of error
                .override(100, 97) // resizing
                .centerCrop()
                .into(holder.image);  // imageview object
    }

    @NonNull
    @Override
    public myItemsAdapter.itemsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_my_item,parent,false);
        return new myItemsAdapter.itemsViewHolder(view);
    }

    class itemsViewHolder extends RecyclerView.ViewHolder{
        TextView title,price,status;
        ImageView image;
        public itemsViewHolder(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.item_titleTextView);
            price = itemView.findViewById(R.id.item_priceTextView);
            image = itemView.findViewById(R.id.itemImageView);
            status = itemView.findViewById(R.id.item_statusTextView);
        }
    }
}
