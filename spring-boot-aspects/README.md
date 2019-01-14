# spring-boot-aspects

Spring Boot 整合 AOP。

# 说明

## 什么是 AOP
　　
aop全称Aspect Oriented Programming，面向切面，AOP主要实现的目的是针对业务处理过程中的切面进行提取，它所面对的是处理过程中的某个步骤或阶段，以获得逻辑过程中各部分之间低耦合性的隔离效果。其与设计模式完成的任务差不多，是提供另一种角度来思考程序的结构，来弥补面向对象编程的不足。

通俗点讲就是提供一个为一个业务实现提供切面注入的机制，通过这种方式，在业务运行中将定义好的切面通过切入点绑定到业务中，以实现将一些特殊的逻辑绑定到此业务中。

比如，若是需要一个记录日志的功能，首先想到的是在方法中通过log4j或其他框架来进行记录日志，但写下来发现一个问题，在整个业务中其实核心的业务代码并没有多少，都是一些记录日志或其他辅助性的一些代码。而且很多业务有需要相同的功能，比如都需要记录日志，这时候又需要将这些记录日志的功能复制一遍，即使是封装成框架，也是需要调用之类的。在此处使用复杂的设计模式又得不偿失。

所以就需要面向切面出场了。

## AOP概念名词

先介绍一些aop的名词，其实这些名词对使用aop没什么影响，但为了更好的理解最好知道一些
   
* 切面（Aspect）：一个关注点的模块化，这个关注点可能会横切多个对象。事务管理是J2EE应用中一个关于横切关注点的很好的例子。在Spring AOP中，切面可以使用基于模式或者基于@Aspect注解的方式来实现。
   
* 连接点（Joinpoint）：在程序执行过程中某个特定的点，比如某方法调用的时候或者处理异常的时候。在Spring AOP中，一个连接点总是表示一个方法的执行。
   
* 通知（Advice）：在切面的某个特定的连接点上执行的动作。其中包括了“around”、“before”和“after”等不同类型的通知（通知的类型将在后面部分进行讨论）。许多AOP框架（包括Spring）都是以拦截器做通知模型，并维护一个以连接点为中心的拦截器链。
   
* 切入点（Pointcut）：匹配连接点的断言。通知和一个切入点表达式关联，并在满足这个切入点的连接点上运行（例如，当执行某个特定名称的方法时）。切入点表达式如何和连接点匹配是AOP的核心：Spring缺省使用AspectJ切入点语法。
   
* 引入（Introduction）：用来给一个类型声明额外的方法或属性（也被称为连接类型声明（inter-type declaration））。Spring允许引入新的接口（以及一个对应的实现）到任何被代理的对象。例如，你可以使用引入来使一个bean实现IsModified接口，以便简化缓存机制。
   
* 目标对象（Target Object）：被一个或者多个切面所通知的对象。也被称做被通知（advised）对象。既然Spring AOP是通过运行时代理实现的，这个对象永远是一个被代理（proxied）对象。
   
* AOP代理（AOP Proxy）：AOP框架创建的对象，用来实现切面契约（例如通知方法执行等等）。在Spring中，AOP代理可以是JDK动态代理或者CGLIB代理。
   
* 织入（Weaving）：把切面连接到其它的应用程序类型或者对象上，并创建一个被通知的对象。这些可以在编译时（例如使用AspectJ编译器），类加载时和运行时完成。Spring和其他纯Java AOP框架一样，在运行时完成织入。
   
其中重要的名词有：切面，切入点

## 切入点表达式 

定义切入点的时候需要一个包含名字和任意参数的签名，还有一个切入点表达式，如

```java
execution(public * com.example.aop...(..))
```
  
切入点表达式的格式：execution([可见性]返回类型[声明类型].方法名(参数)[异常]) 

其中[]内的是可选的，其它的还支持通配符的使用： 

1. `*`：匹配所有字符 
2. `..`：一般用于匹配多个包，多个参数 
3. `+`：表示类及其子类 
4. 运算符有：`&&`,`||`,`!`
   

切入点表达式关键词用例： 

1）execution：用于匹配子表达式。 

```java
// 匹配com.cjm.model包及其子包中所有类中的所有方法，返回类型任意，方法参数任意 
@Pointcut(“execution(* com.cjm.model...(..))”) 
public void before(){}
```

   
2）within：用于匹配连接点所在的Java类或者包。 

```java
// 匹配Person类中的所有方法 
@Pointcut(“within(com.cjm.model.Person)”) 
public void before(){} 

//匹配com.cjm包及其子包中所有类中的所有方法 
@Pointcut(“within(com.cjm..*)”) 
public void before(){}
```

3） this：用于向通知方法中传入代理对象的引用。 是指定类型的实例。

```java
@Before(“before() && this(proxy)”) 
public void beforeAdvide(JoinPoint point, Object proxy){ 
// 处理逻辑 
}
```
 
4）target：用于向通知方法中传入目标对象的引用。 是指定类型的实例。

```java
@Before(“before() && target(target) 
public void beforeAdvide(JoinPoint point, Object proxy){ 
// 处理逻辑 
}
```

5）args：用于将参数传入到通知方法中。 

```java
@Before(“before() && args(age,username)”) 
public void beforeAdvide(JoinPoint point, int age, String username){ 
// 处理逻辑 
}
```

6）@within ：用于匹配在类一级使用了参数确定的注解的类，其所有方法都将被匹配。 

```java
@Pointcut(“@within(com.cjm.annotation.AdviceAnnotation)”) 
// 所有被@AdviceAnnotation标注的类都将匹配 
public void before(){}
```

7）@target ：和@within的功能类似，但必须要指定注解接口的保留策略为RUNTIME。 

```java
@Pointcut(“@target(com.cjm.annotation.AdviceAnnotation)”) 
public void before(){}
```
   
8）@args ：传入连接点的对象对应的Java类必须被@args指定的Annotation注解标注。 

```java
@Before(“@args(com.cjm.annotation.AdviceAnnotation)”) 
public void beforeAdvide(JoinPoint point){ 
// 处理逻辑 
}
```

9）@annotation ：匹配连接点被它参数指定的Annotation注解的方法。也就是说，所有被指定注解标注的方法都将匹配。 

```java
@Pointcut(“@annotation(com.cjm.annotation.AdviceAnnotation)”) 
public void before(){}
```

10）bean：通过受管Bean的名字来限定连接点所在的Bean。该关键词是Spring2.5新增的。 

```java
@Pointcut(“bean(person)”) 
public void before(){}
```

