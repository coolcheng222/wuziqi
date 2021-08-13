package com.sealll.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sealll.bean.Msg;
import com.sealll.bean.User;
import com.sealll.rpc.RoomRemoteService;
import org.graalvm.compiler.lir.LIRInstruction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author sealll
 * @time 2021/7/23 15:36
 */
public class RoomCheckinInterceptor implements HandlerInterceptor {
    public static final String SESSION_ATTR = "session_room_token";
    public static final String HEADER_ATTR = "token";
    public static final String REQUEST_ATTR = "user";

    @Autowired
    private RoomRemoteService roomRemoteService;
    @Autowired
    private ObjectMapper objectMapper;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        Object sessAttr = session.getAttribute(SESSION_ATTR);
        String headerAttr = request.getHeader(HEADER_ATTR);
        boolean res;
        Msg check = null;
        if(sessAttr == null && headerAttr == null){
            res = false;
        }else if(headerAttr != null){
            check = roomRemoteService.check(headerAttr);
            if(check.getErrno() == 0){
                session.setAttribute(SESSION_ATTR,headerAttr);
                res = true;
            }else{

                res = false;
            }
        }else{
            check = roomRemoteService.check((String) sessAttr);
            res = check.getErrno() == 0;
        }

        if(res){
            String s = objectMapper.writeValueAsString(check.getExtend());
            request.setAttribute(REQUEST_ATTR,objectMapper.readValue(s, User.class));
            return true;
        }else{
            response.getWriter().write(objectMapper.writeValueAsString(
                    Msg.fail("not in a room")
            ));
            return false;
        }
    }


}
