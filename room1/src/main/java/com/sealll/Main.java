package com.sealll;

import com.sealll.config.SpringConfig2;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author sealll
 * @time 2021/7/5 17:30
 */
public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext ioc = new AnnotationConfigApplicationContext(SpringConfig2.class);
        Object dataSource = ioc.getBean("dataSource");
        System.out.println(dataSource);
    }
}
