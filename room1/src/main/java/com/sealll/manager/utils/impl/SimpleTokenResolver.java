package com.sealll.manager.utils.impl;

import com.sealll.bean.User;
import com.sealll.config.SpringConfig2;
import com.sealll.dao.UserDao;
import com.sealll.manager.utils.TokenResolver;
import com.sealll.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.util.UUID;

/**
 * @author sealll
 * @time 2021/7/9 22:52
 */
@Component
public class SimpleTokenResolver implements TokenResolver {
    @Autowired
    private UserService userService;
    public SimpleTokenResolver(){
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
