FROM openjdk:8-jre-alpine

ADD server/target/*.jar app.jar

EXPOSE 8082

ENTRYPOINT ["java", "-jar", "/app.jar"]
