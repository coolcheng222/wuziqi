package com.sealll.manager.utils.impl;

import com.sealll.bean.User;
import com.sealll.config.SpringConfig2;
import com.sealll.dao.UserDao;
import com.sealll.manager.utils.TokenResolver;
import com.sealll.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.lang.annotation.Annotation;
import java.util.UUID;

/**
 * @author sealll
 * @time 2021/7/9 22:52
 */
public class SimpleTokenResolver implements TokenResolver {
    private UserService userService;
    public SimpleTokenResolver(){
        AnnotationConfigApplicationContext ioc = new AnnotationConfigApplicationContext(SpringConfig2.class);
        userService = ioc.getBean(UserService.class);
    }
    @Override
    public String getToken(Integer rid, Integer uid) {
        return UUID.randomUUID().toString().replace("-","");
    }

    @Override
    public User getByToken(String token) {
        return userService.selectUserByToken(token);
    }
}
