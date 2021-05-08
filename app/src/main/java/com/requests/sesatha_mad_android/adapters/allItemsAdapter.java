package com.requests.sesatha_mad_android.adapters;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.requests.sesatha_mad_android.R;
import com.requests.sesatha_mad_android.interfaces.ItemClickListener;
import com.requests.sesatha_mad_android.models.Item;

public class allItemsAdapter extends FirebaseRecyclerAdapter<Item, allItemsAdapter.itemsViewHolder> {
    private ItemClickListener listener;

    public allItemsAdapter(@NonNull FirebaseRecyclerOptions<Item> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull allItemsAdapter.itemsViewHolder holder, int position, @NonNull Item model) {

        holder.title.setText(model.getTitle());
        holder.price.setText("Rs "+String.format("%.2f",model.getPrice()));

        Glide.with(holder.itemView.getContext())
                .load(model.getImUrl()) // image url
                .placeholder(R.drawable.image_default) // any placeholder to load at start
                .error(R.drawable.image_broken)  // any image in case of error
                .override(100, 97) // resizing
                .transform(new CenterCrop(),new RoundedCorners(9))
                //.centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.image);  // imageview object

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClick(model,position);
            }
        });
    }

    @NonNull
    @Override
    public allItemsAdapter.itemsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_all_items,parent,false);
        return new allItemsAdapter.itemsViewHolder(view);
    }

    class itemsViewHolder extends RecyclerView.ViewHolder{
        TextView title,price;
        ImageView image;

        public itemsViewHolder(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.item_titleTextView);
            price = itemView.findViewById(R.id.item_priceTextView);
            image = itemView.findViewById(R.id.itemImageView);
            //status = itemView.findViewById(R.id.item_statusTextView);
        }

    }

    //use this function to set the click event method in an activity
    public void setItemClickListener(ItemClickListener listener){
        this.listener = listener;
    }
}
