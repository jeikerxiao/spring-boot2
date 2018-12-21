package com.jeiker.shell.config;

import org.jline.utils.AttributedString;
import org.jline.utils.AttributedStyle;
import org.springframework.shell.jline.PromptProvider;
import org.springframework.stereotype.Component;

/**
 * Description: 自定义提示符
 * User: jeikerxiao
 * Date: 2018/12/21 2:31 PM
 */
@Component
public class PromptProviderImpl implements PromptProvider {

    @Override
    public AttributedString getPrompt() {
        return new AttributedString("xiao-shell=>", AttributedStyle.DEFAULT.foreground(AttributedStyle.BLUE));
    }
}
