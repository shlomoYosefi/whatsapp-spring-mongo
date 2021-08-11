package com.whatsappnew.whatsappnewspringmongo;

import javax.xml.crypto.Data;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public abstract class FunctionsGenerics {



    public static String getDate(){
         Date date =new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy  HH:mm:ss");
        return formatter.format(date);
    }


}
