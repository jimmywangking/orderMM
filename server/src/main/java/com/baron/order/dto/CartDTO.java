package com.baron.order.dto;

import lombok.Data;

/***
 @package com.baron.product
 @author Baron
 @create 2020-08-13-6:07 PM
 */
@Data
public class CartDTO {

    private String productId;

    private Integer productQuantity;

    public CartDTO() {
    }

    public CartDTO(String productId, Integer productQuantity) {
        this.productId = productId;
        this.productQuantity = productQuantity;
    }
}
