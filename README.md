# Running the MQ Routing Application

## Prerequisites
- Docker and Docker Compose installed on your system

## Quick Start

1. Clone the repository:
   ```bash
   git clone <repository-url>
   cd <repository-directory>
   ```

2. Start the application stack:
   ```bash
   docker compose up -d
   ```

3. Access the components:
    - UI: http://localhost
    - Backend API: http://localhost:8080
    - IBM MQ Console: https://localhost:9443/ibmmq/console (credentials: admin/passw0rd)
    - PostgreSQL: localhost:5432 

4. Stop the application:
   ```bash
   docker compose down
   ```

## Components
- **IBM MQ**: Message queue system
- **PostgreSQL**: Database for storing messages and partner information
- **MQ Routing Server**: Java Spring Boot backend application
- **MQ Routing UI**: Angular frontend application
