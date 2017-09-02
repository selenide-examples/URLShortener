FROM java:8-jre
MAINTAINER Roman S.A. <raskidan@ya.ru>
ADD ./target/url-shortener.jar /app/
CMD ["java", "-jar", "/app/url-shortener.jar"]
EXPOSE 8080