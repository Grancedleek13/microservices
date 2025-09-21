FROM eclipse-temurin:11-jdk AS build
WORKDIR /app
COPY ../../gradle /app/gradle
COPY ../../gradlew ../../settings.gradle.kts ../../build.gradle.kts /app/
COPY ../../service1-sender /app/service1-sender
COPY ../../service2-core /app/service2-core
COPY ../../service3-admin /app/service3-admin
RUN chmod +x /app/gradlew && /app/gradlew :service1-sender:bootJar --no-daemon

FROM eclipse-temurin:11-jre
WORKDIR /opt/app
COPY --from=build /app/service1-sender/build/libs/*.jar app.jar
EXPOSE 8081
ENTRYPOINT ["java","-jar","/opt/app/app.jar"]
