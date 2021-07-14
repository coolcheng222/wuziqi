package com.sealll.manager.utils;

import com.sealll.bean.ExpireEntity;

import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author sealll
 * @time 2021/7/13 15:13
 */
public abstract class TimeCacheMap<K,T extends ExpireEntity> extends ConcurrentHashMap<K,T> {
    public abstract void storeWithTTL(K key,T entity,Long ttl);
    public abstract void removeExpire();
}
