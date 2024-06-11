FROM openjdk:21-jdk-slim


WORKDIR /app

COPY target/harry-potter-api-1.0-SNAPSHOT.jar .

EXPOSE 8081

ENTRYPOINT ["java", "-jar", "harry-potter-api-1.0-SNAPSHOT.jar"]
