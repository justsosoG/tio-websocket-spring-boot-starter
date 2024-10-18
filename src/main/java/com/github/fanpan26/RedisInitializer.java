package com.github.fanpan26;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.codec.FstCodec;
import org.redisson.config.Config;
import org.redisson.config.SingleServerConfig;

public final class RedisInitializer {

    private RedissonClient redissonClient;
    private RedisConfig redisConfig;

    public RedisInitializer(RedisConfig redisConfig) {
        this.redisConfig = redisConfig;

        initRedis();
    }

    public RedissonClient getRedissonClient() {
        return redissonClient;
    }

    private void initRedis() {
        Config config = new Config();

        String address = redisConfig.toString();
        SingleServerConfig singleServerConfig = config.useSingleServer()
                .setAddress(address)
                .setConnectionPoolSize(redisConfig.getPoolSize())
                .setConnectionMinimumIdleSize(redisConfig.getMinimumIdleSize());

        if (redisConfig.hasPassword()) {
            singleServerConfig.setPassword(redisConfig.getPassword());
        }
        config.setCodec(new FstCodec());
        //默认情况下，看门狗的检查锁的超时时间是30秒钟
        config.setLockWatchdogTimeout(1000 * 30);
        redissonClient = Redisson.create(config);
    }
}
