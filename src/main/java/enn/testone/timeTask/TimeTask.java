package enn.testone.timeTask;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component //定时任务类加这个注解，保证spring的注入
@Service //注入到容器中
public class TimeTask {
    @Scheduled(cron = "0/59 1 * * * ?")//定时任务注解，设置定时执行规则 cron在线生成器
    public void taskTest(){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        String time = dateFormat.format(new Date());
        System.out.println("定时任务："+time);
    }
}
