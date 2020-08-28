package com.baron.order.enums;

import lombok.Getter;

/***
 @package com.baron.order
 @author Baron
 @create 2020-08-12-6:38 PM
 */
@Getter
public enum PayStatusEnum {
    WAIT(0, "等待支付"),
    SUCCESS(1, "支付成功"),
    ;
    private Integer id;
    private String message;

    PayStatusEnum(Integer id, String message) {
        this.id = id;
        this.message = message;
    }
}
