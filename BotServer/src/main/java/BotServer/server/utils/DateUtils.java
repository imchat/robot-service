package BotServer.server.utils;

import org.apache.commons.lang3.StringUtils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * **************************************************************
 * <p>
 * Desc:
 * User: jianguangluo
 * Date: 2018-12-27
 * Time: 17:39
 * <p>
 * **************************************************************
 */
public class DateUtils {
    public static final String DEFALT_ADTE_PATTERN = "yyyy-MM-dd HH:mm:ss";


    public static String dateToStr(Date date) {
        return dateToStr(date, null);
    }

    public static String dateToStr(Date date, String pattern) {
        SimpleDateFormat sdf = new SimpleDateFormat();
        if (StringUtils.isEmpty(pattern)) {
            pattern = DEFALT_ADTE_PATTERN;
        }
        sdf.applyPattern(pattern);
        return sdf.format(date);
    }

    public static Date strToDate(String str) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(DEFALT_ADTE_PATTERN);
            return sdf.parse(str);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
