package com.example.expensesmanagerapp.fragment;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.LinearLayout;

import com.example.expensesmanagerapp.R;
import com.example.expensesmanagerapp.databinding.FragmentAddTransactionBinding;
import com.example.expensesmanagerapp.databinding.ListDialogBinding;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.text.SimpleDateFormat;
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentAddTransactionBinding.inflate(inflater);

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
        });

        //Handling the selectDate setOnClickListener to pick up the date from calendar
        binding.selectDate.setOnClickListener(v -> {
            //DatePickerDialog class and its obj by passing current context of the activity
            DatePickerDialog datePickerDialog = new DatePickerDialog(getContext());

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
                    SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMMM-yyyy");

                    //stored dateToShow in the String as per the defined format
                    String dateToShow = dateFormat.format(calendar.getTime());

                    //finally setting all the date related things to the UI of .xml
                    binding.selectDate.setText(dateToShow);
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

            //ArrayList of Category_Model with its instance
            ArrayList<Category_Model> categoryModelArrayList = new ArrayList<>();

            //adding data to constructor of Category_Model
            categoryModelArrayList.add(new Category_Model("Salary",R.drawable.salary,R.color.category1));
            categoryModelArrayList.add(new Category_Model("Business",R.drawable.firm,R.color.category2));
            categoryModelArrayList.add(new Category_Model("Investment",R.drawable.investment,R.color.category3));
            categoryModelArrayList.add(new Category_Model("Loan",R. drawable.loan,R.color.category4));
            categoryModelArrayList.add(new Category_Model("Rent",R.drawable.rent,R.color.category5));
            categoryModelArrayList.add(new Category_Model("Other",R.drawable.other_menu,R.color.category6));

            //Making obj of the Category_Adapter class and passing context of activity, arraylist of Category_Model and CategoryClickListener for displaying the selected category in the Ui field
            Category_Adapter adapter = new Category_Adapter(getContext(), categoryModelArrayList, new Category_Adapter.CategoryClickListener() {
                @Override
                public void onCategoryClicked(Category_Model categoryModel) {
                    //now displaying selected category in UI element
                    binding.selectCategory.setText(categoryModel.getCategoryName());
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
            accountModelArrayList.add(new Account_Model(580,"PayTm"));
            accountModelArrayList.add(new Account_Model(600,"EasyPaisa"));
            accountModelArrayList.add(new Account_Model(400,"Other"));

            //Making obj of the Account_Adapter class and passing context of activity, arraylist of Category_Model and AccountsClickListener for displaying the selected Account in the Ui field
            Account_Adapter adapter = new Account_Adapter(getContext(), accountModelArrayList, new Account_Adapter.AccountsClickListener() {
                @Override
                public void onAccountSelected(Account_Model accountModel) {
                    //now displaying selected category in UI element
                    binding.selectAccount.setText(accountModel.getAccountName());
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

        //returning the root of .xml file
        return binding.getRoot();


    }
}