package vip.poryi.base.autoconfigure;

import com.sun.net.httpserver.HttpServer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.net.InetSocketAddress;

@Configuration
@EnableConfigurationProperties(HttpServerAutoConfiguration.class)
@Slf4j
public class HttpServerAutoConfiguration {

    @Bean
    @ConditionalOnClass(HttpServer.class)
    public HttpServer httpServer(HttpServerProperties httpServerProperties) throws IOException {
        // 创建 HttpServer 对象，并启动
        HttpServer httpServer = HttpServer.create(new InetSocketAddress(httpServerProperties.getPort()), 0);
        httpServer.start();
        log.info("[httpServer] [启动服务器成功，端口为:{}]", httpServerProperties.getPort());
        return httpServer;
    }
}
