# spring-boot-batch

Spring Boot 配合 Spring Batch。

Spring Batch是一个轻量级的综合性批处理框架，可以应用于企业级大数据量处理系统。

SpringBatch可以提供大量的，可重复的数据处理功能，包括日志/跟踪(tracing)，事务管理，任务处理(processing)统计，任务重启，忽略(skip)，和资源管理等功能。

此外还提供了许多高级服务和特性，使之能够通过优化(optimization)和分片技术(partitioning techniques)来高效地执行超大型数据集的批处理任务。


典型的批处理流程是:

1. 读数据 - 从数据库、文件或队列中读取大量数据
2. 处理数据 - 通过业务规则处理数据
3. 写数据 - 将处理完的数据按需求方式写（数据库、文件等）

通常Spring Batch工作在离线模式下，不需要用户干预、就能自动进行基本的批处理迭代，进行类似事务方式的处理。


## 适用业务

* 定期提交批处理任务（日终处理）
* 并发批处理：并行执行任务
* 分阶段，企业消息驱动处理
* 高并发批处理任务
* 失败后手动或定时重启
* 按顺序处理依赖任务 (使用工作流驱动的批处理插件)
* 局部处理：跳过记录(例如在回滚时)
* 完整的批处理事务：因为可能有小数据量的批处理或存在存储过程/脚本

## 核心能力

* 利用Spring编程模式：使开发者专注于业务逻辑，让框架解决基础功能
* 明确划分在批处理基础架构、执行环境、应用
* 通用的核心服务以接口形式提供
* 提供简单的默认实现，以实现核心执行接口的“开箱即用”
* 易于配置、定制和扩展服务
* 核心服务很容易扩展与替换，且不会影响基础层
* 简单部署模型

## 架构层次

Spring Batch架构主要分为三类高级组件: 应用层(Application), 核心层(Core) 和基础架构层(Infrastructure)。

图1-SpringBatch层次架构图

* 应用层(Application)：指开发人员编写的所有批处理业务作业和自定义代码。
* 核心层(Core)：指加载和控制批处理作业所必需的核心类。含JobLauncher，Job和 Step的实现。
* 基础架构层(Infrastructure)：应用层与核心层都构建在基础架构层之上。基础架构包括通用的readers(ItemReader)和writers(ItemWriter)，以及services (如重试模块 RetryTemplate)，可以被应用层和核心层所使用。

## 领域术语

Step：表示作业Job中的一个完整业务逻辑步骤，一个Job可以有一个或者多个Step组成。

StepExecution：表示试运行一个步骤step的句柄。只有步骤step真的得到运行才会被创建。

Job（作业）：作业是封装整个批处理过程的实体。一个简单的作业需要配置作业名、有序的步骤step、及是否重启。

JobInstance（作业实例）：一个作业实例与其要加载的数据无硬性关联，这完全是由数据读入器ItemReader决定。比如：是否使用同一个作业实例，是由ItemReader根据前一次执行的状态位（state）决定。用新的JobInstance意味从开头读取数据，用已有的表示从上次结束的地方开始。

JobParameter（作业参数）：是指一个批量作业开始的参数集。同时，可以用于标识JobInstance的唯一性。所以可以认为JobInstance=Job+JobParameter。

JobExecution：表示试运行一个作业的句柄。

如下图2.2.1所示，Job好比是容器，可以包含多个业务逻辑步骤step与多个JobInstance，来组织作业的执行（亦可以保证作业的重启）,而JobExecution则是致力于记录执行状态。

每一次执行中JobExecution和step都会进行数据信息传输，比如：commitCount、rollbackCount、startTime、endTime等，这些都会记录进StepExecution。

图2-批处理框架运行期的模型


JobLauncher（作业调度器）：是Spring Batch框架基础设施层提供运行Job的能力。对于将给定Job名称和作Job Parameters的Job，由Java程序、命令行或者其它调度框架（如Quartz）中调用JobLauncher执行Job。

JobRepository（作业仓库）：来存储Job执行期的元数据（这里的元数据是指Job Instance、Job Execution、Job Parameters、Step Execution、Execution Context等数据）。有两种默认实现——内存或数据库。若将元数据存放在数据库中，可以随时监控批处理Job的执行状态。Job执行结果是成功还是失败，并且使失败Job重新启动Job成为可能。

ItemReader：是对step的输入的抽象，每次只读入一条记录，读取完所有记录后，则返回null。

ItemProcessor：是对每条记录按业务逻辑处理的抽象。

ItemWriter：是对step的输出的抽象，每次只可以提供给一次批作业或记录队（chunk）。

下图2.2.2显示了完整的SpringBatch领域概念模型。

JobLancaster启动Job，Job可有多个Step组合，每一个step对应一个ItemReader、ItemProcessor及ItemWriter，而JobRepository记录Job执行信息。

图3-Spring Batch领域概念模型


# 说明




