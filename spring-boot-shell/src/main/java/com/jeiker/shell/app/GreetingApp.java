package com.jeiker.shell.app;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

/**
 * Description: spring-boot2
 * User: jeikerxiao
 * Date: 2018/12/20 8:49 PM
 */
/**
 * 声明类 Greeting App 是一个Spring Shell 的组件。
 */
@ShellComponent
public class GreetingApp {

    /**
     * 表示方法sayHi是可以在命令行运行的命令。
     */
    @ShellMethod("Say hi")
    public String sayHi(String name) {
        return String.format("Hi %s", name);
    }
}
