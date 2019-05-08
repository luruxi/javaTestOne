package enn.testone;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@Slf4j
@SpringBootApplication
@EnableScheduling  //开启定时任务
public class TestoneApplication {

    public static void main(String[] args) {
        SpringApplication.run(TestoneApplication.class, args);
    }

}
