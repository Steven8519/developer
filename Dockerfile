FROM openjdk:8u131-jre

Copy maven/developer-0.0.1-SNAPSHOT.jar webapp.jar

CMD ["java", "-Xmx50m", "-jar","webapp.jar"]
