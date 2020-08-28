package com.baron.order.controller;

import com.baron.order.config.LaptopConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/***
 @package com.baron.order.controller
 @author Baron
 @create 2020-08-18-12:03 AM
 */
@RestController
@RefreshScope
public class LapTopController {

    @Autowired
    private LaptopConfig laptopConfig;

    @GetMapping("/laptop/print")
    public String print(){
        return "LapTopController: "+laptopConfig.getBrand()+" - "+laptopConfig.getModel()+" : "+laptopConfig.getPrice();
    }
}
