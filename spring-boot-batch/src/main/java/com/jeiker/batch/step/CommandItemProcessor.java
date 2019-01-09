package com.jeiker.batch.step;

import com.jeiker.batch.dto.CommandDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Service;

/**
 * Description: 2.处理数据
 * User: jeikerxiao
 * Date: 2019/1/9 9:27 AM
 */
@Service
@Slf4j
public class CommandItemProcessor implements ItemProcessor<CommandDTO, CommandDTO> {

    @Override
    public CommandDTO process(CommandDTO commandDTO) throws Exception {
        // 模拟下发命令给设备
        log.info("send command to device, id = {}", commandDTO.getId());
        // 更新命令状态
        commandDTO.setStatus("SENT");
        // 返回命令对象
        return commandDTO;
    }
}
