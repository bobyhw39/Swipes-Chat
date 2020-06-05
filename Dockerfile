FROM openjdk:11.0-jre
COPY final.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
