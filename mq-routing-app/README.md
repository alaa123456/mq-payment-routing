# MQ Routing Application

## Overview
MQ Routing Application is a Spring Boot service designed to handle MQ message routing.

## Technical Stack
- **Java 21**
- **Spring Boot 3.4.4**
- **IBM MQ** 
- **PostgreSQL** 
- **JPA** 
- **Spring Web** 
- **Lombok**
- **TestContainers** 

## Project Structure
```
com.bank.payment.mq.routing
├── controller
│   ├── MessageController
│   └── PartnerController
├── listener
│   └── MqListener
├── model
│   ├── Message
│   └── Partner
├── repository
│   ├── MessageRepository
│   └── PartnerRepository
├── service
│   └── MessageService
└── MqRoutingAppApplication
```

## Features
- Listens to IBM MQ queues for incoming messages
- Stores messages in a PostgreSQL database
- Manages partner information for message routing
- Provides REST APIs for message and partner management
- Includes health monitoring via Spring Actuator

## Configuration
The application uses standard Spring Boot configuration patterns. Key configuration properties include:

- IBM MQ connection settings
- PostgreSQL database connection
- JMS queue configuration

## Getting Started

### Prerequisites
- Java 21 JDK
- PostgreSQL database
- IBM MQ server or container
- Maven for building

### Building the Application
```bash
mvn clean package
```

### Running the Application
```bash
java -jar target/mq-routing-app-0.0.1.jar
```

### Testing
The application includes unit tests and integration tests using TestContainers, which provides containerized instances of PostgreSQL for testing:

```bash
mvn test
```
