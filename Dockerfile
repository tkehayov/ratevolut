FROM maven:3.9.6-eclipse-temurin-21-alpine AS builder
WORKDIR /app
COPY pom.xml .
COPY src ./src
ARG SPRING_PROFILES_ACTIVE
RUN mvn clean package -DskipTests -Dspring.profiles.active=${SPRING_PROFILES_ACTIVE}


FROM eclipse-temurin:21-jre-alpine
WORKDIR /app

RUN addgroup -S appgroup && adduser -S appuser -G appgroup

COPY --from=builder /app/target/*.jar app.jar

RUN chown -R appuser:appgroup /app
USER appuser

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]