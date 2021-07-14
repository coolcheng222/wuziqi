package com.sealll.service.impl;

import com.sealll.bean.User;
import com.sealll.dao.UserDao;
import com.sealll.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author sealll
 * @time 2021/7/6 21:07
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;
    @Override
    public boolean createUser(User user) {
        return userDao.createUser(user);
    }

    @Override
    public boolean removeUserSelective(User user) {
        return userDao.removeUserSelective(user);
    }

    @Override
    public User selectUserByToken(String token) {
        return userDao.selectUserByToken(token);
    }
}
