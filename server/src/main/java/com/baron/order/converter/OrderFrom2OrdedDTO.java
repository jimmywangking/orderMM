package com.baron.order.converter;

import com.baron.order.form.OrderForm;
import com.baron.order.dataobject.OrderDetail;
import com.baron.order.dto.OrderDTO;
import com.baron.order.enums.ResultEnum;
import com.baron.order.exception.OrderException;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import lombok.extern.slf4j.Slf4j;

import javax.management.OperationsException;
import java.util.ArrayList;
import java.util.List;

/***
 @package com.baron.order
 @author Baron
 @create 2020-08-12-8:04 PM
 */
@Slf4j
public class OrderFrom2OrdedDTO {

    public static OrderDTO convert(OrderForm orderForm){
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setBuyerName(orderForm.getName());
        orderDTO.setBuyerAddress(orderForm.getAddress());
        orderDTO.setBuyerPhone(orderForm.getPhone());
        orderDTO.setBuyerOpenid(orderForm.getOpenid());

        List<OrderDetail> orderDetailList = new ArrayList<>();

        Gson gson = new Gson();

        try{
            orderDetailList = gson.fromJson(orderForm.getItems(),
                    new TypeToken<List<OrderDetail>>(){}.getType());
        }catch (Exception e){
            log.error("【json转换】错误, String = {}", orderForm.getItems());
            throw new OrderException(ResultEnum.PARAM_ERROR);
        }
        orderDTO.setOrderDetailList(orderDetailList);
        return orderDTO;
    }
}
