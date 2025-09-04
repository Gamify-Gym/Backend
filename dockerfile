FROM ubuntu:latest AS build

RUN apt-get update
RUN apt-get install openjdk-21-jdk maven -y

COPY . .

RUN mvn clean install

FROM openjdk:21-jdk-slim

EXPOSE $APP_PORT

COPY --from=build /target/app-0.0.1-SNAPSHOT.jar /app.jar

ENTRYPOINT [ "java","-jar","app.jar" ]