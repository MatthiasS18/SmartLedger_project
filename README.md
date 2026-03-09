# SmartLedger

A full-stack accounting application built with Angular 17, Spring Boot 3, MongoDB, and Elasticsearch.

## Tech Stack

Layer | Technology 

Frontend | Angular 17 (standalone components) + Tailwind CSS + Angular Material
Backend | Spring Boot 3.2.5 + Java 17
Database | MongoDB
Search | Elasticsearch 8.12
Charts | ApexCharts (ng-apexcharts)


## Prerequisites

Make sure you have the following installed:

- **Node.js** >= 18
- **npm** >= 9
- **Java** 17
- **Maven** >= 3.9
- **Docker** + **Docker Compose**

## Getting Started

### 1. Clone the repository

git clone <repo-url>
cd amaris_project

### 2. Start MongoDB + Elasticsearch with Docker

docker-compose up -d

This starts:
- **MongoDB** on `localhost:27017`
- **Elasticsearch** on `localhost:9200`

> Make sure Docker Desktop is running before executing this command.


### 3. Start the Spring Boot backend

cd amarris_spring
mvn clean install
mvn spring-boot:run


The backend runs on `http://localhost:8080`.

### 4. Start the Angular frontend

cd amarris_angular
npm install
ng serve


The frontend runs on `http://localhost:4200`.

## application.properties

Make sure your `src/main/resources/application.properties` contains:

spring.data.mongodb.uri=mongodb://localhost:27017/smartledger
spring.elasticsearch.uris=http://localhost:9200

