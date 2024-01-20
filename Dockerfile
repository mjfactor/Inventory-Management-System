FROM openjdk:17-jdk-alpine
WORKDIR /usr/src/app
COPY ./out/artifacts/invetory_system_jar/invetory-system.jar /usr/src/app
CMD ["java", "-jar", "invetory-system.jar"]