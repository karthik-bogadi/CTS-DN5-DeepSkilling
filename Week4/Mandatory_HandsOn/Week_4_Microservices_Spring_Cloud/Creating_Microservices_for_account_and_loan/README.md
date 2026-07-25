# Creating Microservices for Account and Loan

## Week 4 – Microservices with Spring Boot 3 and Spring Cloud

---

# Introduction

Modern software applications are becoming increasingly large and complex. As the number of users, features, and business requirements grows, developing and maintaining a single large application becomes difficult.

Traditionally, applications were developed using the **Monolithic Architecture**, where all modules such as user management, accounts, loans, payments, reports, notifications, etc., were developed as a single application.

Although this approach is simple for small applications, it creates several problems as the application grows.

To overcome these limitations, organizations such as **Amazon, Netflix, Google, Microsoft, Uber, PayPal, and banks** have adopted the **Microservices Architecture**, where an application is divided into multiple small independent services.

In this hands-on exercise, we implement two independent microservices for a banking application:

- **Account Microservice**
- **Loan Microservice**

Each microservice is developed as a completely separate **Spring Boot REST application** with its own Maven project, source code, dependencies, configuration files, and runtime.

These services communicate through REST APIs and can be deployed independently.

This exercise serves as the foundation for understanding **Spring Cloud**, **Service Discovery**, **API Gateway**, and the complete Microservices ecosystem.

---

# Learning Objectives

After completing this exercise, you will be able to:

- Understand the concept of Microservices Architecture.
- Differentiate between Monolithic and Microservices Architecture.
- Create independent Spring Boot REST applications.
- Develop REST APIs using Spring Boot.
- Create multiple Maven projects.
- Configure different server ports.
- Run multiple Spring Boot applications simultaneously.
- Test REST APIs using Browser and Postman.
- Understand how independent services communicate.
- Prepare applications for Service Discovery using Eureka.

---

# Prerequisites

Before starting this exercise, ensure you have the following:

- Java 21
- Maven
- Spring Boot 3.5.x
- IntelliJ IDEA / Eclipse
- Postman
- Basic knowledge of REST APIs
- Basic understanding of Spring Boot

---

# What is a Monolithic Application?

## Definition

A **Monolithic Application** is a software application in which **all business functionalities are developed, deployed, and executed as a single application**.

Everything exists inside one project.

Example

```
Bank Application

│

├── Login Module

├── Customer Module

├── Account Module

├── Loan Module

├── Transaction Module

├── Payment Module

├── Notification Module

├── Reports Module

└── Database
```

Everything is packaged into one application.

---

# Characteristics of Monolithic Architecture

A monolithic application has the following characteristics:

- Single Project
- Single Deployment
- Single Database
- Single Build Process
- Single Runtime
- Single Server

Whenever one module changes,

the entire application must be rebuilt and redeployed.

---

# Advantages of Monolithic Architecture

For small applications, a monolithic architecture offers several benefits:

- Simple to develop
- Easy to understand
- Easy deployment
- Easier debugging
- Single codebase
- Suitable for small teams
- Lower infrastructure cost

---

# Limitations of Monolithic Architecture

As applications become larger, monolithic architecture creates many challenges.

## Large Codebase

Suppose a banking application contains

```
Accounts

Loans

Customers

Payments

Cards

Insurance

Investments

Reports

Notifications
```

All of these modules exist in a single project.

Eventually,

the project becomes extremely large and difficult to maintain.

---

## Difficult Deployment

Suppose a developer modifies only the Loan Module.

Even though only one module changed,

the entire application must be rebuilt.

```
Loan Module Changed

↓

Build Entire Application

↓

Deploy Entire Application
```

This increases deployment time.

---

## Poor Scalability

Suppose

```
Loan Module

↓

10 Million Requests
```

while

```
Account Module

↓

100 Requests
```

Even though only the Loan Module needs more resources,

the complete application must be scaled.

This wastes CPU and memory.

---

## Technology Restrictions

Imagine the Login Module requires Java,

while the Reporting Module performs better with Python.

In a monolithic application,

everything generally uses the same technology stack.

This limits flexibility.

---

## Difficult Maintenance

Large projects often contain

- Thousands of classes
- Hundreds of packages
- Millions of lines of code

Understanding such applications becomes difficult for developers.

---

# Real-World Example of a Monolithic Application

Imagine a supermarket.

Inside one building, we have

```
Billing

↓

Groceries

↓

Vegetables

↓

Bakery

↓

Medical Store

↓

Customer Support

↓

Warehouse
```

Everything operates inside one building.

If electricity fails,

the entire supermarket stops functioning.

Similarly,

if one module crashes in a monolithic application,

it may affect the entire system.

---

# What is Microservices Architecture?

## Definition

Microservices Architecture is a software development approach in which a large application is divided into **multiple small, independent, and loosely coupled services**.

Each service focuses on one specific business capability.

Each microservice

- has its own code,
- has its own project,
- has its own deployment,
- has its own runtime,
- communicates using REST APIs.

---

# Characteristics of Microservices

Every microservice is

- Independent
- Small
- Focused on one business functionality
- Independently deployable
- Independently scalable
- Loosely coupled
- Highly maintainable

Each service can evolve without affecting other services.

---

# Banking Example

Instead of creating one large banking application,

we divide it into multiple services.

```
Bank System

│

├── Customer Service

├── Account Service

├── Loan Service

├── Payment Service

├── Card Service

├── Notification Service

├── Insurance Service

└── Report Service
```

Each service performs only one responsibility.

---

# Why Do We Split Applications?

Suppose only the Loan Service receives heavy traffic.

Instead of increasing resources for the complete application,

we scale only

```
Loan Service
```

This reduces infrastructure cost and improves performance.

---

# Real-World Example of Microservices

Imagine a shopping mall.

Inside the mall,

there are many independent stores.

```
Mall

│

├── Clothing Store

├── Mobile Store

├── Restaurant

├── Book Store

├── Pharmacy

└── Grocery Store
```

Each store

- has its own employees,
- has its own inventory,
- has its own billing,
- opens and closes independently.

If one store closes,

the remaining stores continue working.

Microservices behave in the same way.

---

# Monolithic vs Microservices

| Monolithic | Microservices |
|------------|---------------|
| Single application | Multiple independent applications |
| Single deployment | Independent deployment |
| Single codebase | Separate codebase for each service |
| Entire application must be rebuilt | Only modified service is rebuilt |
| Difficult to scale | Easy to scale individual services |
| Technology lock-in | Different services can use different technologies |
| One large project | Many small projects |
| Failure may affect entire application | Failure usually affects only one service |

---

# Why Banks Prefer Microservices

Modern banking applications must handle

- Millions of users
- Thousands of transactions per second
- High availability
- Continuous deployment
- Independent teams

A bank may have separate teams working on

- Accounts
- Loans
- Payments
- Cards
- Net Banking
- Mobile Banking
- Notifications

Each team can develop and deploy its own microservice independently.

This significantly improves productivity and reduces deployment risk.

---

# What is a Microservice?

## Definition

A **Microservice** is a small independent application that performs one specific business function and communicates with other services using lightweight protocols such as HTTP/REST.

Examples

```
Account Service

↓

Handles Accounts Only
```

```
Loan Service

↓

Handles Loans Only
```

```
Payment Service

↓

Handles Payments Only
```

Each service is responsible for only one business capability.

---

# What is Spring Boot?

## Definition

Spring Boot is a Java framework that simplifies the development of standalone, production-ready Spring applications.

Spring Boot provides

- Embedded Tomcat
- Auto Configuration
- Starter Dependencies
- REST API Support
- Production-ready features

Each microservice in this exercise is built using Spring Boot.

---

# Why Spring Boot for Microservices?

Spring Boot is widely used for Microservices because it offers:

- Fast project creation
- Embedded server
- Easy dependency management
- REST API development
- Cloud compatibility
- Spring Cloud integration
- Production-ready architecture

It allows developers to build and deploy independent services quickly.

---

# Exercise Overview

In this hands-on exercise, we create two independent Spring Boot applications.

### Account Microservice

Provides account-related information.

Endpoint

```
GET

/accounts/{number}
```

Sample Response

```json
{
    "number":"00987987973432",
    "type":"Savings",
    "balance":234343
}
```

---

### Loan Microservice

Provides loan-related information.

Endpoint

```
GET

/loans/{number}
```

Sample Response

```json
{
    "number":"H00987987972342",
    "type":"Car",
    "loan":400000,
    "emi":3258,
    "tenure":18
}
```

Both services are completely independent and run on different ports.

---

# High-Level Architecture

```
                    Client

                       │

        ┌──────────────┴──────────────┐

        ▼                             ▼

Account Microservice           Loan Microservice

Port : 8080                    Port : 8081

        │                             │

        ▼                             ▼

 JSON Response                JSON Response
```

At this stage, there is **no communication** between the two services. Each microservice operates independently and exposes its own REST endpoint. In the upcoming exercises, these services will be registered with **Eureka Discovery Server**, connected through an **API Gateway**, and later integrated into a complete Spring Cloud Microservices architecture.

# Project Architecture

Before implementing the project, let us understand how the two microservices are organized.

In this exercise, we create **two completely independent Spring Boot applications**.

Each application has its own:

- Maven Project
- Source Code
- Configuration File
- Embedded Tomcat Server
- REST Controller
- Model Classes
- Dependencies

Both applications run separately but together form a Banking System.

```
                 Banking System

                        │
        ┌───────────────┴───────────────┐
        │                               │
        ▼                               ▼
 Account Microservice            Loan Microservice

     Port : 8080                   Port : 8081

        │                               │
        ▼                               ▼
  Account Details                Loan Details
```

Notice that there is **no direct communication** between these services in this exercise.

Each service works independently.

---

# Why Create Separate Maven Projects?

Many beginners ask,

> Why don't we create Account and Loan inside one Spring Boot project?

The answer is **Microservices are independent applications.**

Each microservice must be capable of

- Running independently
- Building independently
- Deploying independently
- Scaling independently
- Updating independently

Therefore,

we create

```
Account Project
```

and

```
Loan Project
```

instead of placing everything inside one project.

---

# Project Structure

The project contains two separate Maven applications.

```
Creating_Microservices_for_account_and_loan

│

├── account

│   │

│   ├── src

│   │   ├── main

│   │   │    ├── java

│   │   │    └── resources

│   │

│   ├── pom.xml

│   └── mvnw

│

└── loan

    │

    ├── src

    │   ├── main

    │   │    ├── java

    │   │    └── resources

    │

    ├── pom.xml

    └── mvnw
```

Each folder represents an independent Spring Boot application.

---

# Account Microservice

## Purpose

The Account Microservice is responsible for handling account-related information.

Examples include

- Account Number
- Account Type
- Balance
- Customer Account Details

Whenever a client requests account information,

this microservice responds.

---

# Responsibilities of Account Microservice

This service performs only account operations.

```
Account Service

↓

Receive Request

↓

Process Request

↓

Return Account Details
```

It does **not**

- Calculate EMI
- Return Loan Details
- Manage Cards
- Send Notifications

Those responsibilities belong to other microservices.

---

# Loan Microservice

## Purpose

The Loan Microservice manages loan-related information.

Examples include

- Loan Number
- Loan Type
- Loan Amount
- EMI
- Loan Tenure

Whenever a client requests loan details,

this microservice responds.

---

# Responsibilities of Loan Microservice

```
Loan Service

↓

Receive Request

↓

Process Request

↓

Return Loan Details
```

It does not perform

- Account Operations
- Payment Processing
- Card Services

It only handles loans.

---

# Independent Deployment

One of the biggest advantages of Microservices is independent deployment.

Suppose developers modify only the Loan Service.

```
Loan Service Updated
```

Only this service is rebuilt.

```
Loan Service

↓

Build

↓

Deploy

↓

Done
```

Account Service continues running.

There is no need to stop or redeploy it.

---

# Independent Scaling

Suppose

```
Account Service

↓

100 Users
```

```
Loan Service

↓

50,000 Users
```

Instead of increasing resources for both services,

only the Loan Service can be scaled.

```
Loan Service

↓

Multiple Instances

↓

Load Balancer

↓

Users
```

This reduces infrastructure cost.

---

# Independent Failure

Suppose the Loan Service crashes.

```
Loan Service

↓

Stopped
```

The Account Service still works.

```
Account Service

↓

Running
```

Users can continue checking their account balance even though loan information is temporarily unavailable.

This improves application availability.

---

# Technologies Used

The following technologies are used in this exercise.

| Technology | Purpose |
|------------|---------|
| Java 21 | Programming Language |
| Spring Boot 3 | REST Application Development |
| Maven | Build Tool |
| Embedded Tomcat | Web Server |
| Spring Web | REST APIs |
| DevTools | Automatic Restart |
| Postman | API Testing |

---

# Spring Boot Components Used

The Account and Loan applications use the following Spring Boot components.

```
Spring Boot Application

↓

Embedded Tomcat

↓

Dispatcher Servlet

↓

REST Controller

↓

Java Model Class

↓

JSON Response
```

Each component performs a specific responsibility.

---

# Understanding the Components

## Spring Boot Application

The application starts here.

```
@SpringBootApplication
```

This annotation tells Spring Boot

- Scan Components
- Configure Beans
- Start Embedded Tomcat

This class is the entry point of the application.

---

## REST Controller

The REST Controller receives HTTP requests.

Example

```
GET

/accounts/1001
```

The controller receives the request,

calls the required methods,

and returns the response.

---

## Model Class

A Model Class represents business data.

Example

```
Account

↓

Account Number

↓

Type

↓

Balance
```

Spring Boot automatically converts the object into JSON.

---

## application.properties

Every Spring Boot project contains

```
application.properties
```

This file stores configuration.

Example

```
Server Port

Application Name

Logging Configuration

Database Configuration

Security Settings
```

For this exercise,

we configure

```
server.port
```

to avoid port conflicts.

---

# Why Different Server Ports?

Every web application requires a port.

Suppose both applications use

```
8080
```

```
Account Service

↓

8080
```

```
Loan Service

↓

8080
```

The second application cannot start because the port is already occupied.

Therefore,

we configure

```
Account Service

↓

8080
```

```
Loan Service

↓

8081
```

Now both applications can run simultaneously.

---

# Request Processing Flow

When a client requests account information,

the request follows this path.

```
Browser

↓

Tomcat Server

↓

Dispatcher Servlet

↓

Account Controller

↓

Account Object

↓

JSON

↓

Browser
```

Similarly,

Loan requests follow

```
Browser

↓

Tomcat Server

↓

Dispatcher Servlet

↓

Loan Controller

↓

Loan Object

↓

JSON

↓

Browser
```

---

# REST API Endpoints

## Account Service

### Request

```
GET

/accounts/{number}
```

Example

```
GET

http://localhost:8080/accounts/00987987973432
```

Response

```json
{
  "number": "00987987973432",
  "type": "Savings",
  "balance": 234343
}
```

---

## Loan Service

### Request

```
GET

/loans/{number}
```

Example

```
GET

http://localhost:8081/loans/H00987987972342
```

Response

```json
{
  "number": "H00987987972342",
  "type": "Car",
  "loan": 400000,
  "emi": 3258,
  "tenure": 18
}
```

---

# Internal Working of Spring Boot

When the application starts,

Spring Boot performs the following sequence automatically.

```
Start Application

↓

Read pom.xml

↓

Download Dependencies

↓

Create Spring Context

↓

Scan Components

↓

Create Beans

↓

Configure Dispatcher Servlet

↓

Start Embedded Tomcat

↓

Listen for HTTP Requests
```

This entire process happens automatically because of Spring Boot's **Auto Configuration** feature.

---

# Advantages of This Architecture

By separating the application into Account and Loan services, we gain several benefits:

- Better code organization
- Easier maintenance
- Independent deployment
- Independent scaling
- Improved fault isolation
- Faster development
- Team independence
- Reusability of services
- Foundation for Spring Cloud and Distributed Systems

This simple exercise demonstrates the first step toward building enterprise-level microservices. In the following exercises, these independent services will be registered with a **Eureka Discovery Server**, enabling service discovery and communication within a complete Spring Cloud ecosystem.

# Complete Folder Structure

The exercise consists of two independent Spring Boot projects.

## Account Microservice Structure

```
account

│

├── src

│   ├── main

│   │   ├── java

│   │   │   └── com

│   │   │       └── cognizant

│   │   │           └── account

│   │   │               ├── AccountApplication.java

│   │   │               ├── controller

│   │   │               │      └── AccountController.java

│   │   │               └── model

│   │   │                      └── Account.java

│   │   │

│   │   └── resources

│   │          └── application.properties

│   │

│   └── test

│

├── pom.xml

└── mvnw
```

---

## Loan Microservice Structure

```
loan

│

├── src

│   ├── main

│   │   ├── java

│   │   │   └── com

│   │   │       └── cognizant

│   │   │           └── loan

│   │   │               ├── LoanApplication.java

│   │   │               ├── controller

│   │   │               │      └── LoanController.java

│   │   │               └── model

│   │   │                      └── Loan.java

│   │   │

│   │   └── resources

│   │          └── application.properties

│   │

│   └── test

│

├── pom.xml

└── mvnw
```

---

# Source Code Explanation

Let us understand how each class contributes to the application.

---

# AccountApplication.java

This is the main class of the Account Microservice.

Responsibilities

- Starts Spring Boot
- Starts Embedded Tomcat
- Creates Spring Container
- Scans Components
- Loads Configuration
- Makes the REST API available

The application starts execution from this class.

```
public static void main()
```

is the first method executed.

---

# Account.java

This class represents the Account object.

It stores

```
Account Number

↓

Account Type

↓

Balance
```

It is called the **Model Class** because it models business data.

Whenever a request arrives,

Spring Boot creates an Account object,

fills its values,

and converts it into JSON.

---

# AccountController.java

This class exposes the REST API.

Example endpoint

```
GET

/accounts/{number}
```

Responsibilities

- Receive HTTP Request
- Read Path Variable
- Create Account Object
- Return JSON Response

This controller does not communicate with a database in this exercise.

Instead,

it creates a dummy object and returns it.

---

# LoanApplication.java

This is the starting point of the Loan Microservice.

Responsibilities

- Starts Spring Boot
- Creates Spring Context
- Starts Embedded Tomcat
- Loads Beans
- Makes REST APIs available

Although both projects contain an Application class,

they run independently.

---

# Loan.java

This class represents Loan information.

It stores

```
Loan Number

↓

Loan Type

↓

Loan Amount

↓

EMI

↓

Tenure
```

Spring Boot converts this object into JSON automatically.

---

# LoanController.java

This class provides the Loan REST API.

Example

```
GET

/loans/{number}
```

Responsibilities

- Receive Request
- Read Loan Number
- Create Loan Object
- Return JSON

---

# application.properties

This configuration file contains project settings.

For Account Service

```
server.port=8080
```

For Loan Service

```
server.port=8081
```

Using different ports allows both applications to run simultaneously.

---

# pom.xml

The pom.xml file is the heart of every Maven project.

It contains

- Dependencies
- Plugins
- Build Configuration
- Java Version
- Spring Boot Version

Whenever Maven executes

```
mvn clean install
```

it reads this file,

downloads dependencies,

compiles the project,

and creates the executable JAR.

---

# Complete Request Processing

Suppose a client opens

```
http://localhost:8080/accounts/1001
```

The request follows this sequence.

```
Client

↓

Browser

↓

HTTP Request

↓

Embedded Tomcat

↓

Dispatcher Servlet

↓

AccountController

↓

Account Object Created

↓

Jackson JSON Converter

↓

HTTP Response

↓

Browser
```

---

# Detailed Flow Explanation

## Step 1

The user enters

```
http://localhost:8080/accounts/1001
```

in the browser.

---

## Step 2

The browser sends an HTTP GET request.

```
GET

/accounts/1001
```

---

## Step 3

Embedded Tomcat receives the request.

Tomcat is the web server inside Spring Boot.

Without Tomcat,

the application cannot receive HTTP requests.

---

## Step 4

Tomcat forwards the request to

```
Dispatcher Servlet
```

Dispatcher Servlet is called

**Front Controller**.

Every request passes through it.

---

## Step 5

Dispatcher Servlet searches for the matching Controller.

It finds

```
AccountController
```

because it contains

```
@GetMapping("/accounts/{number}")
```

---

## Step 6

Spring extracts

```
1001
```

from the URL.

This value becomes

```
@PathVariable
```

---

## Step 7

Controller creates

```
Account Object
```

Example

```
Number

↓

1001

↓

Type

↓

Savings

↓

Balance

↓

234343
```

---

## Step 8

Controller returns

```
Account Object
```

instead of writing JSON manually.

---

## Step 9

Spring Boot internally calls

```
Jackson Object Mapper
```

Jackson converts

```
Java Object

↓

JSON
```

automatically.

Developers do not need to write JSON conversion code.

---

## Step 10

The JSON response is sent back to the browser.

Example

```json
{
  "number":"1001",
  "type":"Savings",
  "balance":234343
}
```

---

# Loan Request Flow

Exactly the same process occurs.

```
Client

↓

GET

↓

/loans/H100

↓

Tomcat

↓

Dispatcher Servlet

↓

Loan Controller

↓

Loan Object

↓

Jackson

↓

JSON

↓

Client
```

---

# Running the Applications

## Step 1

Start Account Microservice.

```
Run

↓

AccountApplication
```

Console

```
Tomcat started on port 8080
```

---

## Step 2

Start Loan Microservice.

```
Run

↓

LoanApplication
```

Console

```
Tomcat started on port 8081
```

Now both services are running independently.

---

# Testing the REST APIs

## Browser

Account

```
http://localhost:8080/accounts/1001
```

Loan

```
http://localhost:8081/loans/H100
```

---

## Postman

Method

```
GET
```

Account URL

```
http://localhost:8080/accounts/1001
```

Loan URL

```
http://localhost:8081/loans/H100
```

Click

```
Send
```

The JSON response is displayed.

---

# Expected Output

### Account Service

```json
{
  "number":"1001",
  "type":"Savings",
  "balance":234343
}
```

---

### Loan Service

```json
{
  "number":"H100",
  "type":"Car",
  "loan":400000,
  "emi":3258,
  "tenure":18
}
```

---

# How Spring Boot Converts Java Objects to JSON

Spring Boot internally performs the following steps.

```
Java Object

↓

@ResponseBody

↓

Jackson Library

↓

JSON

↓

HTTP Response
```

Because of this automatic conversion, developers simply return Java objects from controller methods without manually generating JSON.

---

# Key Concepts Learned in this Exercise

By completing this hands-on exercise, you have learned how to:

- Create multiple independent Spring Boot applications.
- Structure a project using Maven.
- Develop REST APIs using `@RestController`.
- Create model classes for business data.
- Configure different server ports using `application.properties`.
- Start multiple Spring Boot applications simultaneously.
- Test REST endpoints using a browser and Postman.
- Understand how Spring Boot processes HTTP requests internally.
- Prepare applications for future integration with **Eureka Discovery Server**, **API Gateway**, and the broader **Spring Cloud** ecosystem.

# Best Practices

Developing microservices is not only about creating multiple Spring Boot projects. To build reliable, maintainable, and scalable applications, developers should follow industry-standard best practices.

---

## 1. Follow the Single Responsibility Principle (SRP)

Each microservice should be responsible for only one business capability.

### Good Example

```
Account Service

↓

Only Account Operations
```

```
Loan Service

↓

Only Loan Operations
```

```
Payment Service

↓

Only Payment Operations
```

### Bad Example

```
Account Service

↓

Accounts

Loans

Payments

Cards

Notifications
```

A service with multiple responsibilities becomes difficult to maintain and scale.

---

## 2. Keep Services Independent

Every microservice should be developed, tested, and deployed independently.

Each service should have its own:

- Source Code
- Maven Project
- Configuration
- Dependencies
- Deployment

Avoid tightly coupling services.

---

## 3. Use REST APIs for Communication

Microservices should communicate using lightweight protocols such as HTTP/REST.

Example

```
Client

↓

GET /accounts/1001
```

Instead of sharing code directly, services expose REST endpoints.

---

## 4. Use Meaningful API URLs

REST endpoints should clearly describe the resource.

Good

```
/accounts/1001
```

```
/loans/H100
```

Bad

```
/getAccount
```

```
/showLoan
```

REST APIs should represent resources, not actions.

---

## 5. Use Proper HTTP Methods

| Method | Purpose |
|---------|---------|
| GET | Read Data |
| POST | Create Data |
| PUT | Update Data |
| DELETE | Delete Data |

Choosing the correct HTTP method makes APIs intuitive and follows REST principles.

---

## 6. Return JSON Responses

JSON is lightweight, human-readable, and widely supported.

Example

```json
{
  "number":"1001",
  "type":"Savings",
  "balance":234343
}
```

Avoid returning plain text for structured data.

---

## 7. Use Different Server Ports

Every microservice should run on its own port.

Example

```
Account Service

↓

8080
```

```
Loan Service

↓

8081
```

This prevents port conflicts and allows multiple services to run simultaneously.

---

## 8. Keep Configuration Outside the Code

Configuration values such as server ports and database credentials should be placed in:

```
application.properties
```

instead of hardcoding them in Java classes.

---

## 9. Keep Controllers Lightweight

Controllers should only:

- Receive requests
- Validate input
- Call business logic
- Return responses

Business logic should eventually be placed in service classes.

Although this exercise directly returns dummy data from the controller, enterprise applications follow a layered architecture.

---

## 10. Test Every API

Always test REST APIs using:

- Browser
- Postman
- Swagger (future exercises)

Testing ensures that endpoints return the expected response.

---

# Common Mistakes

Beginners often make the following mistakes while developing microservices.

---

## Mistake 1

Creating multiple modules inside one Spring Boot project instead of separate projects.

Correct approach:

```
Account Project
```

```
Loan Project
```

Each microservice must be an independent application.

---

## Mistake 2

Running all services on the same port.

Incorrect

```
Account → 8080

Loan → 8080
```

Result

```
Port Already In Use
```

Correct

```
Account → 8080

Loan → 8081
```

---

## Mistake 3

Combining multiple business functions into one service.

Example

```
Account Service

↓

Accounts

Loans

Payments
```

Each business capability should have its own microservice.

---

## Mistake 4

Hardcoding configuration values.

Incorrect

```java
int port = 8080;
```

Correct

```
application.properties
```

Configuration should always be externalized.

---

## Mistake 5

Returning plain text instead of structured JSON.

Incorrect

```
Savings Account
```

Correct

```json
{
  "number":"1001",
  "type":"Savings",
  "balance":234343
}
```

---

## Mistake 6

Ignoring Maven dependencies.

Always ensure that:

- `pom.xml` contains the required dependencies.
- Maven projects are reloaded after changes.
- The project builds successfully before running.

---

# Advantages of This Exercise

By implementing this project, you gain practical experience with:

- Creating multiple Spring Boot applications.
- Developing RESTful Web Services.
- Working with Maven.
- Understanding microservice boundaries.
- Running multiple applications simultaneously.
- Preparing for Spring Cloud.
- Building scalable software architectures.

---

# Real-World Applications

Microservices are widely used across industries.

### Banking

Separate services for:

- Accounts
- Loans
- Payments
- Cards
- Transactions

---

### E-Commerce

Separate services for:

- Products
- Orders
- Inventory
- Payments
- Shipping

---

### Social Media

Separate services for:

- Users
- Posts
- Comments
- Notifications
- Messaging

---

### Ride-Sharing

Separate services for:

- Driver Management
- Customer Management
- Ride Booking
- Payments
- Location Tracking

---

# Interview Questions

## 1. What is a Microservice?

A Microservice is a small, independent application that focuses on one specific business capability and communicates with other services using lightweight protocols such as HTTP/REST.

---

## 2. What are the advantages of Microservices over Monolithic Architecture?

- Independent deployment
- Independent scaling
- Better maintainability
- Fault isolation
- Technology flexibility
- Smaller codebases

---

## 3. Why do Microservices run on different ports?

Each microservice is an independent application with its own embedded server. Different ports prevent conflicts and allow multiple services to run simultaneously.

---

## 4. Why is Spring Boot preferred for Microservices?

Because it provides:

- Auto Configuration
- Embedded Tomcat
- Starter Dependencies
- Production-ready features
- Easy REST API development
- Seamless integration with Spring Cloud

---

## 5. What is the role of `@SpringBootApplication`?

It combines:

- `@Configuration`
- `@EnableAutoConfiguration`
- `@ComponentScan`

and serves as the entry point of a Spring Boot application.

---

## 6. What is `@RestController`?

`@RestController` marks a class as a REST controller. It receives HTTP requests and automatically returns Java objects as JSON responses.

---

## 7. Why do we use Maven?

Maven is used to:

- Manage dependencies
- Build projects
- Compile source code
- Run tests
- Package applications

---

## 8. What is Embedded Tomcat?

Embedded Tomcat is the web server included with Spring Boot. It allows applications to run without installing a separate application server.

---

## 9. How does Spring Boot convert Java objects into JSON?

Spring Boot uses the Jackson library internally to serialize Java objects into JSON before sending the HTTP response.

---

## 10. Why are separate Maven projects created for Account and Loan services?

Because each microservice should be independently developed, built, deployed, and scaled. Separate Maven projects help maintain this independence.

---

# Quick Revision

## Architecture

```
Client

↓

Account Service (8080)

↓

JSON Response
```

```
Client

↓

Loan Service (8081)

↓

JSON Response
```

---

## Technologies Used

- Java 21
- Spring Boot 3
- Maven
- Spring Web
- Embedded Tomcat
- REST APIs
- Postman

---

## REST Endpoints

### Account

```
GET

/accounts/{number}
```

### Loan

```
GET

/loans/{number}
```

---

## Components

```
Application Class

↓

REST Controller

↓

Model Class

↓

application.properties

↓

pom.xml
```

---

## Internal Request Flow

```
Client

↓

Tomcat

↓

Dispatcher Servlet

↓

Controller

↓

Java Object

↓

Jackson

↓

JSON

↓

Client
```

---

# Conclusion

In this hands-on exercise, we successfully built two independent Spring Boot microservices: **Account Service** and **Loan Service**. Each service was developed as a separate Maven project with its own configuration, REST controller, model classes, and embedded Tomcat server.

Through this implementation, we learned the fundamental concepts of **Microservices Architecture**, including service independence, REST-based communication, separate deployments, and scalability. We also explored how Spring Boot simplifies microservice development by providing auto-configuration, embedded servers, and automatic JSON conversion.

This exercise lays the groundwork for the upcoming Spring Cloud topics. In the next hands-on exercises, these services will be enhanced by integrating them with a **Eureka Discovery Server** for service registration and discovery, followed by an **API Gateway** for centralized request routing and management. Together, these concepts form the foundation of modern enterprise-grade distributed systems built using the Spring Boot and Spring Cloud ecosystem.
