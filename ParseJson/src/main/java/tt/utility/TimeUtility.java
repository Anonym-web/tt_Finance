package tt.utility;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 时间格式转换
 */
public class TimeUtility extends Object {

    /**
     * 将时间转换为时间戳（毫秒单位）
     *
     * @param time（String） 格式化日期
     * @return res（String） 时间戳
     */
    public static String dateToStamp(String time) throws ParseException {
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = simpleDateFormat.parse(time);
        long ts = date.getTime();
        res = String.valueOf(ts);
        return res;
    }

    /**
     * 将时间戳转换为时间（毫秒单位）
     *
     * @param ms（String） 毫秒
     * @return res（String） 格式化日期
     */
    public static String stampToDate(String ms) {
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long lt = new Long(ms);
        Date date = new Date(lt);
        res = simpleDateFormat.format(date);
        return res;
    }

    /**
     * 秒转换为指定格式的日期
     *
     * @param second（long）   秒
     * @param patten（String） 格式
     * @return dateString（String） 格式化的日期
     */
    @SuppressWarnings("unused")
    private static String secondToDate(long second, String patten) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(second * 1000);// 转换为毫秒
        Date date = calendar.getTime();
        SimpleDateFormat format = new SimpleDateFormat(patten);
        String dateString = format.format(date);
        return dateString;
    }
}
