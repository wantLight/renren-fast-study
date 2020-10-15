package io.renren.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

/**
 * 在 WebSocket API 中，浏览器和服务器只需要做一个握手的动作，
 * 然后，浏览器和服务器之间就形成了一条快速通道。两者之间就直接可以数据互相传送。HTML5 定
 * 义的 WebSocket 协议，能更好的节省服务器资源和带宽，并且能够更实时地进行通讯。
 */
@Configuration
public class WebSocketConfig {

    /**
     * ServerEndpointExporter 作用
     *
     * 这个Bean会自动注册使用@ServerEndpoint注解声明的websocket endpoint
     *
     * @return
     */
    @Bean
    public ServerEndpointExporter serverEndpointExporter() {
        return new ServerEndpointExporter();
    }

}
