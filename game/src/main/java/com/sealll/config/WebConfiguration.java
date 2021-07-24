package com.sealll.config;

import com.sealll.security.RoomCheckinInterceptor;
import org.apache.catalina.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

import javax.websocket.server.ServerEndpointConfig;

/**
 * @author sealll
 * @time 2021/6/22 15:57
 */
@Configuration
public class WebConfiguration implements WebMvcConfigurer {
    @Bean
    public RoomCheckinInterceptor roomCheckinInterceptor(){
        return new RoomCheckinInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(roomCheckinInterceptor())
                .addPathPatterns("/game/*");
    }
}
