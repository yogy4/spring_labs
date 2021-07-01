# For Java 8, try this
# FROM openjdk:8-jdk-alpine
# untuk docke compose
FROM maven:3.5-jdk-8
# For Java 11, try this
# FROM adoptopenjdk/openjdk11:alpine-jre

# Refer to Maven build -> finalName
# ARG JAR_FILE=target/labs-0.0.1-SNAPSHOT.jar

# # cd /opt/app
# WORKDIR /opt/app

# # cp target/spring-boot-web.jar /opt/app/app.jar
# COPY ${JAR_FILE} app.jar

# # java -jar /opt/app/app.jar
# ENTRYPOINT ["java","-jar","app.jar"]

# EXPOSE 4000
# set baru
# COPY ./target/labs-0.0.1-SNAPSHOT.jar labs-0.0.1-SNAPSHOT.jar
# COPY ./src/main/resources/META-INF/resources/jsp /jsp
# CMD ["java","-jar","labs-0.0.1-SNAPSHOT.jar"]
# ENTRYPOINT [ "java","-jar","labs-0.0.1-SNAPSHOT.jar" ]