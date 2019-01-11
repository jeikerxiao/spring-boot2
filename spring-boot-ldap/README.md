# spring-boot-ldap

Spring Boot 整合 LDAP。

# 说明

## LDAP简介

目录是一个为查询、浏览和搜索而优化的专业分布式数据库，它呈树状结构组织数据，就好象Linux/Unix系统中的文件目录一样。目录数据库和关系数据库不同，它有优异的读性能，但写性能差，并且没有事务处理、回滚等复杂功能，不适于存储修改频繁的数据。

目录服务是由目录数据库和一套访问协议组成的系统。类似以下的信息适合储存在目录中：

* 企业员工信息，如姓名、电话、邮箱等；
* 公用证书和安全密钥；
* 公司的物理设备信息，如服务器，它的IP地址、存放位置、厂商、购买时间等；

LDAP（Lightweight Directory Access Protocol）是基于目录服务的轻量目录访问协议，它是基于X.500标准而发展起来的，但是它更加简单，并且可以根据需要定制。与X.500不同，LDAP支持TCP/IP，这对访问Internet是必须的。

LDAP具有如下特点：

* LDAP的结构用树来表示，而不是用表格；
* LDAP可以很快地得到查询结果，不过在写方面，效率比较差；
* LDAP提供了静态数据的快速查询方式；
* 基于Client/Server模型，Server 用于存储数据，Client提供操作目录信息树的工具；
* LDAP是一种基于X.500协议的互联网开放标准，LDAP协议是跨平台的互联网协议。

LDAP目录中的信息是按照树型结构进行组织的，具体信息存储在条目(Entry)的数据结构中。

条目相当于关系数据库中表的记录；条目是具有唯一标志名称DN （Distinguished Name）的属性（Attribute），DN是用来引用条目的，DN相当于关系数据库表中的关键字（Primary Key）。

属性（Attribute）由类型（Type）和一个或多个值（Values）组成，相当于关系数据库中的字段（Field）由字段名和数据类型组成，只是为了方便检索的需要，LDAP中的Type可以有多个Value，而不是关系数据库中为降低数据的冗余性要求实现的各个域必须是不相关的。

LDAP中条目的组织一般按照地理位置和组织关系进行组织，非常的直观。LDAP把数据存放在文件中，为提高效率可以使用基于索引的文件数据库，而不是关系数据库。

LDAP的信息是以树型结构存储（如下图所示）的，在树根一般定义国家(c=CN)或域名(dc=com)，在其下则往往定义一个或多个组织 (Organization)(o=Acme)或组织单元(Organizational units) (ou=People)。一个组织单元可能包含诸如所有雇员、大楼内的所有设备等信息。

此外，LDAP支持对条目能够和必须支持哪些属性进行控制，这是有一个特殊的称为对象类别(objectClass)的属性来实现的。该属性的值决定了该条目必须遵循的一些规则，其规定了该条目能够及至少应该包含哪些属性。例如：inetOrgPerson对象类需要支持sn(surname)和cn(common name)属性，但也可以包含可选的如邮件，电话号码等属性。

## LDAP常见简称

|简称|全称|用途
|:--|:--|:--
|o	|organizaiton|组织/公司
|ou	|Organizaiton Unit|组织单元
|c	|Country	|国家
|dc	|Domain Component|域名
|sn	|Suer Name	|真实名称
|cn	|Common Name|常用名称
|dn	|Distiguished Name|唯一标识名
|uid|User ID|用户标识


## AD域与LDAP的区别

Windows AD(Active Directory)域应该是LDAP的一个应用实例，而不应该是LDAP本身。

Windows AD域的用户、权限管理应该是微软公司使用LDAP存储了一些数据来解决域控这个具体问题，AD域提供了相关的用户接口，我们可以把AD域当做微软定制的LDAP服务器。

Active Directory先实现一个LDAP服务器，然后自己先用这个LDAP服务器实现了自己的一个具体应用。

# 代码

## pom

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-ldap</artifactId>
</dependency>

<dependency>
    <groupId>com.unboundid</groupId>
    <artifactId>unboundid-ldapsdk</artifactId>
    <scope>test</scope>
</dependency>
```

`spring-boot-starter-data-ldap`是Spring Boot封装的对LDAP自动化配置的实现，它是基于spring-data-ldap来对LDAP服务端进行具体操作的。

`unboundid-ldapsdk`主要是为了在这里使用嵌入式的LDAP服务端来进行测试操作，所以scope设置为了test，实际应用中，我们通常会连接真实的、独立部署的LDAP服务器，所以不需要此项依赖。

## 数据文件

在`src/test/resources`目录下创建`ldap-server.ldif`文件，用来存储LDAP服务端的基础数据，以备后面的程序访问之用。


```properties
dn: dc=didispace,dc=com
objectClass: top
objectClass: domain

dn: ou=people,dc=didispace,dc=com
objectclass: top
objectclass: organizationalUnit
ou: people

dn: uid=ben,ou=people,dc=didispace,dc=com
objectclass: top
objectclass: person
objectclass: organizationalPerson
objectclass: inetOrgPerson
cn: didi
sn: zhaiyongchao
uid: didi
userPassword: {SHA}nFCebWjxfaLbHHG1Qk5UU4trbvQ=
```


这里创建了一个基础用户，真实姓名为zhaiyongchao,常用名didi，在后面的程序中，我们会来读取这些信息。更多内容解释大家可以深入学习LDAP来理解，这里不做过多的讲解。

