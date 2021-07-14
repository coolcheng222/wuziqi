package com.sealll.dao;

import com.sealll.bean.User;

/**
 * @author sealll
 * @time 2021/7/5 19:17
 */
public interface UserDao {
    public boolean createUser(User user);

    public boolean removeUserSelective(User user);

    public User selectUserByToken(String token);

}
