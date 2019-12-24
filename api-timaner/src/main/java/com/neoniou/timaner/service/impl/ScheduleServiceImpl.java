package com.neoniou.timaner.service.impl;

import com.mitchellbosecke.pebble.PebbleEngine;
import com.mitchellbosecke.pebble.template.PebbleTemplate;
import com.neoniou.timaner.dao.ScheduleDao;
import com.neoniou.timaner.dao.UserDao;
import com.neoniou.timaner.pojo.Schedule;
import com.neoniou.timaner.pojo.User;
import com.neoniou.timaner.service.ScheduleService;
import com.neoniou.timaner.util.DateUtil;
import com.neoniou.timaner.util.exception.ExceptionEnum;
import com.neoniou.timaner.util.exception.MyException;
import io.github.biezhi.ome.OhMyEmail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * @author neo.zzj
 * @date 2019-11-26
 */
@Service
public class ScheduleServiceImpl implements ScheduleService {

    @Autowired
    private ScheduleDao scheduleDao;
    @Autowired
    private UserDao userDao;

    private static String password;

    static {
        Properties props = new Properties();
        InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream("ome.properties");
        try {
            props.load(is);
            password = props.getProperty("password");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Schedule> selectAllByUid(int uid) {
        List<Schedule> schedules = scheduleDao.selectAllByUid(uid);
        if (CollectionUtils.isEmpty(schedules)) {
            throw new MyException(ExceptionEnum.SCHEDULE_NOT_FOUND);
        }

        return schedules;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addSchedule(Schedule submitSchedule) {
        int count = scheduleDao.addSchedule(submitSchedule);
        if (count != 1) {
            throw new MyException(ExceptionEnum.SCHEDULE_ADD_FAIL);
        }
    }

    @Override
    public void deleteScheduleById(int sid) {
        int count = scheduleDao.deleteScheduleById(sid);
        if (count != 1) {
            throw new MyException(ExceptionEnum.DELETE_SCHEDULE_FAIL);
        }
    }

    @Override
    @Scheduled(cron = "0 * * * * ?")
    public void scheduleSendEmail() {
        //获取所有schedule
        List<Schedule> schedules = scheduleDao.selectAllSchedule();
        for (Schedule schedule : schedules) {
            //时间匹配便发送邮件
            if (DateUtil.getFormatTime().equals(schedule.getDate())) {
                User user = userDao.selectEmailByUid(schedule.getUid());
                String email = user.getEmail();
                //发送邮箱，代码冗长，后期提取一下
                try {
                    OhMyEmail.config(OhMyEmail.SMTP_QQ(true), "notify@neoniou.com", password);
                    //pebble
                    PebbleEngine engine = new PebbleEngine.Builder().build();
                    PebbleTemplate compiledTemplate = engine.getTemplate("notify.html");

                    Map<String, Object> context = new HashMap<String, Object>();
                    context.put("title", schedule.getTitle());

                    Writer writer = new StringWriter();
                    compiledTemplate.evaluate(writer, context);

                    String output = writer.toString();

                    //send
                    OhMyEmail.subject("提醒事项")
                            .from("Timaner")
                            .to(email)
                            .html(output)
                            .send();
                } catch (Exception e) {
                    throw new MyException(ExceptionEnum.MAIL_SEND_FAIL);
                }
            }
        }
    }
}
