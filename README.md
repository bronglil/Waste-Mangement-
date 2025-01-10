# Waste Mangement System

A robust waste management solution built with Java Spring Boot, designed to efficiently manage garbage bins, drivers, and vehicles.

## Table of Contents
- [Overview](#overview)
- [Technology Stack](#technology-stack)
- [Project Structure](#project-structure)
- [Setup and Installation](#setup-and-installation)
- [Key Features](#key-features)
- [API Documentation](#api-documentation)
- [Dependencies](#dependencies)

## Overview

The Waste Mangement System is a comprehensive solution for managing municipal waste collection operations. It provides functionality for tracking garbage bins, managing drivers, and coordinating vehicle operations through a REST API interface.

## Technology Stack

- **Java 21**
- **Spring Boot 3.3.5**
- **Spring Data JPA**
- **Spring Security**
- **H2 Database**
- **Swagger/OpenAPI**
- **JWT Authentication**
- **Jakarta Mail**
- **Lombok**

## Project Structure

```
src/main/java/com/example/garbagecollection/
├── config/
│   └── DatabaseConfig.java        # Database configuration
├── controller/
│   ├── BinController.java         # Bin management endpoints
│   ├── DriverController.java      # Driver management endpoints
│   └── VehicleController.java     # Vehicle management endpoints
├── dto/
│   ├── BinRequestDTO.java
│   ├── DriverRequestDTO.java
│   └── VehicleRequestDTO.java
├── entity/
│   ├── Bin.java
│   ├── Driver.java
│   └── Vehicle.java
├── exception/
│   └── CustomExceptionHandler.java
├── repository/
│   ├── BinRepository.java
│   ├── DriverRepository.java
│   └── VehicleRepository.java
├── service/
│   ├── BinService.java
│   ├── BinServiceImpl.java
│   ├── DriverService.java
│   ├── DriverServiceImpl.java
│   ├── VehicleService.java
│   └── VehicleServiceImpl.java
├── util/
│   └── GeoUtils.java
└── GarbageCollectionApplication.java
```

## Setup and Installation

### Prerequisites
- JDK 21
- Gradle
- IDE (IntelliJ IDEA recommended)

### Build Configuration

The project uses Gradle as the build tool. Key configurations are defined in `build.gradle.kts`:

```kotlin
plugins {
    java
    id("org.springframework.boot") version "3.3.5"
    id("io.spring.dependency-management") version "1.1.6"
    kotlin("jvm")
}

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}
```

### Running the Application

1. Clone the repository
```https://github.com/bronglil/Waste-Mangement-.git```

2. Navigate to the project directory  
``` cd waste-mangement ```
3. Run: `./gradlew bootRun`
4. Access the application at `http://localhost:8080`
5. Swagger UI is available at `http://localhost:8080/swagger-ui.html`

## Key Features

### Bin Management
- Track bin locations
- Monitor fill levels

### Driver Management
- Driver profiles and scheduling
- Route optimization
- Real-time status updates

  ### Admin Management
- Admin profiles and scheduling
- Performance tracking


### Vehicle Management
- Fleet tracking
- Route assignments

## API Documentation

### Bin Controller Endpoints
- GET `/api/bins` - List all bins
- POST `/api/bins` - Register new bin
- PUT `/api/bins/{id}` - Update bin information
- DELETE `/api/bins/{id}` - Remove bin

### Driver Controller Endpoints
- GET `/api/drivers` - List all drivers
- POST `/api/drivers` - Register new driver
- PUT `/api/drivers/{id}` - Update driver information
- DELETE `/api/drivers/{id}` - Remove driver

### Vehicle Controller Endpoints
- GET `/api/vehicles` - List all vehicles
- POST `/api/vehicles` - Register new vehicle
- PUT `/api/vehicles/{id}` - Update vehicle information
- DELETE `/api/vehicles/{id}` - Remove vehicle

## Dependencies

### Core Dependencies
```gradle
implementation("org.springframework.boot:spring-boot-starter-web")
implementation("org.springframework.boot:spring-boot-starter-data-jpa")
implementation("org.springframework.boot:spring-boot-starter-security")
```

### Security
```gradle
implementation("io.jsonwebtoken:jjwt-api:0.11.5")
implementation("io.jsonwebtoken:jjwt-impl:0.11.5")
implementation("io.jsonwebtoken:jjwt-jackson:0.11.5")
```

### Documentation
```gradle
implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.2.0")
```

### Utilities
```gradle
compileOnly("org.projectlombok:lombok:1.18.30")
implementation("org.springframework.boot:spring-boot-starter-mail")
implementation("jakarta.mail:jakarta.mail-api:2.1.0")
```

## Contributing

1. Fork the repository
2. Create a feature branch
3. Commit your changes
4. Push to the branch
5. Create a Pull Request

## License

This project is licensed under the MIT License - see the LICENSE file for details.
