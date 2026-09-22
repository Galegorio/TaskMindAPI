# TaskMind API

> Intelligent API for task, event and operational management through natural language.

[![Java](https://img.shields.io/badge/Java-21+-ED8B00?style=flat\&logo=openjdk\&logoColor=white)](https://www.java.com/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.x-6DB33F?style=flat\&logo=springboot\&logoColor=white)](https://spring.io/projects/spring-boot)
[![PostgreSQL](https://img.shields.io/badge/PostgreSQL-17+-4169E1?style=flat\&logo=postgresql\&logoColor=white)](https://www.postgresql.org/)
[![Maven](https://img.shields.io/badge/Maven-3.x-C71A36?style=flat\&logo=apachemaven\&logoColor=white)](https://maven.apache.org/)
[![Status](https://img.shields.io/badge/status-in%20development-yellow?style=flat)]()

---

## Overview

**TaskMind** is an API-first platform designed to transform natural-language instructions into structured actions for managing tasks, events and operational activities.

Instead of requiring users to manually navigate through multiple screens and forms, TaskMind aims to allow interactions such as:

> "Amanhã às 9h chega o caminhão da empresa X com 300 caixas. O descarregamento deve levar aproximadamente 40 minutos."

The system will interpret the request, extract the relevant information, validate the data and create the corresponding operation.

The project is being designed as an independent API, allowing different applications and interfaces to consume its services.

---

## The Idea

TaskMind goes beyond a traditional to-do list.

The platform is being designed to represent different types of activities and operations, including:

* Tasks
* Meetings
* Appointments
* Visits
* Deliveries
* Merchandise receiving
* Pickups
* Transfers
* Other operational activities

The main goal is to create a common platform where these activities can be managed through both traditional API requests and natural-language interactions.

---

## How It Works

The core concept follows a controlled pipeline:

```text
┌─────────────────────┐
│   Natural Language  │
│                     │
│ "Marca uma reunião  │
│  sexta às 15h."     │
└──────────┬──────────┘
           │
           ▼
┌─────────────────────┐
│   AI Interpretation │
│                     │
│ Identify intent     │
│ Extract information │
└──────────┬──────────┘
           │
           ▼
┌─────────────────────┐
│ Structured Action   │
│                     │
│ CREATE_EVENT        │
│ MEETING             │
│ 15:00               │
└──────────┬──────────┘
           │
           ▼
┌─────────────────────┐
│      Validation     │
│                     │
│ Business rules      │
│ Data validation     │
│ Permissions         │
└──────────┬──────────┘
           │
           ▼
┌─────────────────────┐
│      Execution      │
│                     │
│ Database / Services │
└─────────────────────┘
```

A inteligência artificial não terá acesso direto às operações internas do sistema.

Instead, it will propose a structured action that is validated and executed by the API.

---

## Example

### User input

```text
Marca uma reunião com João para sexta às 15h.
```

### Possible AI interpretation

```json
{
  "intent": "CREATE_EVENT",
  "event_type": "MEETING",
  "title": "Reunião com João",
  "date": "2026-09-25",
  "start_time": "15:00"
}
```

The API can then validate the information before creating the event.

This architecture allows the AI layer to remain independent from the application's business logic.

---

## Architecture

The project follows an **API-first architecture**.

```text
                       ┌───────────────────┐
                       │    Web Client     │
                       └─────────┬─────────┘
                                 │
                       ┌─────────▼─────────┐
                       │    Mobile Client  │
                       └─────────┬─────────┘
                                 │
                                 ▼
                    ┌────────────────────────┐
                    │      TaskMind API      │
                    │                        │
                    │  Authentication       │
                    │  Tasks                │
                    │  Events               │
                    │  Operations           │
                    │  AI                   │
                    │  Notifications        │
                    │  Integrations         │
                    └───────────┬────────────┘
                                │
              ┌─────────────────┼─────────────────┐
              │                 │                 │
              ▼                 ▼                 ▼
       ┌─────────────┐   ┌─────────────┐   ┌──────────────┐
       │ PostgreSQL  │   │ AI Provider │   │ Integrations │
       └─────────────┘   └─────────────┘   └──────────────┘
```

The client applications are consumers of the API rather than the core of the system.

This allows TaskMind to potentially support multiple clients in the future.

---

## Core Concepts

The domain is being designed around three main concepts:

### Tasks

Activities that need to be completed.

Examples:

* Send an email
* Review a document
* Call a supplier
* Prepare an order

### Events

Activities associated with a specific time or period.

Examples:

* Meetings
* Appointments
* Visits
* Scheduled deliveries

### Operations

Activities that involve a real-world operational process.

Examples:

* Receiving merchandise
* Delivery
* Pickup
* Transfer
* Return

These concepts can be related to other entities such as people, locations, companies, vehicles and merchandise.

---

## Planned AI Capabilities

The AI layer is planned to support operations such as:

* Natural-language task creation
* Event creation and modification
* Task categorization
* Priority detection
* Date and time extraction
* Schedule organization
* Conflict detection
* Schedule suggestions
* Daily summaries
* Natural-language queries
* Conversational interactions

For potentially destructive or large-scale actions, the system may use a **preview and confirmation flow** before applying changes.

---

## Integrations

The project is designed with external integrations in mind.

Planned integrations include:

* Google Tasks
* Google Calendar
* AI/LLM providers
* Mapping and location services
* Notification services

The integration layer will be designed to keep external services decoupled from the core business logic.

---

## Planned Input Methods

The initial interaction will be text-based.

Future versions may support additional input methods, including:

```text
Text
  │
  ├──────────────► AI Interpretation
  │
Audio
  │
  └──► Speech-to-Text ──► AI Interpretation
```

This allows different input methods to share the same processing pipeline.

---

## Technology Stack

### Backend

* Java
* Spring Boot
* Spring Data JPA
* Hibernate
* Maven

### Database

* PostgreSQL

### API

* REST
* JSON
* OpenAPI / Swagger

### Security

* Spring Security
* JWT
* OAuth 2.0

### Artificial Intelligence

An abstraction layer will be used for AI providers, allowing the underlying model or provider to be replaced without coupling the core application to a specific service.

---

## Roadmap

The project is currently in its planning and foundation stage.

### Phase 1 — Foundation

* [ ] Project setup
* [ ] Spring Boot configuration
* [ ] Database configuration
* [ ] Initial domain model
* [ ] API documentation
* [ ] Authentication

### Phase 2 — Core API

* [ ] Task management
* [ ] Event management
* [ ] Operation management
* [ ] People and locations
* [ ] Notifications
* [ ] Audit history

### Phase 3 — Integrations

* [ ] Google OAuth
* [ ] Google Tasks integration
* [ ] Google Calendar integration
* [ ] External service abstraction

### Phase 4 — AI

* [ ] Natural-language interpretation
* [ ] Structured AI actions
* [ ] Validation pipeline
* [ ] AI-generated tasks and events
* [ ] Schedule analysis
* [ ] Conflict detection
* [ ] Confirmation flow

### Phase 5 — Client Application

* [ ] Web application
* [ ] Dashboard
* [ ] Calendar
* [ ] Task management
* [ ] AI assistant interface

### Phase 6 — Multimodal Interaction

* [ ] Audio input
* [ ] Speech-to-text
* [ ] Document processing
* [ ] Image-based information extraction

---

## Project Status

🚧 **Under active development**

TaskMind is currently in the planning and architecture stage.

The architecture, features and roadmap may evolve as the project develops.

---

## Project Goals

TaskMind is being developed with two complementary goals:

**1. Build a useful platform**

Create an API capable of handling tasks, events and operational workflows through both traditional APIs and natural-language interactions.

**2. Explore modern backend development**

The project is also being used to explore and demonstrate concepts such as:

* REST API development
* Backend architecture
* Domain modeling
* Database design
* Authentication and authorization
* External API integrations
* Artificial intelligence integration
* Natural-language processing
* Business rules
* Software architecture
* API documentation

---

## License

This project is licensed under the MIT License.
