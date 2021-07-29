package com.sealll.rpc;

import com.sealll.bean.Msg;
import com.sealll.bean.Room;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.net.URI;

/**
 * @author sealll
 * @time 2021/7/23 15:41
 */
@Service
@ConfigurationProperties(prefix = "room-rpc")
public class RoomRemoteService implements RoomRemote{
    @Autowired
    private WebClient.Builder webClientBuilder;

    private String host;
    private Long port;
    private String schema;
    private String context = "/";

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public Long getPort() {
        return port;
    }

    public void setPort(Long port) {
        this.port = port;
    }

    public String getSchema() {
        return schema;
    }

    public void setSchema(String schema) {
        this.schema = schema;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public String baseUrl(){
        return schema + "://" + host + ":" + port + context;
    }

    public Msg check(String token){
        WebClient build = webClientBuilder.build();
        Mono<Msg> msgMono = build
                .get()
                .uri(baseUrl() + "/user/check?token={token}", token)
                .retrieve()
                .bodyToMono(Msg.class);
        Msg block = msgMono.block();
        return block;
    }

    @Override
    public Msg info(Integer rid) {
        WebClient build = webClientBuilder.build();
        Msg block = build.get()
                .uri(baseUrl() + "/room/info?rid={rid}", rid)
                .retrieve()
                .bodyToMono(Msg.class)
                .block();
        return block;
    }

    public Msg other(Msg msg){
        WebClient build = webClientBuilder.build();
        Mono<Msg> msgMono = build
                .post()
                .uri(baseUrl() + "/ws/other")
                .bodyValue(msg)
                .retrieve()
                .bodyToMono(Msg.class);
        return msgMono.block();
    }

    public Msg create(Room room){
        WebClient build = webClientBuilder.build();
        Msg msg= build
                .post()
                .uri(baseUrl() + "/room/")
                .bodyValue(room)
                .retrieve()
                .bodyToMono(Msg.class)
                .block();
        return msg;
    }

    public Msg enRoom(Room room){
        WebClient build = webClientBuilder.build();
        Msg msg = build.put()
                .uri(baseUrl() + "/room")
                .bodyValue(room)
                .retrieve()
                .bodyToMono(Msg.class)
                .block();
        return msg;
    }

    public Msg checkRooms(){
        WebClient build = webClientBuilder.build();
        Msg block = build.get()
                .uri(baseUrl() + "/room/ids")
                .retrieve()
                .bodyToMono(Msg.class)
                .block();
        return block;
    }
}
