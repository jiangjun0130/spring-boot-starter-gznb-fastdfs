package com.gznb.fastdfs.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Fast DFS配置
 *
 * @author jiangjun
 * @create 2017/9/30
 */
@ConfigurationProperties(prefix = "gznb.fastdfs")
public class FastDFSProperties {

    /**
     * fast dfs 服务器地址
     */
    private String host;

    /**
     * 配置文件路径
     */
    private String configPath;

    @Override
    public String toString() {
        return "FastDFSProperties{" +
                "host='" + host + '\'' +
                ", configPath='" + configPath + '\'' +
                '}';
    }

    public String getConfigPath() {
        return configPath;
    }

    public void setConfigPath(String configPath) {
        this.configPath = configPath;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }
}
