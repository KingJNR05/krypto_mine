FROM eclipse-temurin:17-jdk-alpine
COPY target/krypto_mine.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]