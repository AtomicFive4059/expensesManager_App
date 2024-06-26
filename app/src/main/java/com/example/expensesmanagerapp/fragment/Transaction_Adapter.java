package com.example.expensesmanagerapp.fragment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.expensesmanagerapp.R;
import com.example.expensesmanagerapp.Utiles.Constant;
import com.example.expensesmanagerapp.Utiles.Helper;
import com.example.expensesmanagerapp.databinding.RowTransactionBinding;

import io.realm.RealmResults;

public class Transaction_Adapter extends RecyclerView.Adapter<Transaction_Adapter.TransactionViewHolder> {

    Context context;
   // ArrayList<Transaction_Model> arrayList;

    //Query of Realm database, for inserting data just like ArrayList
    RealmResults<Transaction_Model> transactionModelRealmResults;

    public Transaction_Adapter(Context context, RealmResults<Transaction_Model> transactionModelRealmResults) {
        this.context = context;
        this.transactionModelRealmResults = transactionModelRealmResults;
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
        Transaction_Model transactionModel = transactionModelRealmResults.get(position);

        //getting and setting transactionAmount to Transaction list
        holder.binding.transactionAmount.setText(String.valueOf(transactionModel.getAmount()));
        //getting and setting accountLabel to Transaction list
        holder.binding.transactionAccount.setText(transactionModel.getAccount());

       // Helper helper = new Helper(); instead of creating a every time obj of same class, we gonna do that particular method and function as a static and call directly

        //by creating instance of Category_Model and Constant.getCategoryDetails(transactionModel.getCategory()) checking is equal to or not
        Category_Model categoryModel = Constant.getCategoryDetails(transactionModel.getCategory());
        //getting and setting categoryIcon to Transaction list
        holder.binding.categoryIcon.setImageResource(categoryModel != null ? categoryModel.getCategoryImage() : 0);
        //getting and setting setBackgroundTintList to Transaction list
        assert categoryModel != null;
        holder.binding.categoryIcon.setBackgroundTintList(context.getColorStateList(categoryModel.getCategoryColor()));

        //holder.binding.categoryIcon.setImageResource(categoryModel22.getCategoryImage());

      //  holder.binding.categoryIcon.setBackgroundTintList(context.getColorStateList(categoryModel22 != null ? categoryModel22.getCategoryColor() : 0));

        //getting and setting transactionDate to Transaction list with help of Helper class
        holder.binding.transactionDate.setText(Helper.dateformat(transactionModel.getDate()));
        //getting and setting transactionCategory to Transaction list
        holder.binding.transactionCategory.setText(transactionModel.getCategory());

        //setting background color to accountLabel with help of getAccountsColors method and color resource folder
//       holder.binding.accountLabel.setBackgroundTintList(context.getColorStateList(Constant.getAccountsColors(transactionModel.getAccount())));

        //instead of setting background, i'm going to set text color for feel of UI
//      holder.binding.accountLabel.setTextColor(context.getColor(Constant.getAccountsColors(transactionModel.getAccount())));

        //setting Textcolor of Transaction amount on basis of Income and Expenses
        if (transactionModel.getType().equals(Constant.INCOME)){
            //if Income,set Green
            holder.binding.transactionAmount.setTextColor(context.getColor(R.color.greenColor));
        } else if (transactionModel.getType().equals(Constant.EXPENSES)) {
            //otherwise, to expenses Red
            holder.binding.transactionAmount.setTextColor(context.getColor(R.color.redColor));
        }

    }

    //returning the size of array
    @Override
    public int getItemCount() {
        return transactionModelRealmResults.size();
    }

    //ViewHolder class for holding the view
    public static class  TransactionViewHolder extends RecyclerView.ViewHolder{

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
