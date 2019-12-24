package com.neoniou.timaner.service.impl;

import com.neoniou.timaner.dao.CalenderDao;
import com.neoniou.timaner.pojo.Calender;
import com.neoniou.timaner.service.CalenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author neo.zzj
 * @date 2019-12-2
 */
@Service
public class CalenderServiceImpl implements CalenderService {

    @Autowired
    private CalenderDao calenderDao;

    @Override
    public Calender selectCalenderByIndex(String index) {
        return calenderDao.selectCalenderByIndex(index);
    }

}
