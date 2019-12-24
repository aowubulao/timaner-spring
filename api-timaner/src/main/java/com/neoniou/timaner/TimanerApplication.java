package com.neoniou.timaner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @author neo.zzj
 */
@SpringBootApplication
@MapperScan("com.neoniou.timaner.dao")
@EnableScheduling
public class TimanerApplication {

    public static void main(String[] args) {
        SpringApplication.run(TimanerApplication.class, args);
    }
}
