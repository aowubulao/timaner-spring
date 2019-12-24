package com.neoniou.timaner.dao;

import com.neoniou.timaner.pojo.Calender;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * @author neo.zzj
 * @date 2019-12-2
 */
@Repository
public interface CalenderDao {

    /**
     * 根据索引查找日历信息
     * @param index 索引
     * @return 日历对象
     */
    @Select("select * from calender where c_index = #{index}")
    Calender selectCalenderByIndex(String index);

}
