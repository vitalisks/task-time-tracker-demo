FROM gradle:jdk8 as build
#default database config H2
ARG DB_TYPE=h2
WORKDIR /app 
COPY ./build.gradle ./
COPY ./config/application.properties.${DB_TYPE} ./application.properties.db
COPY ./src ./src/
RUN gradle test && \
    cp ./application.properties.db ./src/main/resources/application.properties && \
    gradle build -x test

FROM openjdk:8-jdk-alpine
COPY --from=build /app/build/libs/*.jar /app/app.jar
ENTRYPOINT ["java","-jar","/app/app.jar"]
EXPOSE 8080
