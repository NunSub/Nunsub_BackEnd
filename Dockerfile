FROM gradle:8.12.1-jdk21-alpine AS build
WORKDIR /home/gradle/project

COPY gradlew ./
RUN chmod +x gradlew

COPY gradle ./gradle/
COPY build.gradle settings.gradle ./
COPY src ./src

RUN ./gradlew clean build


FROM eclipse-temurin:21-jre-alpine
WORKDIR /app

COPY --from=build /home/gradle/project/build/libs/*.jar api.jar

EXPOSE 8080

ENTRYPOINT ["java", "-Dspring.profiles.active=default,credentials", "-Duser.timezone=Asia/Seoul", "-jar", "api.jar"]
