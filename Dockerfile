FROM openjdk:11
ARG JAR_FILE=target/farm-app.jar
COPY ${JAR_FILE} app.jar

ENTRYPOINT java -jar /app.jar

#ENTRYPOINT java -jar /app.jar --spring.cloud.config.uri=${CONFIG_URI} --spring.cloud.config.label=${CONFIG_NAME} --spring.profiles.active=${CONFIG_ENV}
