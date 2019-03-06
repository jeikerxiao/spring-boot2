package com.jeiker.weixin.utils;

import java.io.Serializable;

/**
 * Description: weixin-java-mp-demo-springboot
 * User: jeikerxiao
 * Date: 2019/3/4 5:29 PM
 */
public class TwoTuple<A, B> implements Serializable {

    private static final long serialVersionUID = 1L;

    private A first;
    private B second;

    public TwoTuple(A a, B b) {
        this.first = a;
        this.second = b;
    }

    public A getFirst() {
        return first;
    }

    public void setFirst(A first) {
        this.first = first;
    }

    public B getSecond() {
        return second;
    }

    public void setSecond(B second) {
        this.second = second;
    }

}
