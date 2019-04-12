# spring-boot-shell

Spring Boot 创建 Spring Shell 应用.

Java 在命令行应用的开发中也有一席之地。

在很多情况下，相对于图形用户界面来说，命令行界面响应速度快，所占用的系统资源少。

在与用户进行交互的场景比较单一时，命令行界面是更好的选择。命令行界面有其固定的交互模式。

通常是由用户输入一系列的参数，在执行之后把相应的结果在控制台输出。命令行应用通常需要处理输入参数的传递和验证、输出结果的格式化等任务。

Spring Shell 可以帮助简化这些常见的任务，让开发人员专注于实现应用的业务逻辑。

# pom

添加依赖

```xml
<dependency>
	<groupId>org.springframework.shell</groupId>
	<artifactId>spring-shell-starter</artifactId>
	<version>2.0.0.RELEASE</version>
</dependency>
```

# 项目代码

```java
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
 
@ShellComponent
public class GreetingApp {
 
  @ShellMethod("Say hi")
  public String sayHi(String name) {
    return String.format("Hi %s", name);
  }
}
```


## 运行测试用例

运行测试

```
say-hi Alex
```


内置命令
Spring Shell 提供了很多内置的命令，如下所示。

* 运行 help 命令可以列出来应用中的所有命令和对应的描述信息。
* 运行 clear 命令可以进行清屏操作。
* 运行 exit 命令可以退出命令行应用。
* 运行 script 命令可以执行一个文件中包含的所有命令。

如果不需要某个内置命令，可以通过把上下文环境中的属性 spring.shell.command.<command>.enabled 的值设为 false 来禁用。如果希望禁用全部的内置命令，可以把 spring-shell-standard-commands 从 Maven 依赖中排除。

排除内置命令对应的 Maven 依赖

```java
<dependency>
  <groupId>org.springframework.shell</groupId>
  <artifactId>spring-shell-starter</artifactId>
  <version>2.0.0.M2</version>
  <exclusions>
    <exclusion>
      <groupId>org.springframework.shell</groupId>
      <artifactId>spring-shell-standard-commands</artifactId>
    </exclusion>
  </exclusion>
</dependency>
```
