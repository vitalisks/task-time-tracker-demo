FROM gradle:jdk8 as build
WORKDIR /app
COPY ./build.gradle ./
COPY ./config/application.properties.pg ./
COPY ./src ./src/
RUN gradle test && \
    cp ./application.properties.pg ./src/main/resources/application.properties && \
    gradle build -x test

FROM openjdk:8-jdk-alpine
COPY --from=build /app/build/libs/*.jar /app/app.jar
ENTRYPOINT ["java","-jar","/app/app.jar"]
EXPOSE 8080
