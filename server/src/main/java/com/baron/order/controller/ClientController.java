package com.baron.order.controller;

import com.baron.common.dataobject.DescreaseStockInput;
import com.baron.common.dataobject.ProductInfoOutput;
import com.baron.product.client.ProductClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

/***
 @package com.baron.order.controller
 @author Baron
 @create 2020-08-13-3:14 PM
 */
@RestController
@Slf4j
@ComponentScan(basePackages = {"com.baron","com.baron.product.client.ProductClient"})
public class ClientController {

    @Autowired
    private LoadBalancerClient loadBalancerClient;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private ProductClient productClient;

    @GetMapping("/getProductMsg")
    public String getProductMsg() {
        //方法一
//        RestTemplate restTemplate = new RestTemplate();
//        String response = restTemplate.getForObject("http://localhost:8081/msg",String.class);
//        log.info("response={}", response);
//        return response;

        //方法二
        RestTemplate restTemplate = new RestTemplate();
        ServiceInstance serviceInstance = loadBalancerClient.choose("PRODUCT");
        String url = String.format("http://%s:$s", serviceInstance.getHost(), serviceInstance.getPort());
        String response = restTemplate.getForObject(url, String.class);
        log.info("response={}", response);
        return response;

        //方法三
//        String response = restTemplate.getForObject("http://PRODUCT/msg",String.class);
//        log.info("response={}", response);
//        return response;

    }

    @GetMapping("/getProductMsgByFeign")
    public String getProductMsgByFeign() {
//        String response = productClient.productMSG();
        String response = "productClient.productMSG()";
        log.info("response={}" + response);
        return response;

    }


    @GetMapping("/getProductList")
    public List<ProductInfoOutput> listForOrder() {
        List<ProductInfoOutput> response = productClient.listForOrder(Arrays.asList("157875196366160022"));
        log.info("response={}" + response.toString());
        return response;
    }


    @GetMapping("/productDescreaseStock")
    public void productDescreaseStock(){
        DescreaseStockInput descreaseStockInput = new DescreaseStockInput("157875196366160022",1);
        productClient.descreaseStock(Arrays.asList(descreaseStockInput));
        log.info("productDescreaseStock");
    }

}
