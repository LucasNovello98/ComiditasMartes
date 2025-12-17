FROM eclipse-temurin:21-jdk

WORKDIR /app

COPY . .

RUN chmod +x mvnw

EXPOSE 8080

CMD ["java", "-jar", "target/comidita-martes-0.0.1-SNAPSHOT.jar"]