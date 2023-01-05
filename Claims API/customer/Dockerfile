FROM maven:3.6.0-jdk-11 AS build


# Copy folder in docker
WORKDIR /opt/app

COPY ./ /opt/app
RUN mvn clean install -DskipTests


# Run spring boot in Docker
FROM openjdk:11

COPY --from=build /opt/app/target/*.jar customer.jar

ENV PORT 9002
EXPOSE $PORT

ENTRYPOINT ["java","-jar","-Xmx1024M","-Dserver.port=${PORT}","customer.jar"]
