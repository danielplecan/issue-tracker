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

    public static class TimeUtils {

        private boolean agoFlag;
        private final StringBuilder result;
        private final Date oldDate;
        private final long days;
        private final long hours;
        private final long minutes;
        private final long seconds;
        private boolean timeFlag;

        public TimeUtils(Interval interval, Date date) {
            result = new StringBuilder();
            agoFlag = true;
            timeFlag = false;
            
            if(date != null) {
                oldDate = new Date(date.getTime());
            } else {
                oldDate = null;
            }
            

            days = interval.toDuration().getStandardDays();

            hours = interval.toDuration().getStandardHours() - days * 24;

            minutes = interval.toDuration().getStandardMinutes()
                    - days * 24 * 60 - hours * 60;

            seconds = interval.toDuration().getStandardSeconds()
                    - days * 24 * 60 * 60 - hours * 60 * 60 - minutes * 60;

        }

        public StringBuilder getResult() {
            return result;
        }

        public boolean getAgoFlag() {
            return agoFlag;
        }

        public void appendDays() {
            if (days == 1) {
                result.append(" 1 day ");
                timeFlag = true;
            } else if (days > 1 && days < 14) {
                result.append(days).append(" days ");
                timeFlag = true;
            } else if (days >= 14) {
                result.append(getDateFormat2(oldDate));
                agoFlag = false;
                timeFlag = true;
            }
        }

        public void appendHours() {
            if(timeFlag) {
                return;
            }
            
            if (hours == 1) {
                result.append(" 1 hour ");
                timeFlag = true;
            } else if (hours > 1) {
                result.append(hours).append(" hours ");
                timeFlag = true;
            }
        }

        public void appendMinutes() {
            if(timeFlag) {
                return;
            }
            
            if (minutes == 1) {
                result.append(" 1 minute ");
                timeFlag = true;
            } else if (minutes > 1) {
                result.append(minutes).append(" minutes ");
                timeFlag = true;
            }
        }

        public void appendSeconds() {
            if(timeFlag) {
                return;
            }
            
            if (seconds == 1) {
                result.append(" 1 second ");
                timeFlag = true;
            } else if (seconds < 1) {
                result.append(" just now ");
                agoFlag = false;
                timeFlag = true;
            } else {
                result.append(seconds).append(" seconds ");
            }
        }

        public void createDate() {
            appendDays();
            appendHours();
            appendMinutes();
            appendSeconds();
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

    public static String getFriendlyInterval(Date oldDate) {
        Interval interval = new Interval(new DateTime(oldDate), new DateTime(new Date()));
        TimeUtils time = new TimeUtils(interval, oldDate);
        time.createDate();
        return appendAgo(time.getResult(), time.getAgoFlag());
    }
}
