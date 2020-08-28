package com.baron.order.message;

import com.baron.order.OrderApplicationTests;
import org.junit.Test;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

import static org.junit.Assert.*;

/***
 @package com.baron.order.message
 @author Baron
 @create 2020-08-18-5:42 PM
 */
@Component
public class MqReceiverTest extends OrderApplicationTests {

    @Autowired
    private AmqpTemplate amqpTemplate ;

    @Test
    public  void send(){
        amqpTemplate.convertAndSend("myQueue","now "+ new Date());
    }

    @Test
    public  void sendOrder(){
        amqpTemplate.convertAndSend("myQueue","digital","now "+ new Date());
    }
}
