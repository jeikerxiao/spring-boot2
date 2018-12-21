package com.jeiker.shell.app;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

/**
 * Description: 校验参数
 * User: jeikerxiao
 * Date: 2018/12/20 9:32 PM
 */
@ShellComponent
public class ParametersValidationApp {

    /**
     * string-size jeikerxiao
     *
     * @param name
     * @return
     */
    @ShellMethod("string-size")
    public String stringSize(@Size(min = 3, max = 16) String name) {
        return String.format("Your name is %s", name);
    }

    /**
     * number-range 23
     *
     * @param number
     * @return
     */
    @ShellMethod("number-range")
    public String numberRange(@Min(10) @Max(100) int number) {
        return String.format("The number is %s", number);
    }
}
