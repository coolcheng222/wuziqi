package com.sealll;

import com.sealll.config.SpringConfig2;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author sealll
 * @time 2021/7/5 17:30
 */
@SpringBootApplication
@EnableScheduling
@MapperScan(basePackages = "com.sealll.dao")
public class RoomApplication {
    public static void main(String[] args) {
        SpringApplication.run(RoomApplication.class);
    }
}
