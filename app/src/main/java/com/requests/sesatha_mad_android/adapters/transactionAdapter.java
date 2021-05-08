package com.requests.sesatha_mad_android.adapters;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.requests.sesatha_mad_android.R;
import com.requests.sesatha_mad_android.models.Transaction;

public class transactionAdapter extends FirebaseRecyclerAdapter<Transaction, transactionAdapter.transactionViewHolder> {
    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public transactionAdapter(@NonNull FirebaseRecyclerOptions<Transaction> options) {
        super(options);

    }


    @Override
    protected void onBindViewHolder(@NonNull transactionViewHolder holder, int position, @NonNull Transaction model) {
        Log.d("Trans",model.getOrderDate());
        holder.transID.setText(model.getTransactionID());
        holder.amount.setText(String.valueOf(model.getAmount()));
        holder.transDate.setText(model.getOrderDate());
    }

    @NonNull
    @Override
    public transactionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.transaction_cards,parent,false);
        return new transactionAdapter.transactionViewHolder(view);
    }

    public class transactionViewHolder extends RecyclerView.ViewHolder {
        TextView transID, amount, transDate;
        public transactionViewHolder(@NonNull View itemView) {
            super(itemView);
            transDate = itemView.findViewById(R.id.trans_date);
            transID = itemView.findViewById(R.id.trans_transid);
            amount = itemView.findViewById(R.id.trans_amount);
        }
    }
}
