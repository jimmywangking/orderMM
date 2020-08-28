package com.baron.order.repository;

import com.baron.order.OrderApplicationTests;
import com.baron.order.dataobject.OrderMaster;
import com.baron.order.enums.OrderStatusEnum;
import com.baron.order.enums.PayStatusEnum;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;


/***
 @package com.baron.order.repository
 @author Baron
 @create 2020-08-12-6:35 PM
 */
@Component
public class OrderMasterRepositoryTest extends OrderApplicationTests {

    @Autowired
    private OrderMasterRepository orderMasterRepository;

    @Test
    public void testSave(){
        OrderMaster orderMaster = new OrderMaster();
        orderMaster.setOrderId("123");
        orderMaster.setBuyerName("Hunter");
        orderMaster.setBuyerAddress("Hi-Tech Zone");
        orderMaster.setBuyerPhone("1233211234567");
        orderMaster.setOrderAmount(new BigDecimal(2));
        orderMaster.setBuyerOpenid("WSE");
        orderMaster.setOrderStatus(OrderStatusEnum.NEW.getId());
        orderMaster.setPayStatus(PayStatusEnum.SUCCESS.getId());
        OrderMaster result = orderMasterRepository.save(orderMaster);
        Assert.assertTrue(result != null);

    }

}
