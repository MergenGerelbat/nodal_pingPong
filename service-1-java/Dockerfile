FROM maven:3.8.4-openjdk-8 AS builder
WORKDIR /build
COPY pom.xml .
COPY service-1-java/pom.xml ./service-1-java/
COPY service-1-java/src ./service-1-java/src
WORKDIR /build/service-1-java
RUN mvn clean package -DskipTests -N && \
    mvn clean package -DskipTests

FROM openjdk:8-jdk-alpine
WORKDIR /app
COPY --from=builder /build/service-1-java/target/service-1-java-1.0-SNAPSHOT.jar app.jar
RUN addgroup -S spring && adduser -S spring -G spring
USER spring
ENTRYPOINT ["java", "-jar", "app.jar"]