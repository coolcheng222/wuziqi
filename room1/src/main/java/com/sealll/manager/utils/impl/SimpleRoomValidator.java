package com.sealll.manager.utils.impl;

import com.sealll.manager.constants.RoomConstants;
import com.sealll.manager.utils.RoomValidator;
import org.springframework.stereotype.Component;

/**
 * @author sealll
 * @time 2021/7/9 22:49
 */
@Component
public class SimpleRoomValidator implements RoomValidator {
    @Override
    public String ridValidate(Integer rid) {
        return null;
    }

    @Override
    public String passValidate(String password) {
        if(!password.matches("[A-Za-z0-9]{3,10}")){
            return RoomConstants.PASSFAULT;
        }else{
            return null;
        }
    }
}
