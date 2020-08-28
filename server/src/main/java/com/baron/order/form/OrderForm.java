package com.baron.order.form;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

/***
 @package com.baron.order
 @author Baron
 @create 2020-08-12-7:45 PM
 */
@Data
public class OrderForm {

    @NotEmpty(message = "姓名必填")
    private String name;

    @NotEmpty(message="手机号必填")
    private String phone;

    @NotEmpty(message = "地址必填")
    private String address;

    @NotEmpty(message = "openid必填")
    private String openid;

    @NotEmpty(message = "购物车不能为空")
    private String items;
}
