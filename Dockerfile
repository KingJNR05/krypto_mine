#FROM eclipse-temurin:17-jdk-alpine
#COPY target/krypto_mine.jar krypto_mine.jar
#EXPOSE 8080
#ENTRYPOINT ["java", "-jar", "app.jar"]
#


# Stage 1: Build the Java app using Maven
FROM eclipse-temurin:17-jdk-alpine as builder

WORKDIR /app

# Copy your source code and Maven wrapper
COPY . .

# Build the jar (skip tests if needed)
RUN ./mvnw clean package -DskipTests

# Stage 2: Run the built jar
FROM eclipse-temurin:17-jdk-alpine

WORKDIR /app

# Copy the jar from the builder stage
COPY --from=builder /app/target/krypto_mine.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]