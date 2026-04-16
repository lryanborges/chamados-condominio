FROM maven:3.9-eclipse-temurin-25 AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

FROM eclipse-temurin:25-jdk

WORKDIR /app

COPY --from=build app/target/*.war app.war

EXPOSE 8080
ENTRYPOINT ["sh", "-c", "java -Xmx300m -Dserver.tomcat.additional-tld-skip-patterns=*.jar -Dserver.port=${PORT:-10000} -jar app.war"]