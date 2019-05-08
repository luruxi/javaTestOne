package enn.testone.controller;

import enn.testone.TestoneApplication;
import enn.testone.entity.Employee;
import javafx.application.Application;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.SimpleDateFormat;
import java.util.Date;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {TestoneApplication.class})
public class TestDate {
    @Autowired
    RedisTemplate redisTemplate; //<k,v>都是對象的
    @Autowired
    StringRedisTemplate stringRedisTemplate; //<k,v>都是字符串的操作
    @Autowired
    RedisTemplate<Object,Employee> empRedisTemplate;

    @Test//redis 的json实体类序列化缓存
    public void redisEmpSerializerTest(){
        Employee employee = new Employee();
        employee.setAge(1);
        employee.setName("两三天");
        employee.setSex("男");
        employee.setBumen("大数据");
        empRedisTemplate.opsForValue().set("emp2",employee);
    }
    @Test
    public void getDate(){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
        String formatStr =formatter.format(new Date());
        log.info(formatStr);
    }
    @Test
    public void redisTest(){
//        stringRedisTemplate.opsForValue().append("testTwo","你好！");
//        stringRedisTemplate.opsForValue().set("testThree","Hello! World!");
        String testTwo = stringRedisTemplate.opsForValue().get("testTwo");
        log.info("testTwo:"+testTwo);
    }
    @Test
    public void redisTest2(){
        stringRedisTemplate.opsForList().leftPush("test3","1");
        stringRedisTemplate.opsForList().leftPush("test3","111");
    }

}
