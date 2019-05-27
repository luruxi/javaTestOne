package enn.testone.service;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class AsyncService {

    @Async //增加这个注解告诉spring这是一个异步方法，spring会开一个线程池来处理这个
    public void hello(){
        try {
            Thread.sleep(3000);//增加阻塞线程
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("处理数据中。。。。。。");
    }
}
