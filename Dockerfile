FROM openjdk:11.0-jre
COPY target/swipeschat-0.0.1-RELEASE.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
