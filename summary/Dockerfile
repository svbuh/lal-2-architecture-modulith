FROM openjdk:21-jdk-slim


WORKDIR /app

COPY target/summary-1.0-SNAPSHOT.jar .

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "summary-1.0-SNAPSHOT.jar"]
