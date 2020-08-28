package com.baron.order.repository;

import com.baron.order.OrderApplicationTests;
import com.baron.order.dataobject.OrderDetail;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

import static org.junit.Assert.*;

/***
 @package com.baron.order
 @author Baron
 @create 2020-08-12-6:20 PM
 */
@Component
public class OrderDetailRepositoryTest extends OrderApplicationTests {

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Test
    public void testSave(){
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setOrderId("1234567");
        orderDetail.setDetailId("123456");
        orderDetail.setProductId("productId");
        orderDetail.setProductName("prodName");
        orderDetail.setProductQuantity(3);
        orderDetail.setProductPrice(new BigDecimal(100));
        orderDetail.setProductIcon("HOT!");
        OrderDetail result = orderDetailRepository.save(orderDetail);
        Assert.assertTrue(result != null);
    }

}
