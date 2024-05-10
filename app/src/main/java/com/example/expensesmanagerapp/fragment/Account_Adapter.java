package com.example.expensesmanagerapp.fragment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.expensesmanagerapp.R;
import com.example.expensesmanagerapp.Utiles.Constant;
import com.example.expensesmanagerapp.databinding.RowAccountBinding;

import java.util.ArrayList;

//Account_Adapter extending RecyclerView.Adapter
public class Account_Adapter extends RecyclerView.Adapter<Account_Adapter.AccountViewHolder> {

    //Context of the current activity
    Context context;

    //ArrayList of the Account_Model class
    ArrayList<Account_Model> arrayList;

    //creating a interface for handling the clicked event of "select Account" tag and display the selected account type in the field
    public interface AccountsClickListener{
        void onAccountSelected(Account_Model accountModel);
    }

    //obj of AccountsClickListener interface
    AccountsClickListener accountsClickListener;

    //constructor of the class with parameter
    public Account_Adapter(Context context, ArrayList<Account_Model> arrayList, AccountsClickListener accountsClickListener) {
        this.context = context;
        this.arrayList = arrayList;
        this.accountsClickListener = accountsClickListener;
    }

    //three methods of adapter class
    //1. onCreateViewHolder for inflating the layout
    @NonNull
    @Override
    public AccountViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //AccountViewHolder class for holding the view
        return new AccountViewHolder(LayoutInflater.from(context).inflate(R.layout.row_account,parent,false));
    }

    //onBindViewHolder for binding the view and data of the Account_Model class
    @Override
    public void onBindViewHolder(@NonNull AccountViewHolder holder, int position) {
        //getting position of the element and data of Account_Model
        Account_Model accountModel = arrayList.get(position);
        //getting Account Name from position of Account_Model and setting it to UI tags of layout
        holder.binding.accontName.setText(accountModel.getAccountName());

       // holder.binding.accontName.setBackgroundTintList(context.getColorStateList(Constant.getAccountsColors(accountModel.getAccountName())));

        //instead of setting background, i'm going to set text color of the account name
        holder.binding.accontName.setTextColor(context.getColor(Constant.getAccountsColors(accountModel.getAccountName())));

        //handling the onClick of the itemView
        holder.itemView.setOnClickListener(c ->{
            accountsClickListener.onAccountSelected(accountModel);
        });
    }

    //returning the ItemCount means size of array
    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    //ViewHolder class extending from RecyclerView.ViewHolder
    public class AccountViewHolder extends RecyclerView.ViewHolder{

        //initiating View Binding here
        RowAccountBinding binding;

        //matching super constructor
        public AccountViewHolder(@NonNull View itemView) {
            super(itemView);
            //binding view via View Binding
            binding = RowAccountBinding.bind(itemView);
        }
    }
}
