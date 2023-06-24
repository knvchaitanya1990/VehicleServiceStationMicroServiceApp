# Use a base image with Java and Alpine Linux
FROM eclipse-temurin:17-jdk-alpine

# label for the image
LABEL Description="PoliceVerification"

# Set the working directory inside the container
WORKDIR /app

# Copy the application JAR file into the container
COPY target/PoliceVerification-0.0.1.jar .

# Expose the port that the application listens on
EXPOSE 9000

# Set the command to run the application when the container starts
CMD ["java", "-jar", "PoliceVerification-0.0.1.jar"]
