package vip.poryi.base.autoconfigure;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "server")
public class HttpServerProperties {

    /**
     * 默认端口
     */
    private static final Integer DEFAULT_PORT = 8080;
    /**
     * 端口
     */
    private Integer port = DEFAULT_PORT;

    public static Integer getDefaultPort() {
        return DEFAULT_PORT;
    }

    public Integer getPort() {
        return port;
    }

    public HttpServerProperties setPort(Integer port) {
        this.port = port;
        return this;
    }
}
