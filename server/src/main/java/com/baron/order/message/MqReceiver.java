package com.baron.order.message;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/***
 @package com.baron.order
 @author Baron
 @create 2020-08-18-5:37 PM
 */
@Slf4j
@Component
public class MqReceiver {

    //1. @RabbitListener( queues = "myQueue")
    //2. @RabbitListener(queuesToDeclare = @Queue("myQueue"))
    //3. Exchange和Queue绑定
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue("myQueue"),
            exchange = @Exchange("myExchange")
    ))
    public void process(String message){
        log.info("MqReceiver:{}", message);
    }


   /*

    */
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue("myOrder"),
            key = "digital",
            exchange = @Exchange("digitalOrder")
    ))
    public void processDigital(String message){
        log.info("Digital Order:{}", message);
    }

    /*

     */
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue("myOrder"),
            key = "fruit",
            exchange = @Exchange("fruitOrder")
    ))
    public void processFruit(String message){
        log.info("Fruit Order:{}", message);
    }
}
