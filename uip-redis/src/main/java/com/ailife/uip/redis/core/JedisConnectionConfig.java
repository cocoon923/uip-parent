package com.ailife.uip.redis.core;

import com.ailife.uip.core.util.StringUtils;

public class JedisConnectionConfig {

    private String hostname;
    private int port;
    private String password;
    private int timeout = 2000;

    public JedisConnectionConfig() {
    }

    public JedisConnectionConfig(String hostname, int port) {
        super();
        this.hostname = hostname;
        this.port = port;
    }

    public JedisConnectionConfig(String hostname, int port, String password) {
        super();
        this.hostname = hostname;
        this.port = port;
        this.password = password;
    }

    public JedisConnectionConfig(String hostname, int port, String password, int timeout) {
        super();
        this.hostname = hostname;
        this.port = port;
        this.password = password;
        this.timeout = timeout;
    }

    public JedisConnectionConfig(String hostname, int port, int timeout) {
        super();
        this.hostname = hostname;
        this.port = port;
        this.timeout = timeout;
    }

    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }

    public String getHostname() {
        return hostname;
    }

    public int getPort() {
        return port;
    }

    public String getPassword() {
        return password;
    }

    public int getTimeout() {
        return timeout;
    }

    public boolean hasPassword() {
        return StringUtils.isNotNullorEmpty(password);
    }

}
