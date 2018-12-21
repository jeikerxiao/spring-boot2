package com.jeiker.shell.app;

import com.jeiker.shell.model.PrefixedResult;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

/**
 * Description: 使用 PrefixedResult 的命令
 * User: jeikerxiao
 * Date: 2018/12/21 2:25 PM
 */
@ShellComponent
public class CustomResultHandlerApp {

    @ShellMethod("result-handler")
    public PrefixedResult resultHandler() {
        return new PrefixedResult("PRE", "Hello!");
    }
}
