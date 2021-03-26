package org.shiqiu.jedis.util;

import org.apache.commons.lang3.StringUtils;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;


public class RedisFactory {
    private static JedisPool jedisPool;

    static
    {
        /**
         * redis连接地址
         */
        String redisHost = "localhost";
        /**
         * redis端口
         */
        int redisPort = 6379;
        /**
         * 密码
         */
        String redisPass = "" ;
        /**
         * 最大等待连接中的数量
         */
        int redisMaxIdle = 300;
        /**
         * 最大连接数量
         */
        int redisMaxTotal = 600;
        /**
         * 最大等待时间
         */
        int redisMaxWaitMillis =1000;


        /**
         * 配置jedis连接池
         */
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxTotal(redisMaxTotal);
        jedisPoolConfig.setMaxIdle(redisMaxIdle);
        jedisPoolConfig.setMaxWaitMillis(redisMaxWaitMillis);
        jedisPoolConfig.setTestOnBorrow(true);
        if (StringUtils.isNotEmpty(redisPass))
        {
            jedisPool = new JedisPool(jedisPoolConfig, redisHost, redisPort, 10000, redisPass);
        }
        else
        {
            jedisPool = new JedisPool(jedisPoolConfig, redisHost, redisPort, 10000);
        }
    }

    public static Jedis getJedis()
    {
        return jedisPool.getResource();
    }

    private RedisFactory()
    {
    }
}
