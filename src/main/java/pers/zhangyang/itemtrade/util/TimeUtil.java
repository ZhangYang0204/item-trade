package pers.zhangyang.itemtrade.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeUtil {




    public static long getCurrentTime(){
        return  System.currentTimeMillis();
    }

    public static String getTimeFromTimeMill(long mill){

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        Date date = new Date(mill);
        String dateStr = sdf.format(date);
        return dateStr;


    }




}
