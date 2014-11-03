package com.ailife.uip.redis.config;

import com.ailife.uip.core.util.LogUtil;
import org.springframework.boot.context.properties.ConfigurationProperties;

import javax.annotation.PostConstruct;

/**
 * Created by chenmm on 9/29/2014.
 */
@ConfigurationProperties(prefix = RedisAutoConfiguration.CONFIGURATION_PREFIX)
public class RedisProperties {

    private String host;
    private int port;
    private String password;
    private int timeout;

    @PostConstruct
    public void log() {
        LogUtil.debug(this.getClass(), "Initial Redis Properties." + this.toString());
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getTimeout() {
        return timeout;
    }

    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("RedisProperties{");
        sb.append("host='").append(host).append('\'');
        sb.append(", port=").append(port);
        sb.append(", password='").append(password).append('\'');
        sb.append(", timeout=").append(timeout);
        sb.append('}');
        return sb.toString();
    }

}
