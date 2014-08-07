/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package internship.issuetracker.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.joda.time.DateTime;
import org.joda.time.Interval;

/**
 *
 * @author atataru
 */
public class DateFormatUtil {

    static class TimeUtils {

        private final long days;
        private final long hours;
        private final long minutes;
        private final long seconds;

        public TimeUtils(Interval interval) {
            days = interval.toDuration().getStandardDays();

            hours = interval.toDuration().getStandardHours() - days * 24;

            minutes = interval.toDuration().getStandardMinutes()
                    - days * 24 * 60 - hours * 60;

            seconds = interval.toDuration().getStandardSeconds()
                    - days * 24 * 60 * 60 - hours * 60 * 60 - minutes * 60;
        }

        public long getDays() {
            return days;
        }

        public long getHours() {
            return hours;
        }

        public long getMinutes() {
            return minutes;
        }

        public long getSeconds() {
            return seconds;
        }
    }

    private DateFormatUtil() {

    }

    public static String getDateFormat2(Date date) {
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        return dateFormat.format(date);
    }

    public static String appendAgo(StringBuilder date, boolean needsAgo) {
        if (needsAgo) {
            date.append("ago");
        }
        return date.toString();
    }

    public static boolean createDate(StringBuilder result, TimeUtils time, Date oldDate) {
        boolean agoFlag = true;
        if (time.getDays() == 1) {
            result.append(" 1 day ");
        } else if (time.getDays() > 1 && time.getDays() < 14) {
            result.append(time.getDays()).append(" days  ");
        } else if (time.getDays() >= 14) {
            result.append(getDateFormat2(oldDate));
            agoFlag = false;
        } else if (time.getHours() == 1) {
            result.append(" 1 hour ");
        } else if (time.getHours() > 1) {
            result.append(time.getHours()).append(" hours ");
        } else if (time.getMinutes() == 1) {
            result.append(" 1 minute ");
        } else if (time.getMinutes() > 1) {
            result.append(time.getMinutes()).append(" minutes ");
        } else if (time.getSeconds() == 1) {
            result.append(" 1 second ");
        } else if (time.getSeconds() < 1) {
            result.append(" just now ");
            agoFlag = false;
        } else {
            result.append(time.getSeconds()).append(" seconds ");
        }
        return agoFlag;
    }
            
    public static String getFriendlyInterval(Date oldDate) {
        Interval interval = new Interval(new DateTime(oldDate), new DateTime(new Date()));
        StringBuilder result = new StringBuilder();
        TimeUtils time = new TimeUtils(interval);
        boolean agoFlag = createDate(result, time, oldDate);
        return appendAgo(result, agoFlag);
    }
}
