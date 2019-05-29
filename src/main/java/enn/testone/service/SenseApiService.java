package enn.testone.service;

/*import com.enn.selfservice.dao.MUserModelOrderMapper;
import com.enn.selfservice.dao.PUserMapper;
import com.enn.selfservice.entity.PUser;
import com.enn.selfservice.entity.senseparam.DataConnCustomDefinition;
import com.enn.selfservice.entity.senseparam.stream.CustomProperties;
import com.enn.selfservice.senseApi.SenseHelper;
import com.enn.selfservice.senseApi.SenseResult;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


*//**
 * @author 朱胜波
 * @ProjectName 自助分析
 * @Description: TODO
 * @date 2019/3/49:57
 *//*
@Service
public class SenseApiService {

    @Autowired
    private SenseHelper senseHelper;
    @Autowired
    PUserMapper pUser;
    @Autowired
    MUserModelOrderMapper mUserModelOrderMapper;
    @Value("${sense.modelFid}")
    private String modelFid;
    @Value("${sense.auditStream}")
    private String auditStream;
    @Value("${sense.publishStream}")
    private String publishStream;
    @Value("${sense.roleswithaccessId}")
    private String roleswithaccessId;

    *//**
     *
     * @return
     * @throws Exception
     *//*
    public SenseResult getAboutApi
    () throws Exception {
        System.out.print("getAboutApi---------------------------");
        System.out.print("------------***");
        Map<String,String> map = new HashMap<String,String>();
        map.put("code","200");
        map.put("url","/about?xrfkey=7rBHABt65vFflaZ7");
        map.put("method","GET");
        JSONObject obj = senseHelper.senseApi(map);
        return new SenseResult(obj);
    }
    *//**
     * 创建与复制
     * @return
     * @throws Exception
     *//*
    public  JSONObject getCopeAndCreataApi
            (String appId,String appName) throws Exception {
        System.out.print("getCopeAndCreataApi---------------------------");
        System.out.print("------------***");
        Map<String,String> map = new HashMap<String,String>();
        map.put("code","201");
        if(StringUtils.isBlank(appId)){
            appId = this.modelFid;
        }
        appName = URLEncoder.encode(appName,"utf-8");
        map.put("url","/app/"+appId+"/copy?xrfkey=7rBHABt65vFflaZ7&name="+appName);
        map.put("method","POST");
        JSONObject obj = senseHelper.senseApi(map);
        return obj;
    }
    *//**
     * 修改模型名称
     * @return
     * @throws Exception
     *//*
    public  JSONObject updateModelName
            (String appId,String appName) throws Exception {
        System.out.print("updateModelName---------------------------");
        //获取模型信息
        JSONObject apiMes = getApiMes(appId);
        if(StringUtils.isNotBlank(appName) && !appName.equals((String) apiMes.get("name"))){
            apiMes.put("name",appName);
            Map<String,String> map = new HashMap<String,String>();
            map.put("code","200");
            appName = URLEncoder.encode(appName,"utf-8");
            map.put("url","/app/"+appId+"?xrfkey=7rBHABt65vFflaZ7");
            map.put("method","PUT");
            map.put("body",apiMes.toString());
            JSONObject obj = senseHelper.senseApi(map);
            return obj;
        }else{
            return null;
        }
    }
    *//**
     * 删除模型(驳回发布)
     * @return
     * @throws Exception
     *//*
    public  SenseResult deleteApi
            (String appId) throws Exception {
        System.out.print("getCopeAndCreataApi---------------------------");
        Map<String,String> map = new HashMap<String,String>();
        map.put("method","DELETE");
        map.put("appId",appId);
        map.put("code","204");
        map.put("url","/app/"+appId+"?xrfkey=7rBHABt65vFflaZ7");
        JSONObject obj = senseHelper.senseApi(map);
        return new SenseResult(obj);
    }

    *//**
     * 发布审核流
     * @param appId
     * @param appName
     * @param  （0审核流）（1发布流）(type判断是发布流还是审核流)
     * @return
     * @throws Exception
     *//*
    //stream审核流id：e96ab0a3-eff9-48a6-9a14-39e116f2b50f
    //stream发布流id：3cc0b9ff-cfb0-4db9-9e6e-95f97bc8944b
    public  String  publishAuditApi(String appId, String appName,int type) throws Exception {
        JSONObject createModel = getCopeAndCreataApi(appId, appName);
        System.out.print("getCopeAndCreataApi---------------------------");
        System.out.print("------------***");
        Map<String,String> map = new HashMap<String,String>();
        map.put("code","200");
        //stream审核流id：e96ab0a3-eff9-48a6-9a14-39e116f2b50f
        //stream发布流id：3cc0b9ff-cfb0-4db9-9e6e-95f97bc8944b
        String streamId="";
        if(type==0){
            streamId=this.auditStream;
//            streamId="e96ab0a3-eff9-48a6-9a14-39e116f2b50f";
        }else{
//            streamId="3cc0b9ff-cfb0-4db9-9e6e-95f97bc8944b";
            streamId=this.publishStream;
        }
        appName = URLEncoder.encode(appName,"utf-8");
        map.put("url","/app/"+createModel.get("id")+"/publish?xrfkey=7rBHABt65vFflaZ7&stream="+streamId+"&name="+appName);
        map.put("method","PUT");
        JSONObject obj = senseHelper.senseApi(map);
        return  (String )createModel.get("id");
    }
    *//**
     * 发布公开app（1.删除脚本 2.reload数据 3.发布到发布流）
     * @param appId
     * 查询模型信息替换流（审核流替换为发布流）
     *//*
    //stream审核流id：e96ab0a3-eff9-48a6-9a14-39e116f2b50f
    //stream发布流id：3cc0b9ff-cfb0-4db9-9e6e-95f97bc8944b
    public  SenseResult publishOpenApi(String appId) throws Exception {
        //1.删除脚本，保存脚本2.加载数据3.还原脚本
        senseHelper.webSocketClientApi("3","","",appId);
        //先get到模型信息
        JSONObject apiMes = getApiMes(appId);
        //替为发布换流
        String body = apiMes.toString().replace(this.auditStream,this.publishStream);
        Map<String,String> map = new HashMap<String,String>();
        map.put("code","200");
        map.put("url","/app/"+appId+"?xrfkey=7rBHABt65vFflaZ7");
        map.put("method","PUT");
        System.out.println("body"+body);
        map.put("body",body);
        JSONObject obj = senseHelper.senseApi(map);
        //reload数据
//        JSONObject reload = reload(appId);
        return new SenseResult("");
    }
    *//**
     *
     * 发布非公开app（1.修改脚本 2.reload数据 3.发布到新建的流）
     * @param appId
     *
     *//*
    //stream审核流id：e96ab0a3-eff9-48a6-9a14-39e116f2b50f
    //stream发布流id：3cc0b9ff-cfb0-4db9-9e6e-95f97bc8944b
    public  SenseResult publishNonPublicApi(String appId) throws Exception {
//        String roleswithaccessId = "3869ec96-c5ff-46ff-a573-cbbc157467ec";
        String roleswithaccessId = this.roleswithaccessId;
        //reload数据
//        JSONObject reload = reload(appId);
        //给roleswithaccess的custom properties加一个值
        JSONObject roleswithaccess = getRoleswithaccess(roleswithaccessId);
        List<String> choiceValues = new ArrayList<>();
        choiceValues.add(appId);
        roleswithaccess.put("choiceValues",choiceValues);
        //custom properties加一个值
        JSONObject jsonObject = setRoleswithaccess(roleswithaccess, roleswithaccessId);
        //创建新的流（post创建）
        JSONObject stream = createStreamPost(appId,appId);
        //先get到模型信息
        JSONObject apiMes = getApiMes(appId);
        //替为发布换流
        String body = apiMes.toString().replace(this.auditStream,(String)stream.get("id"));
        Map<String,String> map = new HashMap<String,String>();
        map.put("code","200");
        map.put("url","/app/"+appId+"?xrfkey=7rBHABt65vFflaZ7");
        map.put("method","PUT");
        System.out.println("body"+body);
        map.put("body",body);
        JSONObject obj = senseHelper.senseApi(map);
        //修改脚本
        senseHelper.webSocketClientApi("4","10000200","10000200",appId);
        return new SenseResult(obj);
    }
    *//**
     * setRoleswithaccess
     * @param roleswithaccess
     *//*
    public  JSONObject setRoleswithaccess(JSONObject roleswithaccess,String id) throws Exception {
        System.out.print("setRoleswithaccess---------------------------");
        JSONObject body = new JSONObject();
        Map<String,String> map = new HashMap<String,String>();
        map.put("code","200");
        map.put("url","/custompropertydefinition/"+id+"?xrfkey=7rBHABt65vFflaZ7");
        map.put("method","PUT");
        map.put("body",roleswithaccess.toString());
        System.out.println("********"+roleswithaccess.toString());
        JSONObject obj = senseHelper.senseApi(map);
        return obj;
    }
    *//**
     * 获取roleswithaccess信息
     * @param id
     *//*
    public  JSONObject getRoleswithaccess(String id) throws Exception {
        System.out.print("getRoleswithaccess---------------------------");
        JSONObject body = new JSONObject();
        Map<String,String> map = new HashMap<String,String>();
        map.put("code","200");
        map.put("url","/custompropertydefinition/"+id+"?xrfkey=7rBHABt65vFflaZ7");
        map.put("method","GET");
        System.out.println("********"+body.toString());
        JSONObject obj = senseHelper.senseApi(map);
        return obj;
    }
    *//**
     * 创建liu
     * @param stream
     * 查询模型信息替换流
     *//*
    public  JSONObject createStream(String stream) throws Exception {
        System.out.print("createStream---------------------------");
        JSONObject body = new JSONObject();
        Map<String,String> map = new HashMap<String,String>();
        body.put("name",stream);
        map.put("code","201");
        map.put("url","/stream?xrfkey=7rBHABt65vFflaZ7");
        map.put("method","POST");
        map.put("body",body.toString());
        System.out.println("********"+body.toString());
        JSONObject obj = senseHelper.senseApi(map);
        return obj;
    }
    *//**
     * post创建流
     * @param name
     *
     *//*
    public  JSONObject createStreamPost(String name,String appId) throws Exception {
        System.out.print("createStreamPost---------------------------");
        JSONObject body = new JSONObject();
        Map<String,String> map = new HashMap<String,String>();
        CustomProperties customProperties = new CustomProperties();
        DataConnCustomDefinition dataConnCustomDefinition = new DataConnCustomDefinition();
        dataConnCustomDefinition.setId(this.roleswithaccessId);
        customProperties.setDefinition(dataConnCustomDefinition);
        customProperties.setValue(appId);
        List<CustomProperties> list = new ArrayList<CustomProperties>();
        list.add(customProperties);
        body.put("name",name);
        body.put("customProperties",list);
        map.put("code","201");
        map.put("url","/stream?xrfkey=7rBHABt65vFflaZ7");
        map.put("method","POST");
        map.put("body",body.toString());
        System.out.println("********"+body.toString());
        JSONObject obj = senseHelper.senseApi(map);
        return obj;
    }
    *//**
     * 切换流
     * @param appId
     * @param stream
     * 查询模型信息替换流
     *//*
    public  JSONObject updateStream(String appId,String stream,String appName) throws Exception {
        System.out.print("updateStream---------------------------");
        Map<String,String> map = new HashMap<String,String>();
        map.put("code","200");
        appName = URLEncoder.encode(appName,"utf-8");
        map.put("url","/app/"+appId+"/publish?xrfkey=7rBHABt65vFflaZ7&stream="+stream+"&name="+appName);
        map.put("method","PUT");
        JSONObject obj = senseHelper.senseApi(map);
        return obj;
    }

    *//**
     * 获取模型信息
     * @param appId
     *//*
    public  JSONObject getApiMes(String appId) throws Exception {
        System.out.print("getApiMes---------------------------");
        Map<String,String> map = new HashMap<String,String>();
        map.put("code","200");
//        appName = URLEncoder.encode(appName,"utf-8");
        map.put("url","/app/"+appId+"?xrfkey=7rBHABt65vFflaZ7");
        map.put("method","GET");
        JSONObject obj = senseHelper.senseApi(map);
        return obj;
    }
    *//**
     * 根据itcode获取单个用户信息
     * @param itcode
     *//*
    public  JSONArray getUserMes(String itcode) throws Exception {
        System.out.print("getUserMes---------------------------");
        Map<String,String> map = new HashMap<String,String>();
        map.put("code","200");
//        appName = URLEncoder.encode(appName,"utf-8");
        map.put("url","/user?xrfkey=7rBHABt65vFflaZ7&filter=userId%20eq%20'"+itcode+"'%20and%20userDirectory%20eq%20'XINAO'");
        map.put("method","GET");
        JSONArray array = senseHelper.senseApiArray(map);
        return array;
    }

    *//**
     * 模型全部功能推送
     * 复制一个模型然后修改所有者owner信息
     * @param appId
     * @param appName
     * @param itcode 被推送人用户信息
     * @param name 被推送人组织名称
     * @param code 被推送人组织编码
     *//*
    public   List<Map> pushAll(String appId,String appName,String itcode,String name,String code) throws Exception {
        System.out.print("pushAll---------------------------");
        String[] itcodes = itcode.split(",");
        //根据itcode查询用户信息
        List<PUser> users = pUser.getUserMessageByItcode(itcodes);
        List<Map> array = new ArrayList<Map>();
        for(int i = 0; i < users.size(); i++){
            Map hashMap = new HashMap();
            //复制出一份模型返回模型信息
            JSONObject newModel = getCopeAndCreataApi(appId, appName);
            //修改脚本
            senseHelper.webSocketClientApi("1" ,users.get(i).getOrgCode(),users.get(i).getOrgName(),(String)newModel.get("id"));
            //先获取用户信息，根据用户信息修改所有者
            JSONArray userMes = getUserMes(users.get(i).getItcode());
            Object user = null;
            if(userMes != null){
                user = userMes.get(0);
            }
            newModel.put("owner",user);
            Map<String,String> map = new HashMap<String,String>();
            map.put("code","200");
            map.put("body",newModel.toString());
            map.put("url","/app/"+newModel.get("id")+"?xrfkey=7rBHABt65vFflaZ7");
            map.put("method","PUT");
            JSONObject obj = senseHelper.senseApi(map);
            hashMap.put("appId",newModel.get("id"));
            hashMap.put("eid",users.get(i).getEid());
            System.out.println("------modelId---------"+(String) newModel.get("id"));
            map.put("eid", users.get(i).getEid()+"");
            map.put("modelId", (String) newModel.get("id"));
            mUserModelOrderMapper.insertStick(map);
            array.add(hashMap);
        }
        return array;
    }


    *//**
     * 推送模型部分功能（不加载数据） 复制app 修改所有者owner 删除脚本
     * @param appId
     * @param appName
     * @param itcode
     * @return
     *//*

    public  List<Map> pushPartModel(String appId ,String appName,String itcode) throws Exception {
        String[] itcodes = itcode.split(",");
        //根据itcode查询用户信息
        List<PUser> users = pUser.getUserMessageByItcode(itcodes);
        List<Map> array = new ArrayList<Map>();
        for(int i = 0;i < users.size();i++){
            Map hashMap = new HashMap();
            //复制出一份模型返回模型信息
            JSONObject newModel = getCopeAndCreataApi(appId, appName);
            String newAppId = (String) newModel.get("id");
            //脚本置空
            senseHelper.webSocketClientApi("0" ,"","",newAppId);
            //先获取用户信息，根据用户信息修改所有者
            JSONArray userMes = getUserMes(users.get(i).getItcode());
            Object user = null;
            if(userMes != null){
                user = userMes.get(0);
            }
            newModel.put("owner",user);
            Map<String,String> map = new HashMap<String,String>();
            map.put("code","200");
            map.put("body",newModel.toString());
            map.put("url","/app/"+newModel.get("id")+"?xrfkey=7rBHABt65vFflaZ7");
            map.put("method","PUT");
            JSONObject obj = senseHelper.senseApi(map);
            hashMap.put("appId",newAppId);
            hashMap.put("eid",users.get(i).getEid());
            System.out.println("------modelId---------"+(String) newModel.get("id"));
            map.put("eid", users.get(i).getEid()+"");
            map.put("modelId", newAppId);
            mUserModelOrderMapper.insertStick(map);
            array.add(hashMap);
        }
        return array;

    }

    *//**
     * 公开app订阅功能
     * 复制一个模型
     * 修改脚本
     * 发布到对应的管理实体流
     * @param appId
     * @param appName
     * @param stream 对应的管理实体流
     * @param code 对应的管理实体
     * @param name 对应的管理实体编码
     * @return
     * @throws Exception
     *//*
    public  SenseResult subscibePublicModel(String appId,String appName,String stream,String code,String name) throws Exception {
        System.out.print("subscibeModel---------------------------");
        //复制出一份模型返回模型信息
        JSONObject newModel = getCopeAndCreataApi(appId, appName);
        String newAppId = (String) newModel.get("id");
        //修改脚本
        senseHelper.webSocketClientApi("1" ,code,name,newAppId);
        //发布到管理实体流
        Map<String,String> map = new HashMap<String,String>();
        map.put("code","200");
        appName = URLEncoder.encode(appName,"utf-8");
        stream = URLEncoder.encode(stream,"utf-8");
        map.put("url","/app/"+newAppId+"/publish?xrfkey=7rBHABt65vFflaZ7&stream="+stream+"&name="+appName);
        map.put("method","PUT");
        JSONObject obj = senseHelper.senseApi(map);
//        JSONObject updateStream = updateStream(newAppId, stream, appName);
        return new SenseResult(obj);
    }
    *//**
     * 复制公开app功能
     * 复制一个app
     * 修改脚本（脚本置空）
     * 修改owner
     * @param appId
     * @param appName
     * @param stream 对应的管理实体流
     * @param itcode 复制人信息
     * @return
     * @throws Exception
     *//*
    public  SenseResult copyPublicModel(String appId,String appName,String stream,String itcode) throws Exception {
        System.out.print("copyPublicModel---------------------------");
        //复制出一份模型返回模型信息
        JSONObject newModel = getCopeAndCreataApi(appId, appName);
        String newAppId = (String) newModel.get("id");
//        脚本置空
//        senseHelper.webSocketClientApi("0" ,"","",newAppId);
        //先获取用户信息，根据用户信息修改所有者
        JSONArray userMes = getUserMes(itcode);
        Object user = null;
        if(userMes != null){
            user = userMes.get(0);
        }
        newModel.put("owner",user);
        Map<String,String> map = new HashMap<String,String>();
        map.put("code","200");
        map.put("body",newModel.toString());
        map.put("url","/app/"+newModel.get("id")+"?xrfkey=7rBHABt65vFflaZ7");
        map.put("method","PUT");
        JSONObject obj = senseHelper.senseApi(map);
        return new SenseResult(obj);
    }

    *//**
     * 创建模型
     * @return
     *//*
    public  SenseResult updateOwner(String appName,String itcode) throws Exception {
        System.out.print("updateOwner---------------------------");
        //复制出一份模型返回模型信息
        JSONObject newModel = getCopeAndCreataApi("", appName);
        //先获取用户信息，根据用户信息修改所有者
        JSONArray userMes = getUserMes(itcode);
        Object user = null;
        if(userMes != null){
            user = userMes.get(0);
        }
        newModel.put("owner",user);
        Map<String,String> map = new HashMap<String,String>();
        map.put("code","200");
        map.put("body",newModel.toString());
        map.put("url","/app/"+newModel.get("id")+"?xrfkey=7rBHABt65vFflaZ7");
        map.put("method","PUT");
        JSONObject obj = senseHelper.senseApi(map);
        return new SenseResult(obj);
    }

    *//**
     * 加载模型数据
     * @param appId
     *//*
    public  JSONObject reload(String appId) throws Exception {
        System.out.print("reload---------------------------");
        Map<String,String> map = new HashMap<String,String>();
        map.put("code","204");
        map.put("url","/app/"+appId+"/reload?xrfkey=7rBHABt65vFflaZ7");
        map.put("method","POST");
        JSONObject obj = senseHelper.senseApi(map);
        return obj;
    }

    public static void main(String[] args)throws  Exception{
//        JSONObject reload = createStream("6cbe3a6a-e78a-4306-b62f-9d267390e84e");
//        System.out.println(reload.toString());
    }
}*/
