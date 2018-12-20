package com.jeiker.shell.app;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

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
     * say-hi Xiao
     * 表示方法sayHi是可以在命令行运行的命令。
     */
    @ShellMethod("Say hi")
    public String sayHi(String name) {
        return String.format("Hi %s", name);
    }

    /**
     * 传入参数： echo1 --a 1 --b 2 --c 3
     *
     * @param a
     * @param b
     * @param c
     * @return
     */
    @ShellMethod("Echo1")
    public String echo1(int a, int b, int c) {
        return String.format("a = %d, b = %d, c = %d", a, b, c);
    }

    /**
     * 命名传入参数： echo2 1 --boy 2 3
     *
     * @param a
     * @param b
     * @param c
     * @return
     */
    @ShellMethod("Echo2")
    public String echo2(int a, @ShellOption("--boy") int b, int c) {
        return String.format("a = %d, b = %d, c = %d", a, b, c);
    }

}
