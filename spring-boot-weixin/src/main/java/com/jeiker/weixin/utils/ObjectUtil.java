package com.jeiker.weixin.utils;

import java.util.Collection;
import java.util.Map;

/**
 * Description: weixin-java-mp-demo-springboot
 * User: jeikerxiao
 * Date: 2019/3/4 5:27 PM
 */
public class ObjectUtil {
    /**
     * 判断对象是为空
     *
     * @param obj
     * @return 空返回false，非空true
     */
    public static boolean isNotEmpty(Object obj) {
        if (null == obj) {
            return false;
        }
        if (obj instanceof Collection) {
            if (0 == ((Collection) obj).size()) {
                return false;
            }
        } else if (obj instanceof Map) {
            if (0 == ((Map) obj).size()) {
                return false;
            }
        }
        return true;
    }
}
