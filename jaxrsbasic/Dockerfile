FROM maven:3.6.3-jdk-11 AS build
WORKDIR /app
COPY pom.xml .
RUN mvn dependency:go-offline
COPY src ./src
RUN mvn package -DskipTests

FROM jetty:9
COPY --from=build /app/target/jaxrsbasic.war /var/lib/jetty/webapps/root.war
COPY src/main/resources/config.properties /var/lib/jetty/config.properties

# Konfigurasi Jetty
EXPOSE 8080
CMD ["java", "-Dconfig.file=/var/lib/jetty/config.properties", "-jar", "/usr/local/jetty/start.jar"]