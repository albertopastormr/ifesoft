package Presentacion.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Utilities {

    public static Date parseStringToDate(String dateStr){

        Date date = null;
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        try {
            date = formatter.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    public static String parseDateToString(Date dateStr){
        DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        return df.format(dateStr);
    }

}
