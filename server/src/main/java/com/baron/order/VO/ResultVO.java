package com.baron.order.VO;

import lombok.Data;

/***
 @package com.baron.order
 @author Baron
 @create 2020-08-13-11:44 AM
 */
@Data
public class ResultVO<T> {

    private Integer code;

    private  String msg;

    private T data;

}
