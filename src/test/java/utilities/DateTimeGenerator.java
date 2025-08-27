package utilities;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateTimeGenerator {
    public static String StartDateUtc (int durationInMin){

        SimpleDateFormat dateForm = new SimpleDateFormat ("MM/dd/yyyy hh:mm");
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MINUTE, durationInMin);
        Date StartDateUtc = cal.getTime();
        return dateForm.format(StartDateUtc);

    }

    public static String EndDateUtc (int durationInHour){

        SimpleDateFormat dateForm = new SimpleDateFormat ("MM/dd/yyyy hh:mm");
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.HOUR, durationInHour);
        Date EndDateUtc = cal.getTime();
        return dateForm.format(EndDateUtc);

    }
}
