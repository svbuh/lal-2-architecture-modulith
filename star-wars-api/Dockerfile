FROM openjdk:21-jdk-slim


WORKDIR /app

COPY target/star-wars-api-1.0-SNAPSHOT.jar .

EXPOSE 8082

ENTRYPOINT ["java", "-jar", "star-wars-api-1.0-SNAPSHOT.jar"]
