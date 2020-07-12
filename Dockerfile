FROM openjdk:8-jdk-alpine
RUN addgroup -S spring && adduser -S spring -G spring
USER spring:spring
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]

#build> docker build -t springio/gs-spring-boot-docker .
#run> docker run -p 8080:8080 springio/gs-spring-boot-docker
#stop> docker container stop [CONTAINER ID]

#tmp container info> docker ps
#running container info> docker ps
#containers history> docker ps -a
