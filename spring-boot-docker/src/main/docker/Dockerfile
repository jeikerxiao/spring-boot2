# 基镜像
FROM java:8
# 挂载文件夹
VOLUME /tmp
# 复制文件重命名
ADD spring-boot-docker-0.0.1.jar app.jar
# 暴露接口
EXPOSE 8080
# 运行命令
ENTRYPOINT ["java", "-jar", "app.jar"]
