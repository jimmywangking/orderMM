package com.baron.order.dto;

import com.baron.order.dataobject.OrderDetail;
import lombok.Data;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

/***
 @package com.baron.order.dto
 @author Baron
 @create 2020-08-12-7:12 PM
 */
@Data
public class OrderDTO {
    private String orderId;
    private String buyerName;
    private String buyerPhone;
    private String buyerAddress;
    private String buyerOpenid;
    private BigDecimal orderAmount;
    private Integer orderStatus;
    private Integer payStatus;
    private Date createTime;
    private Date updateTime;
    private List<OrderDetail> orderDetailList;
}
