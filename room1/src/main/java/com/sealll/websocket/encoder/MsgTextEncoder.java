package com.sealll.websocket.encoder;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sealll.bean.Msg;

import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;

/**
 * @author sealll
 * @time 2021/6/22 15:51
 */
public class MsgTextEncoder implements Encoder.Text<Msg>{
    ObjectMapper objectMapper = new ObjectMapper();
    @Override
    public String encode(Msg msg) throws EncodeException {
        try {
            return objectMapper.writeValueAsString(msg);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void init(EndpointConfig endpointConfig) {

    }

    @Override
    public void destroy() {

    }
}
