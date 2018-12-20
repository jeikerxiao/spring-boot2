package com.jeiker.shell.app;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

/**
 * Description: 指定方法名称和参数默认值
 * User: jeikerxiao
 * Date: 2018/12/20 9:22 PM
 */
@ShellComponent
public class NameAndDefaultValueApp {

    /**
     * 测试：default 123
     *
     * @param value
     */
    @ShellMethod(key = "default", value = "With default value")
    public void withDefault(@ShellOption(defaultValue = "Hello") final String value) {
        System.out.printf("Value: %s%n", value);
    }

    /**
     * 测试：echo3 1 2 3
     *
     * @param numbers
     * @return
     */
    @ShellMethod("Echo3")
    public String echo3(@ShellOption(arity = 3) int[] numbers) {
        return String.format("a = %d, b = %d, c = %d", numbers[0], numbers[1], numbers[2]);
    }
}
