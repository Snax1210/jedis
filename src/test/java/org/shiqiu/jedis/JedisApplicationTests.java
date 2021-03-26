package org.shiqiu.jedis;

import org.junit.jupiter.api.Test;
import org.shiqiu.jedis.service.RedisService;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class JedisApplicationTests {

    @Resource
    private RedisService redisService;
    @Test
    void contextLoads() {
        redisService.setString("testKey","testValue");
    }

}
