FROM mcr.microsoft.com/java/jre:8-zulu-alpine

WORKDIR /opt/poster

COPY target/poster-2.2.4-SNAPSHOT.jar /opt/poster/app.jar

EXPOSE 8000

ENTRYPOINT ["java", "-jar", "app.jar"]