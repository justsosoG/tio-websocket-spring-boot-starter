package com.github.fanpan26;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.tio.cluster.redisson.RedissonTioClusterTopic;

/**
 * @author fanyuepan
 */
@Configuration
@Import(TioWebSocketServerInitializerConfiguration.class)
@ConditionalOnBean(TioWebSocketServerMarkerConfiguration.Marker.class)
@EnableConfigurationProperties({ TioWebSocketServerProperties.class, TioWebSocketServerClusterProperties.class, RedisConfig.class})
public class TioWebSocketServerAutoConfiguration {

    @Autowired
    private TioWebSocketServerProperties serverProperties;

    @Autowired
    private RedisConfig redisConfig;

    @Bean
    public TioWebSocketServerBootstrap tioWebSocketServerBootstrap(ApplicationContext applicationContext) {
        return new TioWebSocketServerBootstrap(serverProperties, applicationContext);
    }

    @Bean
    @ConditionalOnProperty(value = "tio.websocket.cluster.enable",havingValue = "true",matchIfMissing = true)
    public RedisInitializer redisInitializer(){
        return new RedisInitializer(redisConfig);
    }

    @Bean
    @ConditionalOnBean(RedisInitializer.class)
    public RedissonTioClusterTopic redissonTioClusterTopic(RedisInitializer redisInitializer) {
        return new RedissonTioClusterTopic(redisConfig.getClusterName(),redisInitializer.getRedissonClient());
    }

}
