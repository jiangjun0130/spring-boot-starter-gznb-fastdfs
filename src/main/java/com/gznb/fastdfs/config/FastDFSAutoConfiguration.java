package com.gznb.fastdfs.config;

import com.gznb.fastdfs.client.FastDFSClient;
import org.csource.common.MyException;
import org.csource.fastdfs.*;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import java.io.IOException;

/**
 * Fast DFS自动装配
 *
 * @author jiangjun
 * @create 2017/9/30
 */
@Configuration
@ConditionalOnClass({TrackerServer.class, TrackerClient.class, StorageClient1.class})
@EnableConfigurationProperties(FastDFSProperties.class)
public class FastDFSAutoConfiguration {

    @Bean
    public StorageClient1 storageClient(FastDFSProperties properties){
        if(properties == null){
            throw new RuntimeException("init fast dfs client failed! fast dfs config is null!");
        }
        Resource resource = new ClassPathResource(properties.getConfigPath());
        String path = "";
        try {
            path = resource.getFile().getPath();
            ClientGlobal.init(path);
        } catch (Exception e) {
            throw new RuntimeException("init fast dfs client failed!",e);
        }
        // 连接tracker的客户端
        TrackerClient trackerClient = new TrackerClient();
        // 连接tracker
        TrackerServer trackerServer = null;
        try {
            trackerServer = trackerClient.getConnection();
            ProtoCommon.activeTest(trackerServer.getSocket());
        } catch (IOException e) {
            throw new RuntimeException("get fast dfs client connection failed!",e);
        }
        // 连接storage
        StorageClient1 storageClient = new StorageClient1(trackerServer,null);
        return storageClient;
    }

    @Bean
    public FastDFSClient fastDFSClient(FastDFSProperties properties){
        StorageClient1 storageClient = storageClient(properties);
        return new FastDFSClient(storageClient);
    }

}
