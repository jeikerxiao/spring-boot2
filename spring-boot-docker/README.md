# spring-boot-docker

使用Docker 部署Spring Boot项目。


下面介绍使用 `docker-maven-plugin` 的两种配置方式。

1. pom文件配置
2. Dockerfile配置


## Docker 开启远程访问

注意服务器上Docker 需要开启远程访问

在`/usr/lib/systemd/system/docker.service`，配置远程访问。主要是在`[Service]`这个部分，加上下面两个参数：

进入配置文件目录：

```
cd /usr/lib/systemd/system
```
编辑配置文件：

```
vim docker.service
```

将原来的配置：

```
ExecStart=/usr/bin/dockerd
```

修改成如下配置：（增加两个参数，原样增加，不要自作聪明改成你自己IP哦）

```
ExecStart=/usr/bin/dockerd -H tcp://0.0.0.0:2375 -H unix://var/run/docker.sock
```

## 重启使配置生效

docker重新读取配置文件，重新启动docker服务。

重新读取配置文件

```bash
systemctl daemon-reload
```

重新启动服务

```bash
systemctl restart docker
```

## 1. pom文件配置

pom.xml

```xml
<plugin>
    <groupId>com.spotify</groupId>
    <artifactId>docker-maven-plugin</artifactId>
    <version>1.2.0</version>
    <executions>
        <!--执行 mvn package 时 自动 执行 mvn docker:build-->
        <execution>
            <id>build-image</id>
            <phase>package</phase>
            <goals>
                <goal>build</goal>
            </goals>
        </execution>
    </executions>
    <configuration>
        <!--推送到私有仓库-->
        <registryUrl>http://192.168.234.97:5000</registryUrl>
        <dockerHost>http://192.168.234.97:2375</dockerHost>
        <imageName>192.168.234.97:5000/jeiker/${project.artifactId}</imageName>
        <!--本地-->
        <!--<imageName>jeiker/spring-boot-docker</imageName>-->
        <imageTags>
            <imageTag>${project.version}</imageTag>
            <!--可以指定多个标签-->
            <!--<imageTag>latest</imageTag>-->
        </imageTags>
        <!--覆盖已存在的标签 镜像-->
        <forceTags>true</forceTags>
        <!--使用pom 配置-->
        <baseImage>java:8</baseImage>
        <entryPoint>["nohup","java","-jar","/${project.build.finalName}.jar"]</entryPoint>
        <!--使用pom 配置 END-->
        <!--使用 Dockerfile-->
        <!--<dockerDirectory>${project.basedir}/src/main/docker</dockerDirectory>-->
        <!--使用 Dockerfile  END-->
        <resources>
            <resource>
                <targetPath>/</targetPath>
                <directory>${project.build.directory}</directory>
                <include>${project.build.finalName}.jar</include>
            </resource>
        </resources>
    </configuration>
</plugin>
```

## 2. Dockerfile配置

### pom.xml

```xml
<plugin>
    <groupId>com.spotify</groupId>
    <artifactId>docker-maven-plugin</artifactId>
    <version>1.2.0</version>
    <executions>
        <!--执行 mvn package 时 自动 执行 mvn docker:build-->
        <execution>
            <id>build-image</id>
            <phase>package</phase>
            <goals>
                <goal>build</goal>
            </goals>
        </execution>
    </executions>
    <configuration>
        <!--推送到私有仓库-->
        <registryUrl>http://192.168.234.97:5000</registryUrl>
        <dockerHost>http://192.168.234.97:2375</dockerHost>
        <imageName>192.168.234.97:5000/jeiker/${project.artifactId}</imageName>
        <!--本地-->
        <!--<imageName>jeiker/spring-boot-docker</imageName>-->
        <imageTags>
            <imageTag>${project.version}</imageTag>
            <!--可以指定多个标签-->
            <!--<imageTag>latest</imageTag>-->
        </imageTags>
        <!--覆盖已存在的标签 镜像-->
        <forceTags>true</forceTags>
        <!--使用pom 配置-->
        <!--<baseImage>java:8</baseImage>-->
        <!--<entryPoint>["nohup","java","-jar","/${project.build.finalName}.jar"]</entryPoint>-->
        <!--使用pom 配置 END-->
        <!--使用 Dockerfile-->
        <dockerDirectory>${project.basedir}/src/main/docker</dockerDirectory>
        <!--使用 Dockerfile  END-->
        <resources>
            <resource>
                <targetPath>/</targetPath>
                <directory>${project.build.directory}</directory>
                <include>${project.build.finalName}.jar</include>
            </resource>
        </resources>
    </configuration>
</plugin>
```

### Dockerfile

```bash

# 基镜像
FROM java:8
# 定义匿名卷
VOLUME /tmp
# 复制文件重命名
ADD spring-boot-docker-0.0.1.jar app.jar
# 暴露端口
EXPOSE 8080
# 入口点
ENTRYPOINT ["java", "-jar", "app.jar"]
```


## 3.构建



使用1或2的配置方式,构建并发布镜像到远程仓库。

这里的远程仓库为 ：192.168.234.97:5000

远程docker api接口为：192.168.234.97:2375

maven构建

```bash
mvn package
```

可以看到镜像执行过程

```bash
[INFO] Building image 192.168.234.97:5000/jeiker/spring-boot-docker
Step 1/5 : FROM java:8

 ---> d23bdf5b1b1b
Step 2/5 : VOLUME /tmp

 ---> Using cache
 ---> c63596126104
Step 3/5 : ADD spring-boot-docker-0.0.1.jar app.jar

 ---> 4e3e2ffc69c8
Step 4/5 : EXPOSE 8080

 ---> Running in 51ef7586c7b7
Removing intermediate container 51ef7586c7b7
 ---> 41b69f8b149a
Step 5/5 : ENTRYPOINT ["java", "-jar", "app.jar"]

 ---> Running in 3e836cceedb4
Removing intermediate container 3e836cceedb4
 ---> fa75ad2747f5
ProgressMessage{id=null, status=null, stream=null, error=null, progress=null, progressDetail=null}
Successfully built fa75ad2747f5
Successfully tagged 192.168.234.97:5000/jeiker/spring-boot-docker:latest
[INFO] Built 192.168.234.97:5000/jeiker/spring-boot-docker
[INFO] Tagging 192.168.234.97:5000/jeiker/spring-boot-docker with 0.0.1
```

## 4. 启动容器


进入服务器 192.168.234.97 

查看刚生成的镜像：

```bash
docker images
```
可以看到上传上来的镜像：

```                                                                                                                 
REPOSITORY                                          TAG                 IMAGE ID            CREATED              SIZE
192.168.234.97:5000/jeiker/spring-boot-docker       0.0.1               fa75ad2747f5        About a minute ago   661MB
```

启动镜像

```
docker run -d -p 8881:8080  192.168.234.97:5000/jeiker/spring-boot-docker:0.0.1
```

查看容器

```bash
docker ps
```


访问

http://localhost:8881/

```json
{
	message: "Hi!"
}
```

