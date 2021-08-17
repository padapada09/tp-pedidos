FROM openjdk:11
ARG JAR_FILE=target/*.jar
COPY . /usr/src/tp
WORKDIR /usr/src/tp
CMD ["./mvnw", "spring-boot:run"]