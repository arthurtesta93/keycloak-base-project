FROM gradle:8.3.0-jdk17 AS builder
WORKDIR /app
ENV KEYCLOAK_PORT=8080
ENV KEYCLOAK_CLIENT_ID=oauth
ENV KEYCLOAK_CLIENT_SECRET=04bfUatIDO6ipwg1TF2mTzHrX8UZD02Z
ENV AUTH_SERVER_PORT=8090
COPY build.gradle settings.gradle /app/
RUN gradle dependencies --stacktrace
COPY . .
RUN gradle clean build

FROM openjdk:17
COPY --from=builder /app/build/libs/*.jar app.jar
EXPOSE 8090
ENTRYPOINT ["java","-jar","app.jar"]