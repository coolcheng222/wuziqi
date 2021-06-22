package com.sealll.websocket.decoder;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sealll.bean.Msg;

import javax.websocket.DecodeException;
import javax.websocket.Decoder;
import javax.websocket.EndpointConfig;

/**
 * @author sealll
 * @time 2021/6/22 15:54
 */
public class MsgTextDecoder implements Decoder.Text<Msg>{
    ObjectMapper objectMapper = new ObjectMapper();
    @Override
    public Msg decode(String s) throws DecodeException {
        try {
            Msg msg = objectMapper.readValue(s, Msg.class);
            return msg;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean willDecode(String s) {
        return false;
    }

    @Override
    public void init(EndpointConfig endpointConfig) {

    }

    @Override
    public void destroy() {

    }
}
