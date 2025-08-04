FROM eclipse-temurin:17-jdk-alpine
COPY target/krypto_mine.jar krypto_mine.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]