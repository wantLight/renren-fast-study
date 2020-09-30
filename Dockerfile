# 1.在服务器新建一个docker文件夹，将maven打包好的jar包和Dockerfile文件复制到服务器的docker文件夹下
# 2.docker build -t monitor .  (制作镜像)
# 3.docker run -d -p 7071:7071 monitor  （-p 是做端口映射）
#基础镜像，如果本地没有，会从远程仓库拉取。
FROM java:8
EXPOSE 7071

# 镜像制作人
MAINTAINER xyzzg/1364932323@qq.com


# 在容器中创建挂载点，可以多个
# 其效果是在主机 /var/lib/docker 目录下创建了一个临时文件，并链接到容器的/tmp
VOLUME ["/tmp"]

# 将jar包添加到容器中并更名为hotel.jar
ADD renren-fast.jar szsti-monitor.jar
# 运行jar包
RUN bash -c 'touch /szsti-monitor.jar'
ENTRYPOINT ["java","-jar","/szsti-monitor.jar"]
