package com.baron.order.service.impl;

import com.baron.common.dataobject.DescreaseStockInput;
import com.baron.common.dataobject.ProductInfoOutput;
import com.baron.order.dataobject.OrderDetail;
import com.baron.order.dataobject.OrderMaster;
import com.baron.order.dto.OrderDTO;
import com.baron.order.enums.OrderStatusEnum;
import com.baron.order.enums.PayStatusEnum;
import com.baron.order.enums.ResultEnum;
import com.baron.order.exception.OrderException;
import com.baron.order.repository.OrderDetailRepository;
import com.baron.order.repository.OrderMasterRepository;
import com.baron.order.service.OrderService;
import com.baron.order.utils.KeyUtil;
import com.baron.product.client.ProductClient;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/***
 @package com.baron.order.service.impl
 @author Baron
 @create 2020-08-12-7:16 PM
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Autowired
    private OrderMasterRepository orderMasterRepository;

    @Autowired
    private ProductClient productClient;

    @Override
    @Transactional
    public OrderDTO create(OrderDTO orderDTO) {

        String orderId = KeyUtil.getUniqueKey();
        //查询商品

        List<String> productIdList = orderDTO.getOrderDetailList().stream().map(OrderDetail::getProductId).collect(Collectors.toList());
        List<ProductInfoOutput> productInfoList = productClient.listForOrder(productIdList);

        //计算总价
        Integer amount = 0;
        BigDecimal orderAmount = new BigDecimal(BigInteger.ZERO);
        for(OrderDetail detail : orderDTO.getOrderDetailList()){
            for(ProductInfoOutput productInfo : productInfoList){
                if(productInfo.getProductId().equals(detail.getProductId())){
                    orderAmount = productInfo.getProductPrice().multiply(new BigDecimal(detail.getProductQuantity())).add(orderAmount);
                    BeanUtils.copyProperties(productInfo,detail);
                    detail.setOrderId(orderId);
                    detail.setDetailId(KeyUtil.getUniqueKey());
                    //订单详情入库
                    orderDetailRepository.save(detail);
                }
            }
        }

        //扣库存
        List<DescreaseStockInput> descreaseStockInputList = orderDTO.getOrderDetailList().stream()
                .map(e -> new DescreaseStockInput(e.getProductId(), e.getProductQuantity()))
                .collect(Collectors.toList());
                productClient.descreaseStock(descreaseStockInputList);


        //订单入库
        OrderMaster orderMaster = new OrderMaster();
        orderDTO.setOrderId(orderId);
        BeanUtils.copyProperties(orderDTO, orderMaster);
        orderMaster.setOrderAmount(new BigDecimal(5));
        orderMaster.setOrderStatus(OrderStatusEnum.NEW.getId());
        orderMaster.setPayStatus(PayStatusEnum.WAIT.getId());

        orderMasterRepository.save(orderMaster);
        return orderDTO;
    }

    @Override
    @Transactional
    public OrderDTO finish(String orderId) {
        //查询订单
        Optional<OrderMaster> orderMasterOptional = orderMasterRepository.findById(orderId);
        if(!orderMasterOptional.isPresent()){
            throw new OrderException(ResultEnum.ORDER_NOT_EXIST);
        }
        //判断订单
        OrderMaster orderMaster = orderMasterOptional.get();
        if(!OrderStatusEnum.NEW.getId().equals(orderMaster.getOrderStatus())){
            throw new OrderException(ResultEnum.ORDER_STATUS_ERROR);
        }
        //修改状态
        orderMaster.setOrderStatus(OrderStatusEnum.FINISHED.getId());
        orderMasterRepository.save(orderMaster);

        //查询订单
        List<OrderDetail> orderDetailList = orderDetailRepository.findByOrderId(orderId);
        if(CollectionUtils.isEmpty(orderDetailList)){
            throw new OrderException(ResultEnum.ORDER_DETAIL_NOT_EXIST);
        }
        OrderDTO orderDTO = new OrderDTO();
        BeanUtils.copyProperties(orderMaster,orderDTO);
        orderDTO.setOrderDetailList(orderDetailList);

        return orderDTO;

    }
}
