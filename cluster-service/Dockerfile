FROM openjdk:11
ADD ./target/cluster-service-0.0.1-SNAPSHOT.jar /usr/src/cluster-service-0.0.1-SNAPSHOT.jar
WORKDIR usr/src
EXPOSE 8095
ENTRYPOINT ["java","-jar", "cluster-service-0.0.1-SNAPSHOT.jar"]

