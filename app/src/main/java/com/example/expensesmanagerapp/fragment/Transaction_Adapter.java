package com.example.expensesmanagerapp.fragment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.expensesmanagerapp.R;
import com.example.expensesmanagerapp.Utiles.Helper;
import com.example.expensesmanagerapp.databinding.RowTransactionBinding;

import java.util.ArrayList;

public class Transaction_Adapter extends RecyclerView.Adapter<Transaction_Adapter.TransactionViewHolder> {

    Context context;
    ArrayList<Transaction_Model> arrayList;

    public Transaction_Adapter(Context context, ArrayList<Transaction_Model> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    //onCreateViewHolder inflate the layout with the help of ViewHolder class
    @NonNull
    @Override
    public TransactionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new TransactionViewHolder(LayoutInflater.from(context).inflate(R.layout.row_transaction,parent,false));
    }

    //binding the view with data
    @Override
    public void onBindViewHolder(@NonNull TransactionViewHolder holder, int position) {
        //getting the position from arraylist
        Transaction_Model transactionModel = arrayList.get(position);

        holder.binding.transactionAmount.setText(transactionModel.getAccount());
        holder.binding.accountLabel.setText(transactionModel.getAccount());

       // Helper helper = new Helper(); instead of creating a every time obj of same class, we gonna do that particular method and function as a static and call directly

        holder.binding.transactionDate.setText(Helper.dateformat(transactionModel.getDate()));
        holder.binding.transactionCategory.setText(transactionModel.getCategory());

        if (transactionModel.getType().equals("Income")){
            holder.binding.transactionAmount.setTextColor(context.getColor(R.color.greenColor));
        } else if (transactionModel.getType().equals("Expenses")) {
            holder.binding.transactionAmount.setTextColor(context.getColor(R.color.redColor));
        }

    }

    //returing the size of array
    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    //ViewHolder class for holding the view
    public class TransactionViewHolder extends RecyclerView.ViewHolder{

        //here initiating view binding of the row_transaction layout
        RowTransactionBinding binding;

        //matching super constructor
        public TransactionViewHolder(@NonNull View itemView) {
            super(itemView);

            //and bind the item view with layout
            binding = RowTransactionBinding.bind(itemView);
        }
    }
}
