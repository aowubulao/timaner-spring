package com.neoniou.timaner.dao;

import com.neoniou.timaner.pojo.Schedule;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @author neo.zzj
 * @date 2019-11-26
 */
@Repository
public interface ScheduleDao extends Mapper<Schedule> {

    /**
     * 通过用户id查询所有日程
     * @param uid 登录用户id
     * @return List<Schedule>
     */
    @Select("select * from schedule where uid = #{uid} order by date ASC")
    List<Schedule> selectAllByUid(int uid);

    /**
     * 添加日程
     * @param submitSchedule 提交的schedule
     * @return 影响的行数
     */
    @Insert("insert into schedule(title, date, uid) values(#{title}, #{date}, #{uid})")
    int addSchedule(Schedule submitSchedule);

    /**
     * 根据sid删除schedule
     * @param sid schedule id
     * @return 影响的行数
     */
    @Delete("delete from schedule where sid = #{sid}")
    int deleteScheduleById(int sid);

    /**
     * 查询所有schedule
     * @return List<Schedule>
     */
    @Select("select title,date,uid from schedule")
    List<Schedule> selectAllSchedule();

}
