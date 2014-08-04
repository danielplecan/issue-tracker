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
    
    public static String getDateFormat2(Date date) {
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        return dateFormat.format(date);
    }
    
    public static String getFriendlyInterval(Date oldDate) {
        DateTime oldTimeInstant = new DateTime(oldDate);
        DateTime newTimeInstant = new DateTime(new Date());
        Interval interval = new Interval(oldTimeInstant, newTimeInstant);
        StringBuilder result = new StringBuilder();

        long days = interval.toDuration().getStandardDays();

        long hours = interval.toDuration().getStandardHours() - days * 24;

        long minutes = interval.toDuration().getStandardMinutes() - days * 24 * 60 - hours * 60;

        long seconds = interval.toDuration().getStandardSeconds() - days * 24 * 60 * 60 - hours * 60 * 60 - minutes * 60;

        boolean agoFlag = true;

        if (days == 1) {
            result.append(" 1 day ");
        } else if (days > 1 && days < 14) {
            result.append(days).append(" days  ");
        } else if (days >= 14) {
            result.append(getDateFormat2(oldDate));
            return result.toString();
        } else if (hours == 1) {
            result.append(" 1 hour ");
        } else if (hours > 1) {
            result.append(hours).append(" hours ");
        } else if (minutes == 1) {
            result.append(" 1 minute ");
        } else if (minutes > 1) {
            result.append(minutes).append(" minutes ");
        } else if (seconds == 1) {
            result.append(" 1 second ");
        } else if (seconds < 1) {
            result.append(" just now ");
            agoFlag = false;
        } else {
            result.append(seconds).append(" seconds ");
        }

        if (agoFlag) {
            result.append("ago");
        }
        return result.toString();
    }
}
