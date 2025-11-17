# Use official Java 17 runtime
FROM eclipse-temurin:17-jdk AS build

WORKDIR /app

# Copy project files
COPY . .

# Build the application without tests
RUN ./mvnw -q -DskipTests package

# ==========================
# PRODUCTION IMAGE
# ==========================
FROM eclipse-temurin:17-jre

WORKDIR /app

# Copy the built JAR from the build stage
COPY --from=build /app/target/*.jar app.jar

# Expose application port
EXPOSE 8080

# Run the Spring Boot application
ENTRYPOINT ["java", "-jar", "app.jar"]
