package com.baron.order.utils;

import java.util.Random;

/***
 @package com.baron.order
 @author Baron
 @create 2020-08-12-7:29 PM
 */
public class KeyUtil {
    public static synchronized String getUniqueKey(){
        return System.currentTimeMillis() + String.valueOf(new Random().nextInt(900000)+100000);
    }
}
