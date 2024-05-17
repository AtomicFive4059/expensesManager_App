package com.example.expensesmanagerapp.fragment;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;

import com.example.expensesmanagerapp.MainActivity;
import com.example.expensesmanagerapp.R;
import com.example.expensesmanagerapp.Utiles.Constant;
import com.example.expensesmanagerapp.Utiles.Helper;
import com.example.expensesmanagerapp.databinding.FragmentAddTransactionBinding;
import com.example.expensesmanagerapp.databinding.ListDialogBinding;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.util.ArrayList;
import java.util.Calendar;


//instead of extending fragment from fragment super class, extending them form BottomSheetDialogFragment that pop-up form bottom of Android OS
public class AddTransactionFragment extends BottomSheetDialogFragment {

    public AddTransactionFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    //initiated ViewBinding for this fragment
    FragmentAddTransactionBinding binding;

    //Making instance of Transaction_Model class
    Transaction_Model transactionModel;

    @SuppressLint("UseCompatLoadingForDrawables")
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentAddTransactionBinding.inflate(inflater);

        //here initiating the Transaction_Model class
        transactionModel = new Transaction_Model();

        //Handling the  binding.incomeBtn.setOnClickListener
        binding.incomeBtn.setOnClickListener(v -> {
            //setting background to incomeBtn field
          binding.incomeBtn.setBackground(getContext().getDrawable(R.drawable.income_selector));
          //setting R.drawable.default_selector to the expenses field
         binding.expenseBtn.setBackground(getContext().getDrawable(R.drawable.default_selector));
         //setting simple R.color.textColor to expenses field
           binding.expenseBtn.setTextColor(getContext().getColor(R.color.textColor));
           //setting text color R.color.greenColor to income field
          binding.incomeBtn.setTextColor(getContext().getColor(R.color.greenColor));
            //getting the type of Transaction that is Income or Expenses via incomeBtn and transactionModel
          transactionModel.setType(Constant.INCOME);
        });

        //Handling the  binding.expenses.setOnClickListener
        binding.expenseBtn.setOnClickListener(v -> {
            //setting default  R.drawable.default_selector background to incomeBtn field
            binding.incomeBtn.setBackground(getContext().getDrawable(R.drawable.default_selector));
            //setting background to income field R.drawable.expenses_selector
            binding.expenseBtn.setBackground(getContext().getDrawable(R.drawable.expenses_selector));
            //setting simple R.color.textColor to incomeBtn field
            binding.incomeBtn.setTextColor(getContext().getColor(R.color.textColor));
            //setting text color R.color.greenColor to expenseBtn field
            binding.expenseBtn.setTextColor(getContext().getColor(R.color.redColor));
            //getting the type of Transaction that is Income or Expenses via expenseBtn and transactionModel
            transactionModel.setType(Constant.EXPENSES);
        });

        //Handling the selectDate setOnClickListener to pick up the date from calendar
        binding.selectDate.setOnClickListener(v -> {

            //DatePickerDialog class and its obj by passing current context of the activity
            DatePickerDialog datePickerDialog = new DatePickerDialog(requireContext());

            //setting setOnDateSetListener for picking the date from calendar
            datePickerDialog.setOnDateSetListener(new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
                    //getting calendar obj
                    Calendar calendar = Calendar.getInstance();
                    //getting date of month
                    calendar.set(Calendar.DAY_OF_MONTH,datePicker.getDayOfMonth());
                    //getting Month of month
                    calendar.set(Calendar.MONTH,datePicker.getMonth());
                    //getting year of month
                    calendar.set(Calendar.YEAR,datePicker.getYear());

                    //setting structure of the Date format 4'M for April and 2'M for digit of the month
                   // SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMMM-yyyy");

                    //stored dateToShow in the String as per the defined format
                    String dateToShow = Helper.dateformat(calendar.getTime());

                    //finally setting all the date related things to the UI of .xml
                    binding.selectDate.setText(dateToShow);
                    //getting selected date form the AddTransaction Fragment to date label of date label
                    transactionModel.setDate(calendar.getTime());

                    //setting Unique Id to every and each Transaction base on Time laps
                    transactionModel.setId(calendar.getTime().getTime());
                }
            });

            //show datePickerDialog to User for capitation of date
            datePickerDialog.show();
        });

        //Handling the selectCategory clicked
        binding.selectCategory.setOnClickListener(v -> {
            //ListDialogBinding for list of the category to inflate
            ListDialogBinding dialogBinding = ListDialogBinding.inflate(inflater);
            //AlertDialog for showing AlertDialog to User for selection of category
            AlertDialog categoryDialog = new AlertDialog.Builder(getContext()).create();
            //setting view to categoryDialog
            categoryDialog.setView(dialogBinding.getRoot());


            //Making obj of the Category_Adapter class and passing context of activity, arraylist of Category_Model and CategoryClickListener for displaying the selected category in the Ui field
            Category_Adapter adapter = new Category_Adapter(getContext(), Constant.categoryModelArrayList, new Category_Adapter.CategoryClickListener() {
                @Override
                public void onCategoryClicked(Category_Model categoryModel) {
                    //now displaying selected category in UI element
                    binding.selectCategory.setText(categoryModel.getCategoryName());
                    //getting the Category of Transaction via selectCategory button
                    transactionModel.setCategory(categoryModel.getCategoryName());
                    //after selecting category, now its time for dismiss categoryDialog !
                    categoryDialog.dismiss();
                }
            });

            //setting dialogBinding to listRecyclerView and showing in the form of GridLayoutManager in three span
            dialogBinding.listRecyclerView.setLayoutManager(new GridLayoutManager(getContext(),3));
            // with help of dialogBinding setting Category_Adapter obj to listRecyclerView
            dialogBinding.listRecyclerView.setAdapter(adapter);
            //here show the categoryDialog user
            categoryDialog.show();
        });

        //Handling selectAccount clicked
        binding.selectAccount.setOnClickListener(c ->{
            //ListDialogBinding for list of the account to inflate
            ListDialogBinding dialogBinding = ListDialogBinding.inflate(inflater);
            //AlertDialog for showing AlertDialog to User for selection of account
            AlertDialog accountDialog = new AlertDialog.Builder(getContext()).create();
            //setting view to accountDialog
            accountDialog.setView(dialogBinding.getRoot());

            //ArrayList of Account_Model with its instance
            ArrayList<Account_Model> accountModelArrayList = new ArrayList<>();;

            //adding data to constructor of Account_Model
            accountModelArrayList.add(new Account_Model(200,"Cash"));
            accountModelArrayList.add(new Account_Model(300,"Bank"));
            accountModelArrayList.add(new Account_Model(450,"Card"));
            accountModelArrayList.add(new Account_Model(580,"PayTm"));
            accountModelArrayList.add(new Account_Model(600,"EasyPaisa"));
            accountModelArrayList.add(new Account_Model(400,"Other"));

            //Making obj of the Account_Adapter class and passing context of activity, arraylist of Category_Model and AccountsClickListener for displaying the selected Account in the Ui field
            Account_Adapter adapter = new Account_Adapter(getContext(), accountModelArrayList, new Account_Adapter.AccountsClickListener() {
                @Override
                public void onAccountSelected(Account_Model accountModel) {
                    //now displaying selected category in UI element
                    binding.selectAccount.setText(accountModel.getAccountName());
                    //getting the Account of Transaction via selectAccount button click means User defines it by it self
                    transactionModel.setAccount(accountModel.getAccountName());
                    //after selecting account, now its time for dismiss categoryDialog !
                    accountDialog.dismiss();
                }
            });
            //setting dialogBinding to listRecyclerView and showing in the form of LinearLayout
            dialogBinding.listRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            //putting some DividerItemDecoration to listRecyclerView via DividerItemDecoration by passing context of the activity
            dialogBinding.listRecyclerView.addItemDecoration(new DividerItemDecoration(getContext(),DividerItemDecoration.VERTICAL));
            // with help of dialogBinding setting Category_Adapter obj to listRecyclerView
            dialogBinding.listRecyclerView.setAdapter(adapter);
            //here show the categoryDialog user
            accountDialog.show();
        });

        //Handling saveTransaction onClick and try to save the Transaction Details to Realm Database
        binding.saveTransaction.setOnClickListener(v -> {
            //getting Transaction Amount from User in the amount variable
            double amount = Double.parseDouble(binding.selectAmount.getText().toString());

            //getting Transaction Notes from User in the amount variable
            String note = binding.makeNotes.getText().toString();

            if (transactionModel.getType().equals(Constant.EXPENSES)){
                // if Expenses set and appear it in negative Transaction amount
                transactionModel.setAmount(amount*-1);
            }else {
               // transactionModel.getType().equals(Constant.INCOME);
                // if Income set and display it in Positive Transaction amount
               transactionModel.setAmount(amount);
            }

            //setting note
            transactionModel.setNote(note);

            //adding Transaction to transactionModel obj of Transaction_Model class
            ((MainActivity)getActivity()).viewModel.addTransaction(transactionModel);
            //For the live updation of Recorded Transaction. Means immediate reflated to UI
            ((MainActivity)getActivity()).getLiveTransactionUpdation();
            dismiss();

        });

        //returning the root of .xml file
        return binding.getRoot();


    }
}