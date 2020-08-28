package com.baron.order.utils;

import com.baron.order.VO.ResultVO;

/***
 @package com.baron.order.utils
 @author Baron
 @create 2020-08-13-11:51 AM
 */
public class ResultVOUtil {

    public static ResultVO success(Object object){
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(0);
        resultVO.setMsg("成功");
        resultVO.setData(object);
        return resultVO;
    }
}
