package com.jeiker.batch.step;

import com.jeiker.batch.dto.CommandDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.stereotype.Service;

/**
 * Description: 1.读数据：CommandLineMapper将文件每行数据转换成程序CommandDTO对象
 * User: jeikerxiao
 * Date: 2019/1/9 9:23 AM
 */
@Service
@Slf4j
public class CommandLineMapper implements LineMapper<CommandDTO> {

    @Override
    public CommandDTO mapLine(String line, int lineNumber) {
        // 逗号分割每一行数据
        String[] args = line.split(",");
        // 创建 CommandDTO 对象
        CommandDTO commandDTO = new CommandDTO();
        // 设置id值到对象中
        commandDTO.setId(args[0]);
        // 设置status值到对象中
        commandDTO.setStatus(args[1]);
        // 返回对象
        return commandDTO;
    }
}
