FROM java:8

ARG JAR_FILE
ADD ${JAR_FILE} /app.jar

ENTRYPOINT [ "java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "/app.jar", "--spring.profiles.active=prod"]

EXPOSE 7000