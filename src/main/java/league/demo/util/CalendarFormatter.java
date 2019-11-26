package league.demo.util;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Locale;

public class CalendarFormatter {

    public static String formatCalender(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy", Locale.GERMANY);
        return sdf.format(date);
    }

}
