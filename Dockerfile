FROM openjdk:12.0.2
ENV APP_FILE developer-0.0.1-SNAPSHOT.jar
ENV APP_HOME /usr/app

EXPOSE 8080

COPY target/*.jar $APP_HOME/
WORKDIR $APP_HOME
ENTRYPOINT ["sh", "-c"]
CMD ["exec java -jar $APP_FILE"]