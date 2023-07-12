FROM eclipse-temurin:17-jdk-alpine

VOLUME /tmp

COPY target/*.jar moviebooking-gitlab.jar

EXPOSE 8080

ENTRYPOINT ["java","-jar","moviebooking-gitlab.jar"]

