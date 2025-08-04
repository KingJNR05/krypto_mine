# Stage 1: Build the app using Maven inside the container
FROM eclipse-temurin:17-jdk-alpine as builder

WORKDIR /app

# Copy everything into the container
COPY . .

# Give execute permission to the Maven wrapper
RUN chmod +x mvnw

# Build the jar file (skip tests to speed it up)
RUN ./mvnw clean package -DskipTests

# Stage 2: Run the app
FROM eclipse-temurin:17-jdk-alpine

WORKDIR /app

# Copy the jar from the builder stage
COPY --from=builder /app/target/krypto_mine1.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
