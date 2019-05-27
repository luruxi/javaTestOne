package enn.testone.controller;

import com.alibaba.fastjson.JSON;
import enn.testone.TestoneApplication;
import enn.testone.entity.Article;
import enn.testone.entity.Book;
import enn.testone.entity.Employee;
import enn.testone.repository.ArticleRepository;
import io.searchbox.client.JestClient;
import io.searchbox.core.Index;
import io.searchbox.core.Search;
import io.searchbox.core.SearchResult;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.test.context.junit4.SpringRunner;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

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

    //jest操作Elasticsearch的JestClient
    @Autowired
    JestClient jestClient;
    @Test
    //通过jestClient给ES搜索服务器添加一个文档
    public void jestClientToEsPut(){
        //1、给ES中索引（保存）一个文档
        Article article = new Article();
        article.setId(1);
        article.setName("吴承恩");
        article.setTitle("西游记");
        article.setContent("hello world！");

        //构建一个Index索引功能                          数据库 - 索引    数据表 - 类型
        Index index = new Index.Builder(article).index("myestest").type("article").build();
        try {
            //执行
            jestClient.execute(index);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void jestClientToEsSearch(){//通过jestClient搜索ES里的文档
        String json = "{\n" +
                "    \"query\" : {\n" +
                "        \"match\" : {\n" +
                "            \"content\" : \"hello\"\n" +
                "        }\n" +
                "    }\n" +
                "}\n";
        //构建搜索功能                      查询条条件       在那个索引下搜索                  在那个类型下搜
        Search search = new Search.Builder(json).addIndex("myestest").addType("article").build();

        try {
            SearchResult result = jestClient.execute(search);
            System.out.println(result.getJsonString());
            System.out.println(result.getHits(Article.class));
            List<SearchResult.Hit<Article, Void>> hits = result.getHits(Article.class);
            System.out.println(JSON.toJSONString(hits));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //springData - Elasticsearch操作Elasticsearch
    /*@Autowired
    ArticleRepository articleRepository;
    @Test
    public void spDERepIndex(){
        Book book = new Book();
        book.setId(2);
        book.setName("曹雪芹");
        book.setTitle("红楼梦");
        book.setContent("贾宝玉和林黛玉");
        articleRepository.index(book);

        List<Book> books = articleRepository.findBookByBookNameLike("梦");
    }*/

   /* @Autowired
    ElasticsearchTemplate elasticsearchTemplate;*/

    /*@Test
    public void spDETempIndex(){
    }*/


    //邮件发送测试
    @Autowired  //注入JavaMailSenderImpl邮件发送模板
    JavaMailSenderImpl mailSender;
    @Test
    //发送一个简单邮件
    public void SimpleMailSendTest(){
        //新建一个简单的邮件消息
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        //设置邮件消息内容
        mailMessage.setSubject("java send mail test");//设置邮件标题
        mailMessage.setText("my first java send mail test");//设置邮件内容
        mailMessage.setTo("1016254365@qq.com");//设置邮件发送对象
        mailMessage.setFrom("3335282009@qq.com");//设置邮件发送来源
        try{
            mailSender.send(mailMessage);//发送邮件消息
        }catch (Exception e){
            e.printStackTrace();
        }

    }
    @Test
    //发送一个复杂邮件
    public void mimeMailSendTest() throws MessagingException {
        //新建一个复杂的邮件消息
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true);//消息对象  是否上传文件
        messageHelper.setSubject("java send mail test");//设置邮件标题
        messageHelper.setText("<p style='color:red;'>my first java send mail test</p>");//设置邮件内容,可以是html片段
        messageHelper.setTo("1016254365@qq.com");//设置邮件发送对象
        messageHelper.setFrom("3335282009@qq.com");//设置邮件发送来源
        //上传文件
        messageHelper.addAttachment("1.png",new File("D:\\SenseSpace\\selfAnalysis\\static\\img\\daosan.png"));//文件名 文件流
        messageHelper.addAttachment("2.png",new File("D:\\SenseSpace\\selfAnalysis\\static\\img\\liukuai.png"));//文件名 文件流
        //发送邮件消息
        mailSender.send(mimeMessage);
    }
}
