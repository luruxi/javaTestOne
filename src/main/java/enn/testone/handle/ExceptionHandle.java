package enn.testone.handle;

import enn.testone.entity.Responsed;
import enn.testone.exception.Myexception;
import enn.testone.utils.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

public class ExceptionHandle {
    private final static Logger logger = LoggerFactory.getLogger(ExceptionHandle.class);

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Responsed handle(Exception e){
        logger.info("进入error");

        //判断是否是自定义异常
        if(e instanceof Myexception){
            Myexception myexception = (Myexception) e;
            return ResponseUtil.error(myexception.getCode(),myexception.getMessage());
        }else {
            logger.error("系统异常{}",e);
            return ResponseUtil.error(1000,"系统异常");
        }
    }
}
