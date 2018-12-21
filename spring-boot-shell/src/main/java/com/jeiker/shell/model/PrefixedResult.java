package com.jeiker.shell.model;

/**
 * Description: 带前缀的结果处理
 * User: jeikerxiao
 * Date: 2018/12/21 2:21 PM
 */
public class PrefixedResult {

    private final String prefix;
    private final String result;

    public PrefixedResult(String prefix, String result) {
        this.prefix = prefix;
        this.result = result;
    }

    public String getPrefix() {
        return prefix;
    }

    public String getResult() {
        return result;
    }
}
