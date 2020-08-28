package com.baron.order.service;

import com.baron.order.dto.OrderDTO;
import org.springframework.stereotype.Service;

/***
 @package com.baron.order
 @author Baron
 @create 2020-08-12-7:11 PM
 */
@Service
public interface OrderService {

    OrderDTO create(OrderDTO orderDTO);


    OrderDTO finish(String orderId);
}
