package com.example.expensesmanagerapp.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.expensesmanagerapp.R;
import com.example.expensesmanagerapp.databinding.FragmentAddTransactionBinding;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;


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
        //  binding.ExpenseBtn.setBackground(getContext().getDrawable(R.drawable.default_selector));
       //    binding.ExpenseBtn.setTextColor(getContext().getColor(R.color.textColor));
          binding.incomeBtn.setTextColor(getContext().getColor(R.color.greenColor));
        });

        return binding.getRoot();


    }
}