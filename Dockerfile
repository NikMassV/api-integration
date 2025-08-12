FROM gradle:8.14.3-jdk21 AS builder
WORKDIR /app
COPY . .
RUN ./gradlew build --no-daemon -x test

FROM amazoncorretto:21
WORKDIR /app
COPY --from=builder /app/build/libs/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]