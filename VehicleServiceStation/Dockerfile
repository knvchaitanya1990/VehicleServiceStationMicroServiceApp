# Use a base image with Java and Alpine Linux
FROM eclipse-temurin:17-jdk-alpine

# Set the working directory inside the container
WORKDIR /app

# Copy the application JAR file into the container
COPY target/VehicleServiceStation-0.0.1-SNAPSHOT.jar .

# Expose the port that the application listens on
EXPOSE 9005

# Set the command to run the application when the container starts
CMD ["java", "-jar", "VehicleServiceStation-0.0.1-SNAPSHOT.jar"]
