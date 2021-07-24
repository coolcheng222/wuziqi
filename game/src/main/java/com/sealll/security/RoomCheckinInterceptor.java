package com.sealll.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sealll.bean.Msg;
import com.sealll.rpc.RoomRemoteService;
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
    public static final String SESSION_ATTR = "session_token";
    public static final String HEADER_ATTR = "token";

    @Autowired
    private RoomRemoteService roomRemoteService;
    @Autowired
    private ObjectMapper objectMapper;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        Object attribute = session.getAttribute(SESSION_ATTR);
        String header = request.getHeader(HEADER_ATTR);
        boolean res;

        if(attribute == null && header == null){
            res = false;
        }else if(attribute == null){
            Msg check = roomRemoteService.check(header);
            if(check.getErrno() == 0){
                session.setAttribute(SESSION_ATTR,header);
                res = true;
            }else{

                res = false;
            }
        }else{
            Msg check = roomRemoteService.check((String) attribute);
            res = check.getErrno() == 0;
        }

        if(res){
            return true;
        }else{
            response.getWriter().write(objectMapper.writeValueAsString(
                    Msg.fail("not in a room")
            ));
            return false;
        }
    }


}
