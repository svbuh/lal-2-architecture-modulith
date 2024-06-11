FROM openjdk:21-jdk-slim

WORKDIR /app

COPY target/database-1.0-SNAPSHOT.jar .

EXPOSE 9090

ENTRYPOINT ["java", "-jar", "database-1.0-SNAPSHOT.jar"]
