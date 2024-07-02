FROM openjdk:21-jdk

COPY /target/medicalclinicproxy-0.0.1-SNAPSHOT.jar app/medicalclinicproxy-0.0.1-SNAPSHOT.jar

ENTRYPOINT ["java","-jar","app/medicalclinicproxy-0.0.1-SNAPSHOT.jar"]