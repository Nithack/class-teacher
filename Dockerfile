# Use a imagem oficial do Gradle com o JDK 17 como imagem de build
FROM gradle:7.6.1-jdk17 as build

# Copia o código para o container
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src

# Compila o projeto
RUN gradle bootJar --no-daemon

# Segunda etapa do build - Usa o OpenJDK para rodar a aplicação
FROM openjdk:17-jdk-alpine

# VOLUME /tmp - opcional
# ARG DEPENDENCY=/home/gradle/src/build/dependency
COPY --from=build /home/gradle/src/build/libs/*.jar app.jar

EXPOSE 8080

# Roda a aplicação
ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar","/app.jar"]
