FROM eclipse-temurin:25-jdk

WORKDIR /app

COPY target/*.war app.war

ENTRYPOINT ["java", "-jar", "app.war"]