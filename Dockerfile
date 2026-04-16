FROM maven:3.9-eclipse-temurin-25 AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

FROM eclipse-temurin:25-jdk

WORKDIR /app

COPY --from=build app/target/*.war app.war

ENTRYPOINT ["java", "-jar", "app.war"]