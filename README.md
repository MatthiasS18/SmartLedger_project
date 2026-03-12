# SmartLedger

A full-stack accounting application for managing invoices, expenses, and users.
Built with Angular 17, Spring Boot 3, MongoDB, and Elasticsearch.

---

## 🌐 Live Demo

The application is deployed and accessible at:

**https://hired-harley-leaving-occur.trycloudflare.com/**

> This is a temporary Cloudflare tunnel for demonstration purposes only.

---

## Tech Stack

| Layer      | Technology                                      |
|------------|-------------------------------------------------|
| Frontend   | Angular 17 (standalone) + Tailwind CSS + Angular Material |
| Backend    | Spring Boot 3.2.5 + Java 17                     |
| Database   | MongoDB                                         |
| Search     | Elasticsearch 8.12 + Kibana                     |
| Charts     | ApexCharts (ng-apexcharts)                      |
| Container  | Docker + Docker Compose                         |

---

## Features

- Create, list and delete users, invoices and expenses
- Full-text search powered by Elasticsearch
- Donut chart for expenses by category
- Bar chart for invoices by month
- Reusable dialog component for all forms

---

## Prerequisites

- Node.js >= 18
- npm >= 9
- Java 17
- Maven >= 3.9
- Docker + Docker Compose

---

## Getting Started

### 1. Clone the repository

```bash
git clone https://github.com/MatthiasS18/SmartLedger_project.git
cd SmartLedger_project
```

### 2. Run with Docker (recommended)

docker compose -f docker-compose.amarris.yml up --build

---

## application.properties

src/main/resources/application.properties:

spring.data.mongodb.uri=mongodb://localhost:27017/smartledger
spring.elasticsearch.uris=http://localhost:9200
