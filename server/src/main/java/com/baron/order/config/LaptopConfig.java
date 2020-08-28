package com.baron.order.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

/***
 @package com.baron.order.config
 @author Baron
 @create 2020-08-17-11:56 PM
 */
@Data
@Component
@ConfigurationProperties("laptop")
@RefreshScope
public class LaptopConfig {

    private String brand;

    private String model;

    private Integer price;
}
