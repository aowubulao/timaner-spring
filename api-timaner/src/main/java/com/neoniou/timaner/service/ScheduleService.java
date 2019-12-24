package com.neoniou.timaner.service;

import com.neoniou.timaner.pojo.Schedule;

import java.util.List;

/**
 * @author neo.zzj
 * @date 2019-11-26
 */
public interface ScheduleService {

    /**
     * 通过用户id查询所有日程
     * @param uid 登录用户id
     * @return List<Schedule>
     */
    List<Schedule> selectAllByUid(int uid);

    /**
     * 添加日程
     * @param submitSchedule 提交的schedule
     */
    void addSchedule(Schedule submitSchedule);

    /**
     * 根据sid删除schedule
     * @param sid schedule id
     */
    void deleteScheduleById(int sid);

    /**
     * 定时发送提醒邮件
     */
    void scheduleSendEmail();
}
