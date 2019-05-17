package enn.testone.controller;

import com.alibaba.fastjson.JSON;
import enn.testone.entity.Employee;
import enn.testone.entity.Responsed;
import enn.testone.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@RestController
@Slf4j
public class EmployeeController {
    @Autowired
    EmployeeService employeeService;

    @RequestMapping(value = "/getOneEmpById", method = {RequestMethod.POST,RequestMethod.GET})
    public Responsed<Employee> getOneEmpById(Integer id){
        Responsed<Employee> responsed = new Responsed<>();
        Employee employee = employeeService.getOneEmpById(id);
        System.out.println("============================");
        System.out.println(JSON.toJSONString(employee));
        Timer timer = new Timer();
        //线程添加
        ExecutorService pool = Executors.newCachedThreadPool();
        MyThread target = new MyThread(employee);
        pool.execute(target);
        pool.shutdown();
        /*timer.schedule(new TimerTask() {
            public void run() {
//                System.out.println("退出");
                employeeService.rabbitMqSend(employee);
                this.cancel();
            }
        }, 5000);// 这里百毫秒*/



        responsed.setData(employee);
        log.info(responsed.toString());
        return responsed;
    }


    class MyThread  implements Runnable {
        Employee employee;
        public MyThread(Employee employee) {
        this.employee = employee;
        }
        public void run() {
            try{
                Thread.sleep(10000);
                employeeService.rabbitMqSend(employee);
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }
}
