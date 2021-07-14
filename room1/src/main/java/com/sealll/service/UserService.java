package com.sealll.service;

import com.sealll.bean.User;

/**
 * @author sealll
 * @time 2021/7/6 20:34
 */
public interface UserService {
    public boolean createUser(User user);

    public boolean removeUserSelective(User user);

    public User selectUserByToken(String token);
}
