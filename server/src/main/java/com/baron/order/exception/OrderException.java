package com.baron.order.exception;

import com.baron.order.enums.ResultEnum;

/***
 @package com.baron.order
 @author Baron
 @create 2020-08-12-7:41 PM
 */
public class OrderException extends RuntimeException{
    private Integer code;

    public OrderException(Integer code, String message) {
        super(message);
        this.code = code;
    }

    public OrderException(ResultEnum resultEnum) {
        super(resultEnum.getMessage());
        this.code = resultEnum.getCode();
    }
}
