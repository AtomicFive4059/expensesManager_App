package com.example.expensesmanagerapp;

import android.os.Bundle;
import android.os.Handler;
import android.provider.CalendarContract;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.Menu;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.expensesmanagerapp.Utiles.Helper;
import com.example.expensesmanagerapp.databinding.ActivityMainBinding;
import com.example.expensesmanagerapp.fragment.AddTransactionFragment;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    //initiating ViewBinding for the initiation of .xml tags
    ActivityMainBinding binding;

    //getting calendar object for current date and nextDate or previousDAte button clicked
    Calendar calendar = Calendar.getInstance();

    //String for storing the current date inside it
    String todayDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        //inflate layout of .xml
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        //getting and setting the root of the .xml
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

        //calling updateDate function
        updateDate();

        //Handling the nextDateBtn to forward the date
        binding.nextDate.setOnClickListener(c ->{
            calendar.add(Calendar.DATE,+1);
            updateDate();
        });

        //Handling the previousDateBtn to backward the date
        binding.previousDate.setOnClickListener(c ->{
            calendar.add(Calendar.DATE,-1);
            updateDate();
        });

        //Handling the clicked event on linearLayout for jumped to the current date of world
       // SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MMMM-yyyy");
        todayDate = Helper.dateformat(calendar.getTime());

        //setting setOnClickListener to managed the clicked event
        binding.linearLayout.setOnClickListener(c ->{
            binding.currentDate.setText(todayDate);
        });


        //to handle the floatingActionButton clicked, then show the BottomSheetFragment to the user for addition of exp or income transaction
        binding.floatingActionButton.setOnClickListener(c -> {
            //this method is ex. of lambda expression in android studio and passing null tag
            new AddTransactionFragment().show(getSupportFragmentManager(),null);
        });
    }

    //outside function that is created outside of the onCreate method

    //this method show the current date to the user on main screen of application call immediately
    public void updateDate(){
        //setting SimpleDateFormat for formatting the date structure
     //   SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MMMM-yyyy");
        //binding thar date structure to the UI of .xml
        binding.currentDate.setText(Helper.dateformat(calendar.getTime()));
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