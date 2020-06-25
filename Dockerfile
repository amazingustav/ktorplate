FROM openjdk:11-jdk-slim

WORKDIR /srv

COPY application/build/libs/app.jar .

CMD java -jar app.jar
