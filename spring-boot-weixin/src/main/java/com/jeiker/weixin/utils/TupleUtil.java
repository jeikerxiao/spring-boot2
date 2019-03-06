package com.jeiker.weixin.utils;

/**
 * Description: weixin-java-mp-demo-springboot
 * User: jeikerxiao
 * Date: 2019/3/4 5:29 PM
 */
public abstract class TupleUtil {

    public static <A, B> TwoTuple<A, B> tuple(A a, B b) {
        return new TwoTuple<A, B>(a, b);
    }
}
