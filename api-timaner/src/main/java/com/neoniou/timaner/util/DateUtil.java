package com.neoniou.timaner.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author neo.zzj
 * @date 2019/12/7
 */
public class DateUtil {

    /**
     * 将当前时间转化为 yyyy-MM-dd HH:mm
     * @return formatDate
     */
    public static String getFormatTime() {
        SimpleDateFormat sdf =new SimpleDateFormat("yyyy-MM-dd HH:mm" );
        Date date= new Date();
        return sdf.format(date);
    }
}
