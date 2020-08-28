package com.baron.order.dataobject;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

/***
 @package com.baron.order
 @author Baron
 @create 2020-08-12-6:02 PM
 */
@Data
@Entity
public class OrderDetail {
//    SpringCloud_Sell.order_detail.detail_id
//    SpringCloud_Sell.order_detail.order_id
//    SpringCloud_Sell.order_detail.product_id
//    SpringCloud_Sell.order_detail.product_name
//    SpringCloud_Sell.order_detail.product_price
//    SpringCloud_Sell.order_detail.product_quantity
//    SpringCloud_Sell.order_detail.product_icon
//    SpringCloud_Sell.order_detail.create_time
//    SpringCloud_Sell.order_detail.update_time
    @Id
    private String detailId;
    private String orderId;
    private String productId;
    private String productName;
    private BigDecimal productPrice;
    private Integer productQuantity;
    private String productIcon;
    private Date createTime;
    private Date updateTime;
}
