FROM maven:3.9.11-eclipse-temurin-21-noble AS build


WORKDIR /app
COPY . .
RUN mvn clean install

FROM eclipse-temurin:21-jre-jammy as run

WORKDIR /app

EXPOSE 8080

COPY --from=build /target/app-0.0.1-SNAPSHOT.jar /app.jar

ENTRYPOINT [ "java","-jar","app.jar" ]