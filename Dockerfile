FROM openjdk:8-jdk-alpine
COPY ./ ./
CMD ["java", "-jar", "target/navigator-0.0.1-SNAPSHOT.jar"]