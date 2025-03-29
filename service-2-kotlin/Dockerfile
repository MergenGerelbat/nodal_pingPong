FROM maven:3.8.4-openjdk-17 AS builder
WORKDIR /build
COPY pom.xml .
COPY service-2-kotlin/pom.xml ./service-2-kotlin/
COPY service-2-kotlin/src ./service-2-kotlin/src
WORKDIR /build/service-2-kotlin
RUN mvn clean package -DskipTests -N && \
    mvn clean package -DskipTests

FROM openjdk:17-jdk-slim
WORKDIR /app
COPY --from=builder /build/service-2-kotlin/target/service-2-kotlin-1.0-SNAPSHOT.jar app.jar
RUN groupadd -r spring && useradd -r -g spring spring
USER spring
ENTRYPOINT ["java", "-jar", "app.jar"]