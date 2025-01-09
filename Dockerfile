# Dockerfile for Backend
FROM openjdk:21-jdk-slim

# Set the working directory
WORKDIR /app

# Copy the JAR file to the container
COPY build/libs/garbagecollection-0.0.1-SNAPSHOT.jar app.jar

# Expose the backend port
EXPOSE 8080

# Run the backend service
CMD ["java", "-jar", "app.jar"]
