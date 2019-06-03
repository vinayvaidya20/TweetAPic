FROM openjdk:8-jdk-alpine
VOLUME /tmp
ARG JAR_FILE=./target/tweetAPic-0.0.1-SNAPSHOT.jar
ADD ${JAR_FILE} tweet.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/tweet.jar"]