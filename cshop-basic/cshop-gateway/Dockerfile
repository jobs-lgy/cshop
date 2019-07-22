FROM java:8
VOLUME /tmp
ADD target/cshop-gateway.jar app.jar

ENTRYPOINT [ "java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "/app.jar", "--spring.profiles.active=prod"]

EXPOSE 7777