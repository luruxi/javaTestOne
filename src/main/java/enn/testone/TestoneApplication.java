package enn.testone;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableScheduling;
import tk.mybatis.spring.annotation.MapperScan;

@Slf4j
@SpringBootApplication
@EnableRabbit  //开启基于注解的rabbit
@EnableScheduling  //开启定时任务
@EnableCaching  //redis缓存注解-开启缓存
@MapperScan("enn.testone.dao")
public class TestoneApplication {

    public static void main(String[] args) {
        SpringApplication.run(TestoneApplication.class, args);
    }

}
