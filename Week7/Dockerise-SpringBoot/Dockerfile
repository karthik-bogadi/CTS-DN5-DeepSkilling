FROM eclipse-temurin:21-jre-jammy
WORKDIR /app
COPY target/Dockerise-SpringBoot-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","app.jar"]
EXPOSE 8080