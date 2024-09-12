FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app
COPY target/Login_Handler-0.0.1.jar Login_Handler-0.0.1.jar
EXPOSE 8080
CMD ["java", "-jar", "Login_Handler-0.0.1.jar"]