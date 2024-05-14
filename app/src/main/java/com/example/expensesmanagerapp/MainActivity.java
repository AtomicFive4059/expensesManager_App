package com.example.expensesmanagerapp;

import android.os.Bundle;
import android.view.Menu;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.expensesmanagerapp.Utiles.Constant;
import com.example.expensesmanagerapp.Utiles.Helper;
import com.example.expensesmanagerapp.databinding.ActivityMainBinding;
import com.example.expensesmanagerapp.fragment.AddTransactionFragment;
import com.example.expensesmanagerapp.fragment.Transaction_Adapter;
import com.example.expensesmanagerapp.fragment.Transaction_Model;

import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

import io.realm.Realm;
import io.realm.RealmResults;

public class MainActivity extends AppCompatActivity {

    //initiating ViewBinding for the initiation of .xml tags
    ActivityMainBinding binding;

    //getting calendar object for current date and nextDate or previousDAte button clicked
    Calendar calendar = Calendar.getInstance();

    //String for storing the current date inside it
    String todayDate;

    //creating instance of Realm database
    Realm realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        //inflate layout of .xml
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        //getting and setting the root of the .xml
        setContentView(binding.getRoot());

        //setting context to Realm database
        Realm.init(this);

        //calling setupDatabase(); method here
        setupDatabase();


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });



        //setting orange color to the toolBar of the application
        setSupportActionBar(binding.toolBar);
        //set Title to the Toolbar
        Objects.requireNonNull(getSupportActionBar()).setTitle("Transaction");

        //calling setCategoris function
        Constant.setCategories();

        //calling updateDate function, that regards with the date of calender
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


        //making arraylist of transaction_model and adding data through this
       // ArrayList<Transaction_Model> transactionModelArrayList = new ArrayList<>();

        //Adding data dynamically
       //  transactionModelArrayList.add(new Transaction_Model(Constant.INCOME,"Business","Cash","Some Notes Here",new Date(),500,1));
       // transactionModelArrayList.add(new Transaction_Model(Constant.EXPENSES,"Investment","Bank","Some Notes Here",new Date(),1000,2));
      //  transactionModelArrayList.add(new Transaction_Model(Constant.INCOME,"Business","Card","Some Notes Here",new Date(),500,3));
       // transactionModelArrayList.add(new Transaction_Model(Constant.INCOME,"Other","Cash","Some Notes Here",new Date(),500,4));
      //  transactionModelArrayList.add(new Transaction_Model(Constant.EXPENSES,"Rent","Other","Some Notes Here",new Date(),500,5));



        //adding data to Realm database
       // realm.copyToRealmOrUpdate(new Transaction_Model(Constant.INCOME,"Business","Cash","Some Notes Here",new Date(),500,new Date().getTime()));
      //  realm.copyToRealmOrUpdate(new Transaction_Model(Constant.EXPENSES,"Investment","Bank","Some Notes Here",new Date(),1000,2));
      //  realm.copyToRealmOrUpdate(new Transaction_Model(Constant.INCOME,"Business","Card","Some Notes Here",new Date(),500,3));
      //  realm.copyToRealmOrUpdate(new Transaction_Model(Constant.INCOME,"Other","Cash","Some Notes Here",new Date(),500,4));
      //  realm.copyToRealmOrUpdate(new Transaction_Model(Constant.EXPENSES,"Rent","Other","Some Notes Here",new Date(),500,5));

        //RealmResults act as Arraylist and  realm.where(Transaction_Model.class).findAll(); this the Query of Realm Database for finding the data
        RealmResults<Transaction_Model> newTransaction = realm.where(Transaction_Model.class).findAll();

        //here beginTransaction
        realm.beginTransaction();

        //adding data to Realm database
        realm.copyToRealmOrUpdate(new Transaction_Model(Constant.EXPENSES,"Business","Cash","Note Come Here",new Date(),500,new Date().getTime()));
        realm.copyToRealmOrUpdate(new Transaction_Model(Constant.INCOME,"Investment","Bank","Note",new Date(),1000,new Date().getTime()));
        realm.copyToRealmOrUpdate(new Transaction_Model(Constant.INCOME,"Business","Card","Note",new Date(),450,new Date().getTime()));
        realm.copyToRealmOrUpdate(new Transaction_Model(Constant.INCOME,"Other","Cash","Note",new Date(),700,new Date().getTime()));

        //finally commitTransaction, all operation must be performed between the beginTransaction() and commitTransaction()
        realm.commitTransaction();

       // instance of Transaction_Adapter with the parameter of application context and arraylist of transaction_model
        Transaction_Adapter adapter = new Transaction_Adapter(MainActivity.this,newTransaction);

        //setting layout of Transaction list with realtime updation
        binding.transactionList.setLayoutManager(new LinearLayoutManager(this));

        //finally setting adapter to transactionList
        binding.transactionList.setAdapter(adapter);
    }



    ///////////////////////////////////////////////

    //method for setting Realm database
   public void setupDatabase(){
        //setting getDefaultInstance to Realm database
        //might be created, nullpointerexception or runtimeexecption for that assert Realm.getDefaultConfiguration() != null;
//       assert Realm.getDefaultConfiguration() != null;
       realm = Realm.getDefaultInstance();

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