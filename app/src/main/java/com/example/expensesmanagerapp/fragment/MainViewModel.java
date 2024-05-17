package com.example.expensesmanagerapp.fragment;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.expensesmanagerapp.Utiles.Constant;

import java.util.Calendar;
import java.util.Date;

import io.realm.Realm;
import io.realm.RealmResults;

//MainViewModel class form the AndroidViewModel and implementing matching super constructor
public class MainViewModel extends AndroidViewModel {

    //MutableLiveData for the live updation Of inserted data, and setting that data to UI of application by observing the method
   public MutableLiveData<RealmResults<Transaction_Model>> realmResultsMutableLiveData = new MutableLiveData<>();

    //MutableLiveData for the live updation Of total Income based on Recorded transaction, and setting that data to UI of application by observing the method
    public MutableLiveData<Double> totalIncome = new MutableLiveData<>();

    //MutableLiveData for the live updation Of total Expenses based on Recorded transaction, and setting that data to UI of application by observing the method
    public MutableLiveData<Double> totalExpense = new MutableLiveData<>();

    //MutableLiveData for the live updation Of total Amount of Transaction based on Recorded transaction, and setting that data to UI of application by observing the method
    public MutableLiveData<Double> totalAmount = new MutableLiveData<>();

   //Initiating instance of Realm database
    Realm realm;
    public MainViewModel(@NonNull Application application) {
        super(application);
        //setting context to Realm database
        Realm.init(application);

        //calling  setupDatabase(); method here
        setupDatabase();
    }

    //addTransaction() method for fetching data to Realm database
    public void getTransaction(Calendar calendar){

        //it gonna brings exact that entry, that recorded or added 12 pm not other
        calendar.set(Calendar.HOUR_OF_DAY,0);
        calendar.set(Calendar.MINUTE,0);
        calendar.set(Calendar.SECOND,0);
        calendar.set(Calendar.MILLISECOND,0);

        //so for that, means to brings other Transaction entry as well
        RealmResults<Transaction_Model> newTransaction = realm.where(Transaction_Model.class)
                //showing Transaction that recorded and entry =< 12 pn
                .greaterThanOrEqualTo("date",calendar.getTime())
                //showing Transaction that recorded and entry > 12 pn
                .lessThan("date",new Date(calendar.getTime().getTime() + (24*60*60*1000))) //this equation is for counting millisecond
                //here finding all transaction
                .findAll();

        //method for Calculation of total Income
        double income = realm.where(Transaction_Model.class)
                //showing Transaction that recorded and entry =< 12 pn
                .greaterThanOrEqualTo("date",calendar.getTime())
                //showing Transaction that recorded and entry > 12 pn
                .lessThan("date",new Date(calendar.getTime().getTime() + (24*60*60*1000)))//this equation is for counting millisecond
                //Addition of type Income
                .equalTo("type",Constant.INCOME)
                //Equation for Addition
                .sum("amount")
                //here sorting all transaction values
                .shortValue();

        //method for Calculation of total Expenses
        double expense = realm.where(Transaction_Model.class)
                //showing Transaction that recorded and entry =< 12 pn
                .greaterThanOrEqualTo("date",calendar.getTime())
                //showing Transaction that recorded and entry > 12 pn
                .lessThan("date",new Date(calendar.getTime().getTime() + (24*60*60*1000)))//this equation is for counting millisecond
                //Addition of type Expenses
                .equalTo("type",Constant.EXPENSES)
                //expression for addition of type Expenses
                .sum("amount")
                //here sorting all Transaction values
                .shortValue();


        //method for Calculation of total Amount (Addition of both Total Income + Total Expenses)
        double total = realm.where(Transaction_Model.class)
                //showing Transaction that recorded and entry =< 12 pn
                .greaterThanOrEqualTo("date",calendar.getTime())
                //showing Transaction that recorded and entry > 12 pn
                .lessThan("date",new Date(calendar.getTime().getTime() + (24*60*60*1000)))//this equation is for counting millisecond
                //Equation for Addition of both Income And Expenses
                .sum("amount")
                //and sorting Transaction values
                .shortValue();

        //setting totalIncome,totalExpense,totalAmount Values of Calculation methods
        totalIncome.setValue(income);
        totalExpense.setValue(expense);
        totalAmount.setValue(total);

        //RealmResults act as Arraylist and  realm.where(Transaction_Model.class).findAll(); this the Query of Realm Database for finding the data
//        RealmResults<Transaction_Model> newTransaction = realm.where(Transaction_Model.class)
//                .equalTo("date",calendar.getTime())
//                .findAll();

        //setting realmResultsMutableLiveData value of newTransaction for live updation of UI to User
        realmResultsMutableLiveData.setValue(newTransaction);
    }


    //addTransaction() method for inserting data to Realm database
    public void addTransaction(Transaction_Model transactionModel){

        //here beginTransaction
        realm.beginTransaction();

        //adding Transaction details in Realm database
        realm.copyToRealmOrUpdate(transactionModel);

        //adding data to Realm database
//        realm.copyToRealmOrUpdate(new Transaction_Model(Constant.EXPENSES,"Business","Cash","Note Come Here",new Date(),500,new Date().getTime()));
//        realm.copyToRealmOrUpdate(new Transaction_Model(Constant.INCOME,"Investment","Bank","Note",new Date(),1000,new Date().getTime()));
//        realm.copyToRealmOrUpdate(new Transaction_Model(Constant.INCOME,"Business","Card","Note",new Date(),450,new Date().getTime()));
//        realm.copyToRealmOrUpdate(new Transaction_Model(Constant.INCOME,"Other","Cash","Note",new Date(),700,new Date().getTime()));

        //finally commitTransaction, all operation must be performed between the beginTransaction() and commitTransaction()
        realm.commitTransaction();
    }

    //method for setting Realm database
    public void setupDatabase(){
        //might be created, nullpointerexception or runtimeexecption for that assert Realm.getDefaultConfiguration() != null;
        // assert Realm.getDefaultConfiguration() != null;

        //setting getDefaultInstance to Realm database
        realm = Realm.getDefaultInstance();
    }
}
