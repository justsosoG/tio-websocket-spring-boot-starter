package com.github.fanpan26;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.tio.utils.hutool.StrUtil;

/**
 * @author <a href="https://blog.qinghuan.fun/index.php/go/gu/">Mr_Gu</a>
 * @apiNote
 * @date 2024-10-18 13:38
 * @since
 **/
@ConfigurationProperties("tio.websocket.cluster.redis")
public class RedisConfig {

    private String ip = "127.0.0.1";

    private Integer port = 6379;

    private String password;

    private String clusterName = "tio_ws_spring_boot_starter";

    private Integer poolSize = 32;

    private Integer minimumIdleSize = 16;

    /*
     * setConnectionPoolSize(32).setConnectionMinimumIdleSize(16);
     * */

    public Integer getPoolSize() {
        return poolSize;
    }

    public void setPoolSize(Integer poolSize) {
        this.poolSize = poolSize;
    }

    public Integer getMinimumIdleSize() {
        return minimumIdleSize;
    }

    public void setMinimumIdleSize(Integer minimumIdleSize) {
        this.minimumIdleSize = minimumIdleSize;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public boolean hasPassword(){
        return StrUtil.isNotBlank(getPassword());
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getClusterName() {
        return clusterName;
    }

    public void setClusterName(String clusterName) {
        this.clusterName = clusterName;
    }

    @Override
    public String toString() {
        return "redis://" + getIp() + ":" + getPort();
    }
}
