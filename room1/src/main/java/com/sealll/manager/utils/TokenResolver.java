package com.sealll.manager.utils;

import com.sealll.bean.User;

/**
 * @author sealll
 * @time 2021/7/9 22:41
 */
public interface TokenResolver {
    public String getToken(Integer rid,Integer uid);
    public User getByToken(String token);
}
