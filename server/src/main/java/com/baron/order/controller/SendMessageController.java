package com.baron.order.controller;

import com.baron.order.dto.OrderDTO;
import com.baron.order.message.StreamClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/***
 @package com.baron.order.controller
 @author Baron
 @create 2020-08-18-8:36 PM
 */
@RestController
public class SendMessageController {

    @Autowired
    private StreamClient streamClient;

//    @GetMapping("/sendMessage")
//    public void process(){
//        streamClient.output().send(MessageBuilder.withPayload("now "+new Date()).build());
//    }


    @GetMapping("/sendMessage")
    public void process(){
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setOrderId("1234");
        streamClient.output().send(MessageBuilder.withPayload(orderDTO).build());
    }
}
