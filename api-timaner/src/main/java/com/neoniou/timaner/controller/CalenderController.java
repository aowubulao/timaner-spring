package com.neoniou.timaner.controller;

import com.neoniou.timaner.pojo.Calender;
import com.neoniou.timaner.service.CalenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author neo.zzj
 * @date 2019-12-2
 */
@RestController
@RequestMapping("/api/timaner/calender")
public class CalenderController {

    @Autowired
    private CalenderService calenderService;

    /**
     * 根据索引查找日历信息
     * @param index 索引
     * @return 日历对象
     */
    @GetMapping("{index}")
    public ResponseEntity<Calender> selectCalenderByIndex(@PathVariable("index") String index) {
        return ResponseEntity.ok().body(calenderService.selectCalenderByIndex(index));
    }
}
