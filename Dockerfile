# Etapa de construcción con Gradle y JDK 21
FROM gradle:8.13-jdk21 AS build

WORKDIR /app
COPY --chown=gradle:gradle . .

# Ejecutar la construcción de la aplicación sin pruebas
RUN gradle build -x test

# Etapa de ejecución con Amazon Corretto 21
FROM amazoncorretto:21-alpine

VOLUME /tmp
COPY --from=build /app/build/libs/*.jar app.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app.jar"]
