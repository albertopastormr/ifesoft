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
            throw new Exception("FALLO EN LA FECHA: Inserta una fecha valida con el formato DD-MM-YYYY.\nPara mas informacion pulsa el boton de ayuda.");
        }
        return date;
    }

    public static String parseDateToString(Date dateStr){
        DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        return df.format(dateStr);
    }

}
