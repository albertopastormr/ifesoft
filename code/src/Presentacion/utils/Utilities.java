package Presentacion.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Utilities {

    public static Date parseIntToDate(String dateStr){

        Date date = null;
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy");
        try {
            date = formatter.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

}
