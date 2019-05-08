package enn.testone;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)

@SpringBootTest
public class TestoneApplicationTests {
    @Autowired
    RedisTemplate redisTemplate; //<k,v>都是對象的
    @Autowired
    StringRedisTemplate stringRedisTemplate; //<k,v>都是字符串的操作

    @Test
    public void contextLoads() {
    }

    @Test
    public void redisTest(){
        stringRedisTemplate.opsForValue().append("testOne","qwert");
    }
}
