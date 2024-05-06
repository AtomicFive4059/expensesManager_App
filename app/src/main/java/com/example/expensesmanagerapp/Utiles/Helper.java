package com.example.expensesmanagerapp.Utiles;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Helper {

    public static String dateformat(Date date){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MMMM-yyyy");
        return simpleDateFormat.format(date);
    }
}
