# Ping-Pong - Two Microservices interacting via RabbitMQ

This project consists of two microservices that communicate through a message broker, RabbitMQ, implementing a ping-pong pattern. The project is built on Java 8, Kotlin, and Spring boot.

## Getting Started

1. Clone the repository:
```bash
git clone <repository-url>
cd nodal_pingPong
```

2. Build and run the services using Docker Compose:
```bash
docker-compose up --build
```

This will:
- Build both services
- Start RabbitMQ message broker
- Start both microservices
- Set up the necessary networking between services

## How It Works

1. Service 1 starts and sends an initial ping message
2. Service 2 receives the ping and responds with a pong
3. Service 1 receives the pong, waits 10 seconds, and sends another ping
4. The cycle continues...

## Message Flow

```
Service 1 (Java)                    RabbitMQ                    Service 2 (Kotlin)
     |                                |                              |
     |--- ping ---------------------->|                              |
     |                                |                              |
     |                                |--- ping -------------------->|
     |                                |                              |
     |                                |<---- pong -------------------|
     |                                |                              |
     |<---- pong ---------------------|                              |
     |                                |                              |
     |--- ping ---------------------->|                              |
     |                                |                              |
     |                                |--- ping -------------------->|
     |                                |                              |
     |                                |<---- pong -------------------|
     |                                |                              |
     |<---- pong ---------------------|                              |
```

## Configuration

### RabbitMQ Configuration
- Version: RabbitMQ 3.x with Management Plugin
- Default port: 5672 (AMQP)
- Management UI port: 15672
- Default credentials: guest/guest
- Image: rabbitmq:3-management

### Service Configuration
Both services are configured to connect to RabbitMQ using environment variables:
- SPRING_RABBITMQ_HOST
- SPRING_RABBITMQ_PORT
- SPRING_RABBITMQ_USERNAME
- SPRING_RABBITMQ_PASSWORD

## Development

### Building Locally

1. Build Service 1 (Java):
```bash
cd service-1-java
mvn clean install
```

2. Build Service 2 (Kotlin):
```bash
cd service-2-kotlin
mvn clean install
```


## Monitoring

- RabbitMQ Management UI: http://localhost:15672
- Default credentials: guest/guest

Author @Mergenmaniac@gmail.com
