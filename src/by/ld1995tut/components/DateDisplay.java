package by.ld1995tut.components;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateDisplay {
    private static Date dateNow = new Date();
    private static final DateFormat dateFormat = new SimpleDateFormat("dd.MM.yy");
    private static final int MINUTES_PER_HOUR = 60, SECONDS_IN_A_MINUTE = 60, MILLISECONDS_IN_A_SECOND = 1000, HOURS_IN_THE_DAY = 24;

    public String display(Date dateMessages)
    {
        if (dateFormat.format(dateMessages).equals(dateFormat.format(dateNow.getTime()))) {
            long timeMs = dateNow.getTime() - dateMessages.getTime();
            long seconds = (timeMs / MILLISECONDS_IN_A_SECOND)/SECONDS_IN_A_MINUTE;
            long minutes = seconds % SECONDS_IN_A_MINUTE;
            long hours = (seconds / MINUTES_PER_HOUR) % HOURS_IN_THE_DAY;
            if (hours > 0) {
                return (hours + " час.");
            } else {
                return (minutes + " мин.");
            }
        } else {
            return (dateFormat.format(dateMessages));
        }
    }
}
