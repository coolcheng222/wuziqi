package com.sealll.config;

import org.apache.catalina.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

import javax.websocket.server.ServerEndpointConfig;

/**
 * @author sealll
 * @time 2021/6/22 15:57
 */
@Configuration
public class SpringConfiguration {
    @Bean
    public ServerEndpointExporter serverEndpointConfig(){
        return new ServerEndpointExporter();
    }
}
