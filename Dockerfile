FROM openjdk:17-jdk-slim-buster

EXPOSE 8080

RUN mkdir -p /usr/app
WORKDIR /usr/app

COPY 'build/libs/payment-service-0.0.1-SNAPSHOT.jar' /usr/app/payment-service.jar

ENV JAVA_OPTS="-Xmx32m"
ENTRYPOINT java $JAVA_OPTS -jar payment-service.jar
