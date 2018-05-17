package Presentacion.Utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Utilities {

    public static Date parseStringToDate(String dateStr) throws Exception {

        Date date = null;
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        try {
            date = formatter.parse(dateStr);
        } catch (ParseException e) {
            throw new Exception("Wrong date format: Please enter a date with the format DD-MM-YYYY.\nPress the Help Button for more informations.");
        }
        return date;
    }

    public static String parseDateToString(Date dateStr){
        DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        return df.format(dateStr);
    }

}
