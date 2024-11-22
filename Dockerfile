# Imagen base
FROM eclipse-temurin:17.0.11_9-jdk

# Definir nuestro directorio de trabajo
WORKDIR /app

# Copiar el JAR dentro del contenedor
COPY ./target/franchise-0.0.1-SNAPSHOT.jar /app1

# Definir el puerto del contenedor
EXPOSE 8080

# Comandos que se ejecutan solo cuando el contenedor se levanta
ENTRYPOINT ["java","-jar","./franchise-0.0.1-SNAPSHOT.jar"]