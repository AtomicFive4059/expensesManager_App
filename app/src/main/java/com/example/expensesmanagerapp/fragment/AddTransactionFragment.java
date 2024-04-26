package com.example.expensesmanagerapp.fragment;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
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

        binding.incomeBtn.setOnClickListener(v -> {
          binding.incomeBtn.setBackground(getContext().getDrawable(R.drawable.income_selector));
         binding.expenseBtn.setBackground(getContext().getDrawable(R.drawable.default_selector));
           binding.expenseBtn.setTextColor(getContext().getColor(R.color.textColor));
          binding.incomeBtn.setTextColor(getContext().getColor(R.color.greenColor));
        });

        binding.expenseBtn.setOnClickListener(v -> {
            binding.incomeBtn.setBackground(getContext().getDrawable(R.drawable.default_selector));
            binding.expenseBtn.setBackground(getContext().getDrawable(R.drawable.expenses_selector));
            binding.incomeBtn.setTextColor(getContext().getColor(R.color.textColor));
            binding.expenseBtn.setTextColor(getContext().getColor(R.color.redColor));
        });

        binding.selectDate.setOnClickListener(v -> {
            DatePickerDialog datePickerDialog = new DatePickerDialog(getContext());

            datePickerDialog.setOnDateSetListener(new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
                    Calendar calendar = Calendar.getInstance();
                    calendar.set(Calendar.DAY_OF_MONTH,datePicker.getDayOfMonth());
                    calendar.set(Calendar.MONTH,datePicker.getMonth());
                    calendar.set(Calendar.YEAR,datePicker.getYear());

                    SimpleDateFormat dateFormat = new SimpleDateFormat("dd-mm-yyyy");
                    String dateToshow = dateFormat.format(calendar.getTime());

                    binding.selectDate.setText(dateToshow);
                }
            });

            datePickerDialog.show();
        });

        binding.selectCategory.setOnClickListener(v -> {
            ListDialogBinding dialogBinding = ListDialogBinding.inflate(inflater);
            AlertDialog categoryDialog = new AlertDialog.Builder(getContext()).create();
            categoryDialog.setView(dialogBinding.getRoot());

            ArrayList<Category_Model> categoryModelArrayList = new ArrayList<>();

            categoryModelArrayList.add(new Category_Model("Salary",R.drawable.salary,R.color.category1));
            categoryModelArrayList.add(new Category_Model("Business",R.drawable.firm,R.color.category2));
            categoryModelArrayList.add(new Category_Model("Investment",R.drawable.investment,R.color.category3));
            categoryModelArrayList.add(new Category_Model("Loan",R. drawable.loan,R.color.category4));
            categoryModelArrayList.add(new Category_Model("Rent",R.drawable.rent,R.color.category5));
            categoryModelArrayList.add(new Category_Model("Other",R.drawable.other_menu,R.color.category6));

            Category_Adapter adapter = new Category_Adapter(getContext(), categoryModelArrayList, new Category_Adapter.CategoryClickListener() {
                @Override
                public void onCategoryClicked(Category_Model categoryModel) {
                    binding.selectCategory.setText(categoryModel.getCategoryName());
                    categoryDialog.dismiss();
                }
            });

            dialogBinding.listRecyclerView.setLayoutManager(new GridLayoutManager(getContext(),3));
            dialogBinding.listRecyclerView.setAdapter(adapter);

            categoryDialog.show();
        });

        binding.selectAccount.setOnClickListener(c ->{
            ListDialogBinding dialogBinding = ListDialogBinding.inflate(inflater);
            AlertDialog accountDialog = new AlertDialog.Builder(getContext()).create();
            accountDialog.setView(dialogBinding.getRoot());

            ArrayList<Account_Model> accountModelArrayList = new ArrayList<>();;

            accountModelArrayList.add(new Account_Model(200,"Cash"));
            accountModelArrayList.add(new Account_Model(300,"Bank"));
            accountModelArrayList.add(new Account_Model(580,"PayTm"));
            accountModelArrayList.add(new Account_Model(600,"EasyPais"));
            accountModelArrayList.add(new Account_Model(400,"Other"));

            Account_Adapter adapter = new Account_Adapter(getContext(), accountModelArrayList, new Account_Adapter.AccountsClickListener() {
                @Override
                public void onAccountSelected(Account_Model accountModel) {
                    binding.selectAccount.setText(accountModel.getAccountName());
                    accountDialog.dismiss();
                }
            });
            dialogBinding.listRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            dialogBinding.listRecyclerView.setAdapter(adapter);
            accountDialog.show();
        });

        return binding.getRoot();


    }
}