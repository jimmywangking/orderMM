package com.baron.order.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/***
 @package com.baron.order.controller
 @author Baron
 @create 2020-08-15-5:08 PM
 */
@RestController
@RequestMapping("env")
@RefreshScope
public class EnvController {

    @Value("${env}")
    private String env;

    @GetMapping(value = "/sayHi")
    public String say(){
        return "HelloHi";
    }

    @GetMapping("/print")
    public String print(){
        return this.env;
    }
}
