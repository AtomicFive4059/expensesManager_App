package com.example.expensesmanagerapp.fragment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.expensesmanagerapp.R;
import com.example.expensesmanagerapp.databinding.RowAccountBinding;

import java.util.ArrayList;

public class Account_Adapter extends RecyclerView.Adapter<Account_Adapter.AccountViewHolder> {

    Context context;
    ArrayList<Account_Model> arrayList;

    public interface AccountsClickListener{
        void onAccountSelected(Account_Model accountModel);
    }

    AccountsClickListener accountsClickListener;

    public Account_Adapter(Context context, ArrayList<Account_Model> arrayList, AccountsClickListener accountsClickListener) {
        this.context = context;
        this.arrayList = arrayList;
        this.accountsClickListener = accountsClickListener;
    }

    @NonNull
    @Override
    public AccountViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new AccountViewHolder(LayoutInflater.from(context).inflate(R.layout.row_account,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull AccountViewHolder holder, int position) {

        Account_Model accountModel = arrayList.get(position);
        holder.binding.accontName.setText(accountModel.getAccountName());

        holder.itemView.setOnClickListener(c ->{
            accountsClickListener.onAccountSelected(accountModel);
        });
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class AccountViewHolder extends RecyclerView.ViewHolder{

        RowAccountBinding binding;
        public AccountViewHolder(@NonNull View itemView) {
            super(itemView);

            binding = RowAccountBinding.bind(itemView);
        }
    }
}
