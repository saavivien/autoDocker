FROM openjdk:20
ADD target/auto-0.0.1-SNAPSHOT.jar auto-docker.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "auto-docker.jar"]
