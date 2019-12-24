package com.neoniou.timaner.service;

import com.neoniou.timaner.pojo.Calender;

/**
 * @author neo.zzj
 * @date 2019-12-2
 */
public interface CalenderService {

    /**
     * 根据索引查找日历信息
     * @param index 索引
     * @return 日历对象
     */
    Calender selectCalenderByIndex(String index);
}
