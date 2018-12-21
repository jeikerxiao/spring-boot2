package com.jeiker.shell.app;

import org.springframework.shell.Availability;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

/**
 * Description: 动态命令可用性
 * User: jeikerxiao
 * Date: 2018/12/21 2:37 PM
 */
@ShellComponent
public class RunTwiceToEnableApp {

    private boolean run = false;

    @ShellMethod("Run once")
    public void runOnce() {
        this.run = true;
    }

    @ShellMethod("Run again")
    public void runAgain() {
        System.out.println("Run!");
    }

    public Availability runAgainAvailability() {
        return run ? Availability.available() : Availability.unavailable("You should run runOnce first!");
    }
}
