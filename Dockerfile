FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app
COPY target/LoginHandler-0.0.1.jar LoginHandler-0.0.1.jar
EXPOSE 8080
CMD ["java", "-jar", "LoginHandler-0.0.1.jar"]