FROM openjdk:15-slim

ADD ./ /api
WORKDIR /api
RUN sh gradlew build
CMD java -jar build/libs/api-0.0.1-SNAPSHOT.jar