# backend/Dockerfile

FROM openjdk:17-jdk-slim
WORKDIR /app

# Spring Boot JAR 복사
COPY build/libs/MyNutrition-0.0.1-SNAPSHOT.jaryNutrition.jar app.jar

# 내부 포트 오픈
EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]