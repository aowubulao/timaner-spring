package com.neoniou.timaner.controller;

import com.neoniou.timaner.pojo.Schedule;
import com.neoniou.timaner.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author neo.zzj
 * @date 2019-11-26
 */
@RestController
@RequestMapping("/api/timaner/schedule")
public class ScheduleController {

    @Autowired
    private ScheduleService scheduleService;

    /**
     * 查询 uid 下的所有schedule
     * @param uid 登录用户id
     * @return List<Schedule>
     */
    @GetMapping("{id}")
    public ResponseEntity<List<Schedule>> selectAllByUid(@PathVariable("id") int uid) {
        return ResponseEntity.ok(scheduleService.selectAllByUid(uid));
    }

    /**
     * 添加日程
     * @param submitSchedule 提交的schedule
     * @return null
     */
    @PostMapping
    public ResponseEntity<Void> addSchedule(Schedule submitSchedule) {
        scheduleService.addSchedule(submitSchedule);
        return ResponseEntity.ok().build();
    }

    /**
     * 根据sid删除schedule
     * @param sid schedule id
     * @return null
     */
    @DeleteMapping("{sid}")
    public ResponseEntity<Void> deleteScheduleById(@PathVariable("sid") int sid) {
        scheduleService.deleteScheduleById(sid);
        return ResponseEntity.ok().build();
    }

    @GetMapping("test")
    public ResponseEntity<Void> test() {
        scheduleService.scheduleSendEmail();
        return ResponseEntity.ok().build();
    }
}
