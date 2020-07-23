FROM openjdk:11
ADD ./target/resource-service-0.0.1-SNAPSHOT.jar /usr/src/resource-service-0.0.1-SNAPSHOT.jar
WORKDIR usr/src
EXPOSE 8097
ENTRYPOINT ["java","-jar", "resource-service-0.0.1-SNAPSHOT.jar"]

