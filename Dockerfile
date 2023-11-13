# Stage 1: Build the application
FROM maven:3.8.4-openjdk-17-slim AS build
WORKDIR /app
COPY . .
RUN mvn clean package

# Stage 2: Create a smaller image with only the JAR file
FROM openjdk:17-slim
WORKDIR /app
COPY --from=build /app/target/PalindromeHuntChallenge-1.0-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
