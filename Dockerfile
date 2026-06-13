
FROM maven:3.9.6-eclipse-temurin-21 AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

FROM eclipse-temurin:21-jre
WORKDIR /app
COPY --from=build /app/target/rate-cs-teaching-staff-0.0.1-SNAPSHOT.jar rate-cs-teaching-staff.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "rate-cs-teaching-staff.jar"]