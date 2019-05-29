package enn.testone.utils;

/*import com.enn.selfservice.dao.POrgMapper;
import com.enn.selfservice.dao.PSenseDataConnotationMapper;
import com.enn.selfservice.dao.SenseAppTaskMapper;
import com.enn.selfservice.dao.SysScheduledTaskMapper;
import com.enn.selfservice.entity.SenseSuperUser;
import com.enn.selfservice.entity.senseparam.*;
import com.enn.selfservice.service.SenseSuperUserService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.net.ssl.*;
import java.io.*;
import java.net.URI;
import java.net.URL;
import java.security.KeyStore;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

//@Component
public class SenseHelper {

    @Value("${sense.xrfkey}")
    private String xrfkey;
    @Value("${sense.host}")
    private String host;
    @Value("${sense.vproxy}")
    private String vproxy;
    @Value("${sense.certFolder}")
    private String certFolder;
    @Value("${sense.proxyCert}")
    private String proxyCert;
    @Value("${sense.proxyCertPass}")
    private String proxyCertPass;
    @Value("${sense.rootCert}")
    private String rootCert;
    @Value("${sense.rootCertPass}")
    private String rootCertPass;
    @Value("${custom.property.id}")
    private String customPropertyId;
    @Value("${data.conn.folder.type}")
    private String dataConnFolderType;
    @Value("${sense.superUser}")
    private String superUser;

    private static SenseSuperUser superUsers = new SenseSuperUser() ;

    @Autowired
    private SysScheduledTaskMapper sysScheduledTaskMapper;
    @Autowired
    private PSenseDataConnotationMapper psenseDataConnotationMapper;
    @Autowired
    private POrgMapper porgMapper;
    @Autowired
    private SenseAppTaskMapper senseAppTaskMapper;
    @Autowired
    private SenseSuperUserService senseSuperUserService;


   static Lock lock= new ReentrantLock();

        *//**
     * @Title: post获取api信息
     * @throws Exception
     *//*
    public JSONObject senseApi(Map<String,String> param )throws Exception {
        String xrfkey = "7rBHABt65vFflaZ7";
        String host=this.host;
        String vproxy = "";
        String proxyCert = certFolder+"client.jks";
        String rootCert = certFolder+"root.jks";
        KeyStore ks = KeyStore.getInstance("JKS");
        ks.load(new FileInputStream(new File(proxyCert)), proxyCertPass.toCharArray());
        KeyManagerFactory kmf = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
        kmf.init(ks, proxyCertPass.toCharArray());
        SSLContext context = SSLContext.getInstance("SSL");
        KeyStore ksTrust = KeyStore.getInstance("JKS");
        ksTrust.load(new FileInputStream(rootCert), rootCertPass.toCharArray());
        TrustManagerFactory tmf = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
        tmf.init(ksTrust);
        context.init(kmf.getKeyManagers(), tmf.getTrustManagers(), null);
        SSLSocketFactory sslSocketFactory = context.getSocketFactory();
        if (vproxy.length() > 0) {
            vproxy = "/" + vproxy;
        }
        URL url=null;
        url = new URL("https://" + host + ":4242/qrs"+param.get("url"));
        System.out.println("-------------senseurl-----------"+url);
        //加上，不加报错
        HostnameVerifier hVerifier = new HostnameVerifier() {
            @Override
            public boolean verify(String hostname, SSLSession session) {
                return true;
            }
        };
        HttpsURLConnection.setDefaultHostnameVerifier(hVerifier);
        HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
        connection.setSSLSocketFactory(sslSocketFactory);
        connection.setRequestProperty("x-qlik-xrfkey", xrfkey);
        connection.setRequestProperty("X-Qlik-User", "UserDirectory=XINAO;UserId="+this.superUser);
        connection.setDoOutput(true);
        connection.setDoInput(true);
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setRequestProperty("Accept", "application/json");
        connection.setRequestMethod(param.get("method"));
        connection.connect();
        String body ="";
        if(param.get("body") != null){
            body = param.get("body");
        }else{
            body = "";
        }
        System.out.println("method"+param.get("method"));
        if(!"GET".equals(param.get("method"))){
            OutputStreamWriter wr = new OutputStreamWriter(connection.getOutputStream());
            wr.write(body);
            wr.flush();
        }
        int responseCode  = connection.getResponseCode();
        InputStream inputStream = null;
        System.out.println("**"+responseCode);
        if(responseCode==Integer.parseInt(param.get("code"))){
            inputStream = new BufferedInputStream(connection.getInputStream());
        }else{
            inputStream = new BufferedInputStream(connection.getErrorStream());
        }
        System.out.println("inputStream"+inputStream.toString());
        InputStreamReader reader = new InputStreamReader(inputStream);
        BufferedReader in = new BufferedReader(reader);
        StringBuilder builder = new StringBuilder();
        String inputLine;
        while ((inputLine = in.readLine()) != null) {
            builder.append(inputLine);
        }
        in.close();
        String data = builder.toString();
        System.out.println( " builder.toString();"+builder.toString());
        JSONObject jsonObject = null;
        if(StringUtils.isNotBlank(data)){
            jsonObject = JSONObject.fromObject(data);
            System.out.println("-------"+jsonObject.toString());
        }
        connection.disconnect();
        return jsonObject;
    }
    *//**
     * @Title: post获取api信息
     * @throws Exception
     *//*
    public JSONArray senseApiArray(Map<String,String> param )throws Exception {
		*//*System.out.print("xrfkey"+xrfkey+"host"+host+"vproxy"+vproxy+"certFolder"+certFolder+"proxyCert"+proxyCert+"proxyCertPass"+proxyCertPass+"rootCert"+rootCert+"rootCertPass"+rootCertPass);
		proxyCert = certFolder+proxyCert;
		rootCert = certFolder+rootCert;*//*
         String xrfkey = "7rBHABt65vFflaZ7";
        String host=this.host;
        String vproxy = "";
        //String certFolder ="E:\\\\java\\\\document\\\\sence\\\\LXAJTX5698.addom.xinaogroup.com\\\\";
       //String certFolder ="/data/senseSertificate/";
        String proxyCert = certFolder+"client.jks";
        //String proxyCertPass = "529708";
       //String proxyCertPass = "123456";
        String rootCert = certFolder+"root.jks";
        //String rootCertPass ="529708";
        //String rootCertPass ="123456";
        KeyStore ks = KeyStore.getInstance("JKS");
        ks.load(new FileInputStream(new File(proxyCert)), proxyCertPass.toCharArray());
        KeyManagerFactory kmf = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
        kmf.init(ks, proxyCertPass.toCharArray());
        SSLContext context = SSLContext.getInstance("SSL");
        KeyStore ksTrust = KeyStore.getInstance("JKS");
        ksTrust.load(new FileInputStream(rootCert), rootCertPass.toCharArray());
        TrustManagerFactory tmf = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
        tmf.init(ksTrust);
        context.init(kmf.getKeyManagers(), tmf.getTrustManagers(), null);
        SSLSocketFactory sslSocketFactory = context.getSocketFactory();
        if (vproxy.length() > 0) {
            vproxy = "/" + vproxy;
        }
        URL url=null;
        url = new URL("https://" + host + ":4242/qrs"+param.get("url"));
        System.out.println("-------------senseurl-----------"+url);
        //加上，不加报错
        HostnameVerifier hVerifier = new HostnameVerifier() {
            @Override
            public boolean verify(String hostname, SSLSession session) {
                return true;
            }
        };
        HttpsURLConnection.setDefaultHostnameVerifier(hVerifier);
        HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
        connection.setSSLSocketFactory(sslSocketFactory);
        connection.setRequestProperty("x-qlik-xrfkey", xrfkey);
        connection.setRequestProperty("X-Qlik-User", "UserDirectory=XINAO;UserId="+this.superUser);
        connection.setDoOutput(true);
        connection.setDoInput(true);
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setRequestProperty("Accept", "application/json");
        connection.setRequestMethod(param.get("method"));
        connection.connect();
        String body ="";
        if(param.get("body") != null){
            body = param.get("body");
        }else{
            body = "";
        }
        System.out.println("method"+param.get("method"));
        if(!"GET".equals(param.get("method"))){
            OutputStreamWriter wr = new OutputStreamWriter(connection.getOutputStream());
            System.out.println("---------body------------"+body);
            wr.write(body);
            wr.flush();
        }
        int responseCode  = connection.getResponseCode();
        String responseMessage = connection.getResponseMessage();
        System.out.println("---------------------------"+responseMessage);
        InputStream inputStream = null;
        System.out.println("**"+responseCode);
        if(responseCode==Integer.parseInt(param.get("code"))){
            inputStream = new BufferedInputStream(connection.getInputStream());
        }else{
            inputStream = new BufferedInputStream(connection.getErrorStream());
        }
        System.out.println("inputStream"+inputStream.toString());
        InputStreamReader reader = new InputStreamReader(inputStream);
        BufferedReader in = new BufferedReader(reader);
        StringBuilder builder = new StringBuilder();
        String inputLine;
        while ((inputLine = in.readLine()) != null) {
            builder.append(inputLine);
        }
        in.close();
        String data = builder.toString();
        connection.disconnect();
        System.out.println( " builder.toString();"+builder.toString());
        JSONArray array = JSONArray.fromObject(data);
        System.out.println("-------"+array.toString());
        return array;
    }


    *//**
     *修改模型脚本
     * @param type 类型（删除脚本 0    修改脚本 1）
     * @param code  编码
     * @param name  名称
     * @throws Exception
     *//*
    public  void webSocketClientApi( String type ,String code,String name,String appId ) throws Exception {
        String user = "";
        try {
//            System.out.println("+++++++++0+"+lock.toString());
//            lock.lock();
          *//*  System.out.println("----------------------------------");
            user  = senseSuperUserService.getSuperUser();*//*
            *//*Map userMap = new HashMap();
            userMap.put("status","1");
            userMap.put("superUser", user);
            senseSuperUserService.updateSuperUserStatus(userMap);*//*
//            System.out.println("--------------------user--------------"+user);
        }  finally {
//            lock.unlock();
        }



        if(StringUtils.isNotBlank(name) && StringUtils.isNotBlank(code)){
            name = code;
        }
        String users = "zhushengbo";
        HashMap<String, String> headers = new HashMap<>();
        if("1".equals(type)){
            users = "huangjing";
        }else if("0".equals(type)){
            users = "huangjing";
        }
        if(StringUtils.isBlank(user)){
            user = "huangjing";
        }
        headers.put("X-Qlik-User", "UserDirectory=XINAO;UserId="+superUser);
//        headers.put("X-Qlik-User", "UserDirectory=XINAO;UserId="+this.superUser);
        String proxyCert = certFolder+"client.jks";
        String rootCert = certFolder+"root.jks";
        KeyStore ks = KeyStore.getInstance("JKS");
        ks.load(new FileInputStream(new File(proxyCert)), proxyCertPass.toCharArray());
        KeyManagerFactory kmf = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
        kmf.init(ks, proxyCertPass.toCharArray());
        SSLContext context = SSLContext.getInstance("SSL");
        KeyStore ksTrust = KeyStore.getInstance("JKS");
        ksTrust.load(new FileInputStream(rootCert), rootCertPass.toCharArray());
        TrustManagerFactory tmf = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
        tmf.init(ksTrust);
        context.init(kmf.getKeyManagers(), tmf.getTrustManagers(), null);
        SSLSocketFactory sslSocketFactory = context.getSocketFactory();
        JSONObject param = new JSONObject();
        JSONArray app = new JSONArray();
        //param
        app.add(appId);
        param.put("method","OpenDoc");
        param.put("handle",-1);
        param.put("params",app);
        param.put("id",1);
        System.out.println("param"+param.toString());
        if("0".equals(type)){
            PushWebSocketClient chatclient = new PushWebSocketClient( new URI( "wss://"+this.host+":4747/app/"+appId ) ,headers,users);
            chatclient.setSocketFactory( sslSocketFactory );
            chatclient.connectBlocking();
            //传送信息
//            chatclient.send(param.toString());
            chatclient.sendPushUnLoad(param.toString(),"0");
//            chatclient.close();
        }else if("1".equals(type)){
            SubscibeWebSocketClient chatclient = new SubscibeWebSocketClient( new URI( "wss://"+this.host+":4747/app/"+appId ) ,headers,users);
            chatclient.setSocketFactory( sslSocketFactory );
            chatclient.connectBlocking();
            Map<String, String> map = new HashMap<>();
            map.put("code",code);
            map.put("name",name);
            map.put("param",param.toString());
            //传送信息
            chatclient.subscibeSend(map);
        }else if("2".equals(type)){
            SubscibeWebSocketClient chatclient = new SubscibeWebSocketClient( new URI( "wss://"+this.host+":4747/app/"+appId ) ,headers,users);
            chatclient.setSocketFactory( sslSocketFactory );
            chatclient.connectBlocking();
            Map<String, String> map = new HashMap<>();
            map.put("param",param.toString());
            //传送信息
            chatclient.subscibeSend(map);
        }else if("3".equals(type)){//发布公开模型
            PublishPublicSocketClient chatclient = new PublishPublicSocketClient( new URI( "wss://"+this.host+":4747/app/"+appId ) ,headers,users);
            chatclient.setSocketFactory( sslSocketFactory );
            chatclient.connectBlocking();
            //传送信息
            chatclient.send(param.toString());
        }else if("4".equals(type)){//发布非公开模型
            PublishNonPublicSocketClient chatclient = new PublishNonPublicSocketClient( new URI( "wss://"+this.host+":4747/app/"+appId ) ,headers,users);
            chatclient.setSocketFactory( sslSocketFactory );
            chatclient.connectBlocking();
            //传送信息
            Map<String, String> map = new HashMap<>();
            map.put("code",code);
            map.put("name",name);
            map.put("param",param.toString());
            chatclient.subscibeSend(map);
        }


        System.out.println("***********");
        BufferedReader reader = new BufferedReader( new InputStreamReader( System.in ) );
        System.out.println("---------"+reader.toString());
    }


}*/
