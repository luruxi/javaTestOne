package enn.testone.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import jdk.nashorn.internal.runtime.logging.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@Api(value = "Hello",tags = {"Hello"})
public class Hello {
    @RequestMapping("/hello")
    @ApiOperation(value = "hello",notes = "hello",httpMethod = "GET")
    public String hello(){
        System.out.println("Hello World");
        return "Hello World";
    }
}
