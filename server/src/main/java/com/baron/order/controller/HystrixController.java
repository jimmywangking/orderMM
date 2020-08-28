package com.baron.order.controller;

import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

/***
 @package com.baron.order.controller
 @author Baron
 @create 2020-08-20-1:21 PM
 http://localhost:8082/getProductInfoList
 */
@RestController
@DefaultProperties(defaultFallback = "defaultFallback")
public class HystrixController {

    //降级
//    @HystrixCommand(fallbackMethod = "fallback")

    //超时
//    @HystrixCommand(commandProperties = {
//            @HystrixProperty(name ="execution.isolation.thread.timeoutInMilliseconds",value ="3000")
//    })

    //配置
//    @HystrixCommand()

    //熔断
    //        this.circuitBreakerEnabled = getProperty(propertyPrefix, key, "circuitBreaker.enabled", builder.getCircuitBreakerEnabled(), default_circuitBreakerEnabled);
    //        this.circuitBreakerRequestVolumeThreshold = getProperty(propertyPrefix, key, "circuitBreaker.requestVolumeThreshold", builder.getCircuitBreakerRequestVolumeThreshold(), default_circuitBreakerRequestVolumeThreshold);
    //        this.circuitBreakerSleepWindowInMilliseconds = getProperty(propertyPrefix, key, "circuitBreaker.sleepWindowInMilliseconds", builder.getCircuitBreakerSleepWindowInMilliseconds(), default_circuitBreakerSleepWindowInMilliseconds);
    //        this.circuitBreakerErrorThresholdPercentage = getProperty(propertyPrefix, key, "circuitBreaker.errorThresholdPercentage", builder.getCircuitBreakerErrorThresholdPercentage(), default_circuitBreakerErrorThresholdPercentage);
    //
    @HystrixCommand(commandProperties = {
            @HystrixProperty(name ="circuitBreaker.enabled",value ="true"),
            @HystrixProperty(name ="circuitBreaker.requestVolumeThreshold",value ="10"),
            @HystrixProperty(name ="circuitBreaker.sleepWindowInMilliseconds",value ="10000"),
            @HystrixProperty(name ="circuitBreaker.errorThresholdPercentage",value ="60")
    })
    @GetMapping("/getProductInfoList")
    public String getProductInfoList(@RequestParam("number") Integer number){
//    public String getProductInfoList(){

        //测试熔断
        if (number %2 == 0){
            return "success!";
        }
        //熔断结束
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.postForObject("http://localhost:8081/product/listForOrder",
                Arrays.asList("157875196366160022"),
                String.class);
    }

    private String fallback(){
        return "-_-!！    服务器被挤爆了，请稍后再试～";
    }

    private String defaultFallback(){
        return "-_-!!！    服务器维护中，请稍后再试～";
    }
}
