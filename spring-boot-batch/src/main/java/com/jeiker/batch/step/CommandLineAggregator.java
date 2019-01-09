package com.jeiker.batch.step;

import com.jeiker.batch.dto.CommandDTO;
import org.springframework.batch.item.file.transform.LineAggregator;

/**
 * Description: 3.写数据：将 CommandDTO 对象转成字符串
 * User: jeikerxiao
 * Date: 2019/1/9 9:29 AM
 */
public class CommandLineAggregator implements LineAggregator<CommandDTO> {

    @Override
    public String aggregate(CommandDTO commandDTO) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(commandDTO.getId());
        stringBuilder.append(",");
        stringBuilder.append(commandDTO.getStatus());
        return stringBuilder.toString();
    }
}
