package enn.testone.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


@Slf4j
@Controller
@RequestMapping("/testOne")
@Api(value = "JSP",tags = {"JSP"})
public class Jsptest {
    @RequestMapping("/")
    @ApiOperation(value = "testOne",notes = "testOne",httpMethod = "GET")
    public ModelAndView testOne(){
        System.out.println("testOne");
//        log.info();
        log.info("testOne");
        ModelAndView modelAndView = new ModelAndView("/index");
        modelAndView.addObject("name","lsy");
        return modelAndView;
    }
}
