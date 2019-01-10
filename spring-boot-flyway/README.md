# spring-boot-flyway

Spring Boot 整合 flyway。

# 说明

## 什么是Flyway

官网地址：http://flywaydb.org

Flyway是一款开源的数据库版本管理工具，它更倾向于规约优于配置的方式。

Flyway可以独立于应用实现管理并跟踪数据库变更，支持数据库版本自动升级，并且有一套默认的规约，不需要复杂的配置，Migrations可以写成SQL脚本，也可以写在Java代码中，不仅支持Command Line和Java API，还支持Build构建工具和Spring Boot等，同时在分布式环境下能够安全可靠地升级数据库，同时也支持失败恢复等。

## 为什么需要Flyway

在开发产品新特性过程中，难免会遇到需要更新数据库Schema的情况，比如：添加新表，添加新字段和约束等。那么如何快速地在其他开发者机器上同步？

并且如何在测试服务器上快速同步？

以及如何保证集成测试能够顺利执行并通过呢？


## Flyway存放路径说明

默认情况下是 `classth:db/migration`

使用`flyway.locations`进行修改

为了接下里的测试，在resources创建目录`db/migration`.

## Flyway sql脚本命令规则

对于Flyway的脚本`V<VERSION>__<NAME>.sql`

`VERSION`使用下划线进行命名的方式，比如：直接命名为1或者是1_1（这个就是代表1.1版本）;

`NAME`：对当前脚本的描述;

这里举例说明下：

* V3__insertBook.sql：这就代表version=3，描述为insertBook；
* V2_1__insertAuthor.sql：这就代表version=2.1，描述为insertAuthor；

>温馨提醒：
>1.这里的V字母要大写;
>2.上面中间是双杠。

## 测试

1. 按命名规则创建脚本，启动项目。
2. 可以查看testdb数据库中多了两张表。
3. book表和flyway记录表。