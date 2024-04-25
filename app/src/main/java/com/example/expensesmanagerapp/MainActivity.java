package com.example.expensesmanagerapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.expensesmanagerapp.databinding.ActivityMainBinding;
import com.example.expensesmanagerapp.fragment.AddTransactionFragment;

public class MainActivity extends AppCompatActivity {

    //initiating ViewBinding for the initiation of .xml tags
    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        //inflate layout of .xml
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        //gettig and setting the root of the .xml
        setContentView(binding.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        //setting orange color to the toolBar of the application
        setSupportActionBar(binding.toolBar);
        //set Title to the Toolbar
        getSupportActionBar().setTitle("Transaction");

        binding.floatingActionButton.setOnClickListener(c -> {
            new AddTransactionFragment().show(getSupportFragmentManager(),null);
        });
    }

    //method for display the top_menu with action icon on ToolBar left side by setting actionMode "always" on
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //inflate the top_menu to toolbar
        getMenuInflater().inflate(R.menu.top_menu,menu);
        //return the menu of resource file
        return super.onCreateOptionsMenu(menu);
    }
}