package com.jeiker.shell.config;

import com.jeiker.shell.model.PrefixedResult;
import org.springframework.shell.ResultHandler;
import org.springframework.stereotype.Component;

/**
 * Description: PrefixedResult 对应处理器实现
 * User: jeikerxiao
 * Date: 2018/12/21 2:23 PM
 */
@Component
public class PrefixedResultHandler implements ResultHandler<PrefixedResult> {

    @Override
    public void handleResult(PrefixedResult prefixedResult) {
        System.out.printf("%s --> %s%n", prefixedResult.getPrefix(), prefixedResult.getResult());
    }
}
