FROM openjdk:8-jdk-alpine
VOLUME /tmp
<<<<<<< HEAD
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["sh", "-c", "java -jar /app.jar"]
=======
ARG JAR_FILE=./target/*.jar
COPY ${JAR_FILE} /app.jar
ENTRYPOINT ["sh", "-c", "java -jar /app.jar"]
>>>>>>> 88935ece825d2179981f0d4faea6eab15e0d4d6f
