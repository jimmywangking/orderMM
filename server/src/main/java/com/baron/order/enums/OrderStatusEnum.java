package com.baron.order.enums;

import lombok.Getter;

/***
 @package com.baron.order
 @author Baron
 @create 2020-08-12-6:31 PM
 */
@Getter
public enum OrderStatusEnum {
    NEW(0, "新订单"),
    FINISHED(1, "完结"),
    CANCEL(2, "取消"),
    ;
    private Integer id;
    private String message;

    OrderStatusEnum(Integer id, String message) {
        this.id = id;
        this.message = message;
    }
}
