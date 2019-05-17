package enn.testone.controller;

import com.alibaba.fastjson.JSON;
import enn.testone.TestoneApplication;
import enn.testone.entity.Employee;
import javafx.application.Application;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitMessagingTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.Cache;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {TestoneApplication.class})
public class TestDate {
    /*注入redis操作模板*/
    @Autowired
    RedisTemplate redisTemplate; //<k,v>都是對象的
    @Autowired
    StringRedisTemplate stringRedisTemplate; //<k,v>都是字符串的操作
//    @Autowired
//    RedisTemplate<Object,Employee> empRedisTemplate;

    @Autowired
    RedisCacheManager redisCacheManager;

    @Test//redis 的json实体类序列化缓存
    public void redisEmpSerializerTest(){
        Employee employee = new Employee();
        employee.setAge(1);
        employee.setName("两三天");
        employee.setSex("男");
        employee.setBumen("大数据");
        redisTemplate.opsForValue().set("emp2",employee);

        /*Cache emp2 = redisCacheManager.getCache("emp2");
        emp2.put("emp2-1",employee);
        System.out.println(emp2.toString()+"__"+emp2.getName());
        System.out.println(JSON.toJSONString(emp2));*/

//        Employee emp2 = empRedisTemplate.opsForValue().get("emp2");
//        System.out.println(emp2.toString()+"__"+emp2.getName());
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

    /*注入rabbitmq操作模板*/
    @Autowired
    RabbitTemplate rabbitTemplate;

    /*rabbitMQ发送测试*/
    @Test
    public void rabbitMQTestSend(){
        HashMap<String, Object> map = new HashMap<>();
        map.put("name","lsy");
        map.put("age",12);
        map.put("sex","男");
        //exchange 交换器
        //routingKey 路由键
        //map 消息内容
//        rabbitTemplate.convertAndSend("exchange.direct","queues.news",map);
        rabbitTemplate.convertAndSend("exchange.direct","queues.news",new Employee(32,"lsy","男","dashuju"));
    }
    /*rabbitMQ接收测试*/
    @Test
    public void rabbitMQTestReceive(){
        //queueName 消息队列名称
        Object o = rabbitTemplate.receiveAndConvert("queues.news");
        System.out.println("++++++++++++");
        System.out.println(o.getClass());
        System.out.println(o);
    }
    /*amqpAdmin使用*/
    @Autowired
    AmqpAdmin amqpAdmin;
    @Test
    public void createAmqpAdmin(){
        //创建交换器exchange  新建交换器 参数：名称
//        amqpAdmin.declareExchange(new DirectExchange("exchange.admin"));
        //创建消息队列queue  新建消息队列 参数：名称 持久化
//        amqpAdmin.declareQueue(new Queue("queues.admin",true));
        //创建绑定规则binding  新建绑定规则 参数 目的地消息队列 绑定类型 交换器名称 路由键 其他参数
        //绑定类型（Binding.DestinationType.QUEUE或Binding.DestinationType.EXCHANGE）
//        amqpAdmin.declareBinding(new Binding("queues.admin",Binding.DestinationType.QUEUE,"exchange.admin","queues.admin",null));
        //删除交换器exchange
//        amqpAdmin.deleteExchange("exchange.admin");
    }

    //延时定时器
    @Test
    public void threadTest(){
        new Thread(new Runnable() {
            public void run() {
                while (true) {
                    test();
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            }
            private void test() {
                System.out.println("test");

                // TODO Auto-generated method stub
            }
            public Runnable start() {
                System.out.println("start");
                run();
                // TODO Auto-generated method stub
                return null;
            }
        }.start());
    }
}
