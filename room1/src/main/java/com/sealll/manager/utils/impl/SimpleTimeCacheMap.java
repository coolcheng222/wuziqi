package com.sealll.manager.utils.impl;

import com.sealll.bean.ExpireEntity;
import com.sealll.manager.utils.TimeCacheMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Iterator;

/**
 * @author sealll
 * @time 2021/7/13 15:28
 */
@Component
public class SimpleTimeCacheMap<K,T extends ExpireEntity> extends TimeCacheMap<K,T> {

    @Override
    public void storeWithTTL(K key, T entity, Long ttl) {
        entity.setTimestamp(ttl + System.currentTimeMillis());
        this.put(key,entity);
    }

    @Override
    public void removeExpire() {
        Iterator<Entry<K, T>> iterator = this.entrySet().iterator();
        Entry<K,T> ktEntry = null;
        while(iterator.hasNext()){
            ktEntry = iterator.next();
            Long timestamp = ktEntry.getValue().getTimestamp();
            if(timestamp <= System.currentTimeMillis()){
                iterator.remove();
            }
        }

    }
}
