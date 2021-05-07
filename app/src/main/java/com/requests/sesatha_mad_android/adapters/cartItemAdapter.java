package com.requests.sesatha_mad_android.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.requests.sesatha_mad_android.R;
import com.requests.sesatha_mad_android.models.Cart;


public class cartItemAdapter extends FirebaseRecyclerAdapter<Cart, cartItemAdapter.cartItemsViewHolder> {
    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public cartItemAdapter(@NonNull FirebaseRecyclerOptions<Cart> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull cartItemAdapter.cartItemsViewHolder holder, int position, @NonNull Cart model) {
        holder.title.setText(model.getTitle());
        holder.unitPrice.setText(String.valueOf(model.getUnitPrice()));
        holder.shipping.setText(String.valueOf(model.getShipping()));
        holder.qty.setText(String.valueOf(model.getQty()));
        holder.itemNo.setText(model.getItemNo());
    }

    @NonNull
    @Override
    public cartItemAdapter.cartItemsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_item_card,parent,false);
        return new cartItemAdapter.cartItemsViewHolder(view);
    }

    public class cartItemsViewHolder extends RecyclerView.ViewHolder {
        //Float unitPrice;
        TextView itemNo, title,unitPrice, qty, shipping;
        public cartItemsViewHolder(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.citem_title);
            itemNo = itemView.findViewById(R.id.citem_itemno);
            unitPrice = itemView.findViewById(R.id.citem_unitprice);
            qty = itemView.findViewById(R.id.citem_qty);
            shipping = itemView.findViewById(R.id.citem_shipping);

        }
    }
}
