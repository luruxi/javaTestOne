package enn.testone.controller;

import enn.testone.entity.Responsed;
import enn.testone.enums.ExceptionEnum;
import enn.testone.exception.Myexception;
import enn.testone.utils.ResponseUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@Api(value = "异常测试", tags = {"异常测试"})
public class ExcepTest {
    @RequestMapping(value = "/except/{except}")
    @ApiOperation(value = "异常测试1",notes = "异常测试1",httpMethod = "GET")
    public Responsed exceptOne(@PathVariable("except") Boolean except ){
        log.info("exceptOne");
        if(except){
            log.error("参数为空");
            return null;
        }
        log.debug("参数有值");
        return ResponseUtil.success();

    }

    @RequestMapping(value = "/exceptTwo/{except}")
    public Responsed exceptTwo(@PathVariable("except") Boolean except ) throws Exception{
        if(except){
            try {
                throw new Myexception(ExceptionEnum.OTHER_ERROR);
            }catch (Exception e){
                System.out.println("1111111111111111");
                e.printStackTrace();
                System.out.println("1111111111111111");
//                log.info();
                log.info("ceshi");
                return ResponseUtil.success();
            }

        }
        log.info("ceshi");
        return ResponseUtil.success();
    }
}
