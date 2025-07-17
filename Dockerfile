FROM eclipse-temurin:21-jre
COPY target/shopping-simulation-app-1.0-SNAPSHOT.jar /app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]
