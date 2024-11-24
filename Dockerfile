# Use a base image with Java installed
FROM openjdk:17

# Set the working directory
WORKDIR /apis

# Copy the JAR file into the container
COPY target/webapi-0.0.1-SNAPSHOT.jar webapi.jar

# Expose the port your app runs on
EXPOSE 8080

# Command to run the application
ENTRYPOINT ["java", "-jar", "webapi.jar"]