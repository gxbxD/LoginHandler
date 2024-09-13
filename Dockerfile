FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app
COPY target/LoginHandler-0.0.1.jar LoginHandler-0.0.1.jar
EXPOSE 8080
CMD ["java", "-jar", "LoginHandler-0.0.1.jar"]

FROM nginx:alpine

# Instala o Certbot
RUN apk add --no-cache certbot certbot-nginx

COPY nginx.conf /etc/nginx/conf.d/default.conf

# Comando para iniciar o Nginx
CMD ["nginx", "-g", "daemon off;"]
