package com.requests.sesatha_mad_android.adapters;

import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.requests.sesatha_mad_android.models.Item;

public class cartItemAdapter extends FirebaseRecyclerAdapter<Item, myItemsAdapter.itemsViewHolder> {
    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public cartItemAdapter(@NonNull FirebaseRecyclerOptions<Item> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myItemsAdapter.itemsViewHolder holder, int position, @NonNull Item model) {



    }

    @NonNull
    @Override
    public myItemsAdapter.itemsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }
}
