package enn.testone.utils;

import enn.testone.entity.Responsed;

public class ResponseUtil {
    public static Responsed success(Object object){
        Responsed<Object> responsed = new Responsed<>();
        responsed.setCode(200);
        responsed.setMessage("SUCCESS!");
        if(object!=null){
            responsed.setData(object);
        }
        return responsed;
    }

    public static Responsed success(){
        return success(null);
    }

    public static Responsed error(Integer code,String message){
        Responsed responsed = new Responsed();
        responsed.setCode(code);
        responsed.setMessage(message);
        return responsed;
    }
}
