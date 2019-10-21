package com.revanwang.utils;

import java.util.Calendar;
import java.util.Date;

public class RevanDateUtil {

    /**
     * 把时间设置为开始时间
     * @param beginDate 2019-10-22 00:23:03
     * @return 2019-10-22 00:00:00
     */
    public static Date revan_beginDate(Date beginDate) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(beginDate);
        //手动设置日历的年月日，时分秒都清零
        calendar.set(calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DATE),
                0, 0, 0);
        System.out.println(calendar.getTime().toLocaleString());
        return calendar.getTime();
    }


    /**
     * 把时间设置为结束时间
     * @param endDate 2019-10-22 00:23:03
     * @return 2019-10-22 23:59:59
     */
    public static Date revan_endDate(Date endDate) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(endDate);
        //手动设置日历的年月日，时分秒都清零
        calendar.set(calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DATE),
                0, 0, 0);
        //天数加1
        calendar.add(Calendar.DATE, 1);
        //秒数-1
        calendar.add(Calendar.SECOND, -1);
        System.out.println(calendar.getTime().toLocaleString());
        return calendar.getTime();
    }

}
