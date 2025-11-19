FROM maven:3.9.11-eclipse-temurin-21-noble AS build


WORKDIR /app
COPY . .
RUN mvn clean package

FROM eclipse-temurin:21-jre-jammy AS run

WORKDIR /app

EXPOSE 8080

COPY --from=build /app/target/app-0.0.1-SNAPSHOT.jar app.jar

ENTRYPOINT [ "java","-jar","app.jar" ]