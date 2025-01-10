# Waste Management System - Backend

The **Waste Management System** backend is the central hub of the project, responsible for managing data, processing business logic, and providing APIs to the frontend, mobile application, and IoT devices. Built with **Spring Boot**, **Gradle**, and **H2 Database**, this backend ensures seamless communication between all system components.

## Project Overview

The **Waste Management System** is a comprehensive solution for monitoring and managing waste collection. The backend is designed to:

1. **IoT Integration**:
   - Receive real-time updates on bin statuses from IoT devices
   - Process and store data in the H2 database

2. **API Services**:
   - Provide REST APIs for the frontend and mobile application

3. **Driver Functionality**:
   - Enable drivers to fetch bin statuses and navigate to bin locations using optimized routes

4. **Real-Time System Connectivity**:
   - Facilitate continuous communication between IoT devices, the backend, and the user-facing applications

## Requirements

To run the backend application, ensure you have the following installed:

- **Java** (11 or later)
- **Gradle** (7.0 or later)

## Steps to Run the Backend

### 1. Clone the Repository

Clone the backend repository to your local machine:

```bash
git clone git@github.com:Irfan-Ullah-cs/Waste-Mangement-System.git
cd Waste-Mangement-System
```

### 2. Configure the H2 Database

The backend uses an embedded H2 database by default. The configuration is located in the `application.properties` file under `src/main/resources`.

Default H2 configuration:
```properties
spring.datasource.url=jdbc:h2:mem:waste_management_db
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=password
spring.jpa.hibernate.ddl-auto=update
spring.h2.console.enabled=true
```

You can access the H2 database console at: http://localhost:8080/h2-console
- **JDBC URL**: jdbc:h2:file:./database/main
- **Username**: sa
- **Password**: passpass

### 3. Build the Application

Build the project using Gradle:
```bash
./gradlew build
```

### 4. Run the Application

Start the backend server:
```bash
./gradlew bootRun
```

The backend will be available at http://localhost:8080

## Features Summary

- **Real-Time Monitoring**: Receives and processes bin updates from IoT devices
- **API Services**: Provides endpoints for the frontend and mobile app
- **Driver Functionality**: Supports route optimization and bin status updates
- **Embedded Database**: Utilizes H2 for quick and lightweight data storage
# API Endpoints Documentation

The backend of the Waste Management System provides various RESTful APIs for managing users, administrators, drivers, vehicles, bins, routing, and dashboard metrics. Below is a detailed list of endpoints categorized by their functionality.

---

## Authentication Endpoints

### **Auth**
- **POST** `/api/auth/signup`  
  - Sign up users.

- **POST** `/api/auth/login`  
  - Log in users.

---

## Admin Endpoints

### **Admin Management**
- **GET** `/api/admin/{id}`  
  - Get admin by ID.

- **PUT** `/api/admin/{id}`  
  - Update admin by ID.

- **DELETE** `/api/admin/{id}`  
  - Delete admin by ID.

- **PUT** `/api/admin/update_driver/{id}`  
  - Update driver by ID.

- **POST** `/api/admin/create_driver`  
  - Add a new driver.

- **GET** `/api/admin`  
  - Get all admins.

- **GET** `/api/admin/{name}`  
  - Get admin by name.

- **GET** `/api/admin/get_drivers`  
  - Get all drivers.

- **GET** `/api/admin/get_driver/{id}`  
  - Get a single driver by ID.

- **GET** `/api/admin/admins`  
  - Get all admins.

- **DELETE** `/api/admin/remove_driver/{id}`  
  - Delete a driver by ID.

---

## Vehicle Endpoints

### **Vehicle Management**
- **GET** `/api/vehicles/{id}`  
  - Get vehicle by ID.

- **PUT** `/api/vehicles/{id}`  
  - Update vehicle by ID.

- **DELETE** `/api/vehicles/{id}`  
  - Delete vehicle by ID.

- **GET** `/api/vehicles`  
  - Get all vehicles.

- **POST** `/api/vehicles`  
  - Add a new vehicle.

---

## Driver Endpoints

### **Driver Management**
- **GET** `/api/drivers/{id}`  
  - Get a single driver by ID.

- **PUT** `/api/drivers/{id}`  
  - Update a driver by ID.

- **DELETE** `/api/drivers/{id}`  
  - Delete a driver by ID.

- **GET** `/api/drivers`  
  - Get all drivers.

- **POST** `/api/drivers`  
  - Add a new driver.

- **GET** `/api/drivers/name/{name}`  
  - Get drivers by name.

- **GET** `/api/drivers/available-drivers`  
  - Get all available drivers.

---

## Bin Endpoints

### **Bin Management**
- **GET** `/api/bins/{id}`  
  - Get bin by ID.

- **PUT** `/api/bins/{id}`  
  - Update bin by ID.

- **DELETE** `/api/bins/{id}`  
  - Delete bin by ID.

- **PATCH** `/api/bins/{id}`  
  - Partially update bin by ID.

- **GET** `/api/bins`  
  - Get all bins.

- **POST** `/api/bins`  
  - Add a new bin.

- **POST** `/api/bins/location`  
  - Add or update bin location.

---

## Routing Endpoints

### **Routing Controller**
- **GET** `/api/routes/shortest`  
  - Get the shortest route for waste collection.

---

## Dashboard Endpoints

### **Dashboard Metrics**
- **GET** `/api/dashboard/metrics`  
  - Get dashboard metrics for monitoring system performance.

---



## Contribution Guidelines

We welcome contributions! To contribute:

1. Fork the repository
2. Create a feature branch
3. Submit a pull request with your changes

## License

This project is licensed under the [MIT License](LICENSE).

## Folder Structure

```plaintext
src/main/java/com/example/garbagecollection/
├── config/
│   └── DatabaseConfig.java        # Configuration for database connections
├── controller/
│   ├── BinController.java         # REST API for managing bins
│   └── DriverController.java  # REST API for managing drivers
|   └── vehicleController.java  # REST API for managing vehicles
├── dto/
│   ├── BinRequestDTO.java         # Data Transfer Object for bin requests
│   └── DriverRequestDTO.java      # Data Transfer Object for driver requests
|   └── vehicleRequestDTO.java 
├── entity/
│   ├── Bin.java                   # Entity class representing bins
│   └── Driver.java                # Entity class representing drivers
|   └── vehicle.java 
├── exception/
│   └── CustomExceptionHandler.java# Global exception handling
├── repository/
│   ├── BinRepository.java         # Repository for bin-related database operations
│   └── DriverRepository.java      # Repository for driver-related database operations
|   └── vehicleRepository.java 
├── service/
│   ├── BinService.java            # Interface for bin services
│   ├── BinServiceImpl.java        # Implementation of bin services
│   ├── DriverService.java         # Interface for driver services
│   └── DriverServiceImpl.java     # Implementation of driver services
│   └── VehicleServiceImpl.java
│   ├── VehicleService.java
├── util/
│   └── GeoUtils.java              # Utility class for geospatial calculations
└── GarbageCollectionApplication.java # Main application entry point
```
