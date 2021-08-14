FROM mcr.microsoft.com/java/jre:8-zulu-alpine

# 需要和 pom.xml、Makefile 同步
ENV VERSION=2.5

WORKDIR /opt/poster

COPY target/poster-$VERSION.jar /opt/poster/poster.jar
COPY application.properties /opt/poster/application.properties

# 创建默认资源文件夹
RUN mkdir downloads fonts templates

# 复制默认字体文件到容器
COPY src/main/resources/fonts/pingfangsr.ttf /opt/poster/fonts/pingfangsr.ttf

# 注意和 application.properties 的 server.port 保持一致
EXPOSE 8000

ENTRYPOINT ["java", "-jar", "poster.jar"]