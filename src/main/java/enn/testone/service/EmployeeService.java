package enn.testone.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import enn.testone.dao.EmployeeMapper;
import enn.testone.entity.Employee;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {
    @Autowired
    EmployeeMapper employeeMapper;

    /*
        缓存是将查询结果存到缓存中，下次查询时候从缓存中拿，不用sql查询
        @Cacheable 开启缓存注解
        cacheName value 指定缓存的组件名  缓存大集合  cacheNames = {"emp","employee"}
        key 缓存组件中的键值，默认使用参数 缓存大集合中一项的键值  keyGenerator 键的生成器和key二选一
    */
    //redis缓存注解-读取存储到缓存
    @Cacheable(cacheNames = "emp")
    public Employee getOneEmpById(Integer id){
        System.out.println(id);
        Employee emp = employeeMapper.getOneEmpById(id);
        System.out.println(emp.getName()+"_"+emp.getBumen()+"_"+emp.getSex()+"_"+emp.getAge());
        System.out.println(JSONObject.toJSONString(emp));
        return emp;
    }


    /*
    * rabbitmq相关操作，监听消息队列变化
    */
    //rabbitmq监听接收消息
    @RabbitListener(queues = "queues.news")
    public void rabbitMqReceive(Employee employee){
        System.out.println("+++++++++++++++++++");
        System.out.println("收到信息："+ JSON.toJSONString(employee));
    }

    //rabbitmq监听接收消息获取信息头和信息内容
    @RabbitListener(queues = "queues.emps")
    public void rabbitMqReceiveHead(Message message){
        System.out.println("+++++++++++++++++++");
        System.out.println("收到信息-头："+ JSON.toJSONString(message.getMessageProperties()));
        System.out.println("收到信息-体："+ JSON.toJSONString(message.getBody()));
    }

    //rabbitmq发送消息
    @Autowired
    RabbitTemplate rabbitTemplate;
    public void rabbitMqSend(Employee employee){
        rabbitTemplate.convertAndSend("exchange.direct","queues.news",employee);
    }
}
