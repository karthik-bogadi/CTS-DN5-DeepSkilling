# Create Eureka Discovery Server and Register Microservices

## Week 4 – Microservices with Spring Boot 3 and Spring Cloud

---

# Introduction

As organizations adopt Microservices Architecture, applications are divided into many independent services. While this architecture provides flexibility and scalability, it also introduces a new challenge:

> **How can one microservice locate and communicate with another?**

In a small application containing only two services, developers may manually specify the address (URL) of another service.

Example

```
Account Service

↓

http://localhost:8081
```

Although this works for small applications, it becomes impractical when hundreds of microservices are deployed across multiple servers.

To solve this problem, Spring Cloud provides **Service Discovery**, where every microservice registers itself with a central server known as the **Eureka Discovery Server**.

Instead of remembering IP addresses and port numbers, microservices simply ask Eureka for the location of another service.

This exercise introduces one of the most important components of the Spring Cloud ecosystem:

- Eureka Discovery Server
- Eureka Client
- Service Registration
- Service Discovery

By the end of this exercise, the following applications will be running:

- Eureka Discovery Server
- Account Microservice
- Loan Microservice

The Account and Loan services automatically register themselves with Eureka, and the Eureka Dashboard displays all registered services.

---

# Learning Objectives

After completing this exercise, you will be able to:

- Understand Spring Cloud.
- Understand Service Discovery.
- Learn why Eureka Server is required.
- Create a Eureka Discovery Server.
- Register Spring Boot applications with Eureka.
- Configure Eureka Clients.
- View registered services in the Eureka Dashboard.
- Understand the service registration process.
- Prepare microservices for inter-service communication.

---

# Prerequisites

Before starting this exercise, you should have completed:

- Java 21
- Maven
- Spring Boot 3.5.x
- Account Microservice
- Loan Microservice
- Basic REST APIs
- Basic Spring Boot knowledge

---

# What is Spring Cloud?

## Definition

**Spring Cloud** is a collection of tools and libraries built on top of Spring Boot that helps developers build distributed systems and Microservices.

Spring Boot simplifies application development.

Spring Cloud simplifies communication between multiple Spring Boot applications.

---

# Why Was Spring Cloud Introduced?

Imagine a banking application.

```
Bank Application

│

├── Account Service

├── Loan Service

├── Payment Service

├── Card Service

├── Customer Service

├── Insurance Service

├── Notification Service

├── Report Service
```

Initially,

every service knows the address of every other service.

Example

```
Account Service

↓

http://localhost:8081
```

```
Payment Service

↓

http://localhost:8083
```

```
Loan Service

↓

http://localhost:8082
```

Now imagine

- Loan Service moves to another server.
- Payment Service changes its port.
- Customer Service starts multiple instances.

Every application must be updated manually.

This quickly becomes impossible in enterprise applications.

Spring Cloud solves this problem.

---

# What is a Distributed System?

## Definition

A **Distributed System** is a collection of multiple independent applications that work together to provide a single business solution.

Instead of one large application,

many small applications collaborate.

Example

```
Internet Banking

↓

Account Service

↓

Loan Service

↓

Payment Service

↓

Notification Service
```

Although these services are independent,

users experience them as one application.

---

# Challenges in Distributed Systems

When applications are distributed,

new problems arise.

Some of them are

- Service Discovery
- Load Balancing
- Configuration Management
- Fault Tolerance
- API Gateway
- Distributed Logging
- Monitoring
- Security

Spring Cloud provides solutions for all these problems.

---

# What is Service Discovery?

## Definition

**Service Discovery** is the process of automatically locating the network location of available microservices.

Instead of hardcoding

```
http://localhost:8081
```

applications ask a discovery server

```
Where is Loan Service?
```

The Discovery Server returns the current location.

---

# Why Do We Need Service Discovery?

Suppose we have three services.

```
Account Service

↓

Loan Service

↓

Payment Service
```

Initially,

Loan Service runs on

```
localhost:8081
```

Later,

the server changes.

```
localhost:9095
```

Every application using Loan Service must now be modified.

This creates maintenance problems.

With Service Discovery,

applications never need to know the physical address.

They only know the service name.

Example

```
LOAN-SERVICE
```

The Discovery Server resolves it automatically.

---

# Without Eureka

Communication looks like this.

```
Account Service

↓

http://localhost:8081

↓

Loan Service
```

If Loan Service changes,

everything breaks.

---

# With Eureka

Communication becomes

```
Account Service

↓

Ask Eureka

↓

Where is Loan Service?

↓

Eureka replies

↓

localhost:8081

↓

Loan Service
```

If Loan Service later changes to another server,

Eureka updates its registry automatically.

Account Service continues working without modification.

---

# What is Eureka?

## Definition

**Eureka** is a Service Discovery Server developed by Netflix and supported by Spring Cloud.

Its primary responsibility is

- Register services
- Maintain service information
- Allow service lookup
- Remove unavailable services

Think of Eureka as a **phone directory for microservices**.

Instead of searching manually,

applications ask Eureka for service locations.

---

# Real-World Analogy

Imagine a college.

There are many departments.

```
Principal Office

↓

Computer Science

↓

Mechanical

↓

Civil

↓

IT

↓

ECE
```

Suppose a student wants to visit the IT Department.

Instead of searching the campus,

the student asks the Principal Office.

```
Where is IT Department?
```

The office provides the location.

Similarly,

microservices ask Eureka

```
Where is ACCOUNT-SERVICE?
```

Eureka returns the address.

---

# What is Eureka Discovery Server?

## Definition

A **Eureka Discovery Server** is a centralized registry where all microservices register themselves when they start.

It maintains information such as

- Service Name
- IP Address
- Port Number
- Status
- Health Information

Every running microservice periodically informs Eureka that it is still alive.

---

# Responsibilities of Eureka Server

The Eureka Server performs several important tasks.

### 1. Service Registration

Whenever a microservice starts,

it registers itself.

Example

```
ACCOUNT-SERVICE

↓

localhost:8080
```

---

### 2. Service Discovery

Other services ask Eureka

```
Where is ACCOUNT-SERVICE?
```

Eureka returns the address.

---

### 3. Health Monitoring

Every registered service periodically sends a heartbeat.

```
ACCOUNT-SERVICE

↓

Heartbeat

↓

Every 30 Seconds
```

If the heartbeat stops,

Eureka marks the service as unavailable.

---

### 4. Service Registry

Eureka stores a registry of all running services.

Example

```
ACCOUNT-SERVICE

↓

localhost:8080

↓

UP
```

```
LOAN-SERVICE

↓

localhost:8081

↓

UP
```

---

# What is a Eureka Client?

## Definition

A **Eureka Client** is any Spring Boot application that registers itself with the Eureka Discovery Server.

Examples

```
Account Service
```

```
Loan Service
```

```
Payment Service
```

All of these act as Eureka Clients.

---

# Service Registration Process

When a microservice starts,

the following sequence occurs.

```
Start Service

↓

Read application.properties

↓

Connect to Eureka Server

↓

Register Service

↓

Heartbeat Started

↓

Service Available
```

The registration is automatic.

Developers only need to include the required dependency and configuration.

---

# High-Level Architecture

```
                    Client

                       │

          ┌────────────┴────────────┐

          ▼                         ▼

   Account Service            Loan Service

          │                         │

          └────────────┬────────────┘
                       │
                       ▼

            Eureka Discovery Server

                 Port : 8761
```

The Eureka Server sits at the center of the system. Every microservice registers itself with Eureka and can later discover other services without hardcoding their addresses.

---

# Exercise Overview

In this hands-on exercise, we will:

- Create a Eureka Discovery Server.
- Configure it using Spring Cloud Netflix Eureka Server.
- Start the Eureka Dashboard on port **8761**.
- Register the **Account Microservice** as a Eureka Client.
- Register the **Loan Microservice** as a Eureka Client.
- Verify that both services appear in the Eureka Dashboard with status **UP**.

This exercise forms the foundation for the next Spring Cloud topics, including **API Gateway**, **Load Balancing**, and **Inter-Service Communication**, where services will discover and communicate with one another using Eureka instead of fixed URLs.

# Project Architecture

In this exercise, we create **three independent Spring Boot applications**.

1. Eureka Discovery Server
2. Account Microservice
3. Loan Microservice

Unlike the previous exercise, these applications are now connected through **Service Discovery**.

Instead of communicating using hardcoded URLs, every microservice first registers itself with Eureka.

---

# Overall Architecture

```
                        Client

                           │
                           │
               HTTP Request
                           │
                           ▼

                 Account Service
                     (8080)

                           │
                           │
          Ask Eureka for Other Services
                           │
                           ▼

                Eureka Discovery Server
                       (8761)

                           ▲
                           │
          Register Services │
                           │
          ┌────────────────┴──────────────┐
          │                               │
          ▼                               ▼

   Account Service                 Loan Service
       (8080)                         (8081)
```

Notice that

- Eureka does **not** process business requests.
- Eureka only stores service information.
- Actual communication happens directly between microservices.

---

# Components of the Architecture

The architecture contains three important components.

---

## 1. Eureka Discovery Server

This is the heart of the system.

Responsibilities

- Maintains Service Registry
- Accepts Registration Requests
- Stores Service Details
- Monitors Running Services
- Removes Failed Services
- Helps Services Discover Each Other

Think of Eureka as a **Directory Service**.

---

## 2. Eureka Clients

Every Spring Boot application that registers itself with Eureka is called a Eureka Client.

Examples

```
Account Service
```

```
Loan Service
```

Later,

```
Payment Service
```

```
Customer Service
```

```
Notification Service
```

can also register.

---

## 3. Service Registry

The Service Registry is a list maintained by Eureka.

Example

```
ACCOUNT-SERVICE

↓

localhost

↓

8080

↓

UP
```

```
LOAN-SERVICE

↓

localhost

↓

8081

↓

UP
```

Whenever a service starts,

its information is added here.

---

# Creating the Eureka Discovery Server

The first application created in this exercise is

```
Eureka Discovery Server
```

Unlike Account and Loan,

this application does not expose business APIs.

Its only responsibility is

```
Register Services

↓

Maintain Registry

↓

Provide Service Discovery
```

---

# Project Structure

```
eureka-discovery-server

│

├── src

│   ├── main

│   │

│   ├── java

│   │

│   │   └── com

│   │       └── cognizant

│   │           └── eureka_discovery_server

│   │

│   │               └── EurekaDiscoveryServerApplication.java

│   │

│   └── resources

│

│       └── application.properties

│

├── pom.xml

│

└── mvnw
```

---

# Understanding pom.xml

The **pom.xml** file tells Maven

- Which dependencies are required
- Which plugins are required
- Which Java version is used

For Eureka Server,

important dependencies include

```
Spring Boot Starter Web
```

```
Spring Cloud Netflix Eureka Server
```

```
Spring Boot DevTools
```

Without these dependencies,

the application cannot become a Discovery Server.

---

# Why Do We Add Spring Cloud Dependency?

Spring Boot alone knows nothing about

```
Service Discovery
```

or

```
Service Registry
```

Adding

```
spring-cloud-starter-netflix-eureka-server
```

provides all the classes required to create

```
Eureka Server

↓

Dashboard

↓

Registry

↓

Heartbeat Handling
```

---

# Main Application Class

The entry point is

```
EurekaDiscoveryServerApplication
```

This class contains

```
@SpringBootApplication
```

and

```
@EnableEurekaServer
```

---

# What Does @EnableEurekaServer Do?

Normally,

Spring Boot starts

```
Embedded Tomcat
```

After adding

```
@EnableEurekaServer
```

Spring Boot additionally creates

- Service Registry
- Eureka Dashboard
- Heartbeat Listener
- Registration Endpoints
- Discovery APIs

This single annotation transforms a normal Spring Boot application into a Discovery Server.

---

# application.properties of Eureka Server

Important configurations include

```
server.port=8761
```

This tells Spring Boot

```
Run Eureka Server

↓

Port 8761
```

---

Another important configuration is

```
spring.application.name=eureka-server
```

This assigns a logical name to the application.

---

Since Eureka Server should not register itself,

we configure

```
eureka.client.register-with-eureka=false
```

Meaning

```
Do NOT register

↓

I am the Registry
```

---

Similarly,

```
eureka.client.fetch-registry=false
```

means

```
Do NOT fetch registry

↓

I already maintain it
```

---

# Why Port 8761?

By convention,

Netflix Eureka uses

```
8761
```

Although any port can be used,

8761 is the standard and is recognized by most tutorials and enterprise projects.

---

# Running Eureka Server

When the application starts,

the following sequence occurs.

```
Run Application

↓

Create Spring Context

↓

Start Embedded Tomcat

↓

Initialize Eureka

↓

Create Registry

↓

Start Dashboard

↓

Listening on Port 8761
```

Now,

visiting

```
http://localhost:8761
```

opens the Eureka Dashboard.

---

# Understanding the Eureka Dashboard

Initially,

the dashboard looks similar to

```
Eureka Server

↓

No Instances Available
```

This is expected because

no microservices have registered yet.

The Discovery Server is running,

but the registry is empty.

---

# Registering Account Microservice

The next step is converting the Account application into a Eureka Client.

To achieve this,

we add the dependency

```
spring-cloud-starter-netflix-eureka-client
```

Now,

Spring Boot gains the ability to communicate with Eureka.

---

# Configuring Account Service

The following properties are important.

```
spring.application.name=ACCOUNT-SERVICE
```

This is the logical service name.

Notice that

applications communicate using

```
ACCOUNT-SERVICE
```

instead of

```
localhost:8080
```

---

Next,

configure

```
server.port=8080
```

to run the Account Service.

---

Finally,

tell the application where Eureka is located.

```
eureka.client.service-url.defaultZone

↓

http://localhost:8761/eureka
```

Now,

Account Service knows where to register.

---

# Registering Loan Microservice

Loan Service follows exactly the same steps.

Add

```
spring-cloud-starter-netflix-eureka-client
```

Configure

```
spring.application.name=LOAN-SERVICE
```

Configure

```
server.port=8081
```

Configure

```
eureka.client.service-url.defaultZone

↓

http://localhost:8761/eureka
```

Now,

Loan Service also registers automatically.

---

# Registration Process

The complete registration process is

```
Start Account Service

↓

Read application.properties

↓

Connect to Eureka

↓

Register

↓

Heartbeat Started

↓

Visible on Dashboard
```

Loan Service performs exactly the same process.

---

# Dashboard After Registration

After both services start,

the Eureka Dashboard displays

```
Registered Instances

↓

ACCOUNT-SERVICE

↓

UP

↓

localhost:8080
```

```
LOAN-SERVICE

↓

UP

↓

localhost:8081
```

The status **UP** indicates that the services are alive and continuously sending heartbeats to Eureka.

---

# Heartbeat Mechanism

Registration is not enough.

Every Eureka Client periodically sends a heartbeat.

```
Account Service

↓

Heartbeat

↓

Every 30 Seconds

↓

Eureka
```

Loan Service does the same.

If Eureka stops receiving heartbeats,

it assumes the service has failed and removes it from the active registry.

This mechanism ensures that other microservices always receive the addresses of healthy and available services.

# Internal Working of Eureka

Now let us understand what actually happens behind the scenes when the applications start.

Many beginners think that Eureka continuously communicates with every service.

Actually, the process is much simpler.

When a microservice starts,

it performs the following steps automatically.

```
Start Application

↓

Read application.properties

↓

Read Eureka URL

↓

Connect to Eureka Server

↓

Register Service

↓

Receive Registration Success

↓

Start Sending Heartbeats

↓

Ready to Serve Requests
```

Everything happens automatically because of the Spring Cloud Eureka Client dependency.

---

# Complete Working Flow

Suppose we start all three applications.

### Step 1

Start Eureka Server.

```
Run

↓

EurekaDiscoveryServerApplication

↓

Port 8761

↓

Registry Created
```

---

### Step 2

Start Account Service.

```
Run

↓

AccountApplication

↓

Port 8080
```

Immediately,

Account Service contacts Eureka.

```
ACCOUNT-SERVICE

↓

Register

↓

localhost:8080
```

Eureka stores this information.

---

### Step 3

Start Loan Service.

```
Run

↓

LoanApplication

↓

Port 8081
```

Loan Service also contacts Eureka.

```
LOAN-SERVICE

↓

Register

↓

localhost:8081
```

Now Eureka contains two registered services.

---

### Step 4

Open Dashboard

```
http://localhost:8761
```

Dashboard displays

```
Registered Instances

↓

ACCOUNT-SERVICE

↓

UP

↓

LOAN-SERVICE

↓

UP
```

This confirms successful registration.

---

# Registration Sequence Diagram

```
                  Eureka Server

                        ▲

                        │

      Register          │

                        │

Account Service─────────┘



                        ▲

                        │

      Register          │

                        │

Loan Service────────────┘
```

Both services independently register with Eureka.

---

# What Information Does Eureka Store?

For every service,

Eureka stores information similar to

```
Service Name

↓

ACCOUNT-SERVICE
```

```
Host

↓

localhost
```

```
Port

↓

8080
```

```
Status

↓

UP
```

```
Lease Duration

↓

90 Seconds
```

```
Heartbeat Interval

↓

30 Seconds
```

This information helps Eureka determine whether the service is healthy.

---

# Heartbeat Process

After registration,

the service sends a heartbeat.

```
Account Service

↓

Heartbeat

↓

Eureka

↓

Heartbeat

↓

Eureka

↓

Heartbeat

↓

Eureka
```

This process continues while the application is running.

---

# What Happens if a Service Stops?

Suppose Loan Service crashes.

```
Loan Service

↓

Stopped
```

No heartbeat reaches Eureka.

```
Heartbeat Missing

↓

Timeout

↓

Service Removed
```

The dashboard changes from

```
UP
```

to

```
DOWN

or

Removed
```

Now other services will no longer receive Loan Service from Eureka.

---

# Service Discovery Process

Suppose Account Service wants Loan Service.

Instead of calling

```
localhost:8081
```

it asks Eureka.

```
Account Service

↓

Where is LOAN-SERVICE?

↓

Eureka

↓

localhost:8081

↓

Account Service

↓

Loan Service
```

This is called

```
Service Discovery
```

No hardcoded URLs are required.

---

# Why Service Names Are Better than URLs

Imagine Loan Service moves.

Old Address

```
localhost:8081
```

New Address

```
192.168.1.50:9001
```

Without Eureka,

every application must change.

With Eureka,

only Loan Service updates its registration.

Other services continue using

```
LOAN-SERVICE
```

Nothing else changes.

---

# How Spring Boot Finds Eureka

When the application starts,

Spring Boot reads

```
application.properties
```

Example

```
eureka.client.service-url.defaultZone

↓

http://localhost:8761/eureka
```

Spring Boot automatically creates

```
Discovery Client

↓

Connect

↓

Register
```

No manual registration code is required.

---

# Internal Components of Eureka

Internally,

Eureka consists of several modules.

```
Eureka Server

│

├── Registry

├── Heartbeat Manager

├── Dashboard

├── REST APIs

├── Instance Manager

└── Discovery APIs
```

Each module performs a specific task.

---

# Request Flow During Registration

```
Account Service

↓

Read Configuration

↓

Create Discovery Client

↓

HTTP POST

↓

Eureka Server

↓

Store Instance

↓

Return Success

↓

Registration Complete
```

The same flow is used for every microservice.

---

# Dashboard Explanation

Open

```
http://localhost:8761
```

You will see several sections.

---

## System Status

Displays

```
UP
```

if Eureka Server is running successfully.

---

## DS Replicas

Shows other Eureka Servers.

In this exercise,

there is only one Eureka Server.

So it may display

```
Unavailable
```

This is normal.

---

## Instances Currently Registered

This is the most important section.

Initially

```
No Instances Available
```

After Account starts

```
ACCOUNT-SERVICE

↓

UP
```

After Loan starts

```
ACCOUNT-SERVICE

↓

UP

LOAN-SERVICE

↓

UP
```

---

## Lease Information

Each instance displays

```
Lease Renewal Interval

↓

30 Seconds
```

```
Lease Expiration

↓

90 Seconds
```

If renewal stops,

the service is removed.

---

# API Testing

## Test Eureka Dashboard

```
GET

http://localhost:8761
```

Browser displays

```
Eureka Dashboard
```

---

## Test Account Service

```
GET

http://localhost:8080/accounts/1001
```

Expected Output

```json
{
  "number":"1001",
  "type":"Savings",
  "balance":234343
}
```

---

## Test Loan Service

```
GET

http://localhost:8081/loans/H100
```

Expected Output

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

# What We Achieved in This Exercise

After completing this hands-on exercise, we successfully:

- Created a Eureka Discovery Server.
- Converted Account Service into a Eureka Client.
- Converted Loan Service into a Eureka Client.
- Registered both microservices with Eureka.
- Verified the registered services using the Eureka Dashboard.
- Understood how Eureka performs service registration and health monitoring.
- Learned how microservices discover each other using service names instead of hardcoded URLs.

This exercise provides the foundation for the next stage of Spring Cloud, where an **API Gateway** will route requests to services using Eureka, and microservices will communicate dynamically through **Service Discovery** instead of fixed network addresses.

# Best Practices

Developing microservices using Spring Cloud and Eureka requires more than simply creating a Discovery Server. Following best practices ensures that applications remain scalable, maintainable, and reliable.

---

## 1. Keep Eureka Highly Available

In enterprise environments, Eureka Server should not become a single point of failure.

Instead of running only one Eureka Server,

use multiple Eureka Servers.

Example

```
Eureka Server 1

↓

Eureka Server 2

↓

Eureka Server 3
```

Each server replicates registry information to the others.

This ensures service discovery continues even if one server becomes unavailable.

---

## 2. Use Meaningful Service Names

Every microservice should have a clear and unique application name.

Good Examples

```
ACCOUNT-SERVICE
```

```
LOAN-SERVICE
```

```
PAYMENT-SERVICE
```

Bad Examples

```
Service1
```

```
TestApp
```

Meaningful names improve readability and simplify service discovery.

---

## 3. Avoid Hardcoding URLs

Never write

```
http://localhost:8081
```

inside Java code.

Instead,

use the service name.

Example

```
LOAN-SERVICE
```

Eureka will automatically provide the correct address.

---

## 4. Keep Microservices Independent

Each microservice should have

- Separate Maven Project
- Separate Configuration
- Separate Deployment
- Separate Database (recommended)
- Separate Runtime

Independent services are easier to maintain and scale.

---

## 5. Monitor Service Health

Always ensure services expose health information.

Spring Boot Actuator can be used for monitoring.

Example endpoints

```
/actuator/health
```

```
/actuator/info
```

This helps Eureka and administrators determine whether a service is healthy.

---

## 6. Configure Proper Heartbeat Intervals

Heartbeat intervals should be configured carefully.

Very short intervals increase network traffic.

Very long intervals delay failure detection.

Default values are suitable for most applications.

---

## 7. Keep Configuration External

Store configuration values in

```
application.properties
```

or

```
application.yml
```

instead of Java code.

Examples include

- Server Port
- Eureka URL
- Database Credentials
- Logging Configuration

---

## 8. Use Consistent Naming Conventions

Maintain consistency across all microservices.

Example

```
ACCOUNT-SERVICE
```

```
LOAN-SERVICE
```

```
PAYMENT-SERVICE
```

Avoid mixing uppercase, lowercase, abbreviations, and inconsistent naming.

---

## 9. Test Registration Before Development

Always verify that

```
Status

↓

UP
```

appears in the Eureka Dashboard before implementing inter-service communication.

If registration fails,

communication will also fail.

---

## 10. Maintain Separate Configuration for Each Service

Every service should have its own

```
application.properties
```

Example

Account Service

```
server.port=8080
```

Loan Service

```
server.port=8081
```

Eureka Server

```
server.port=8761
```

This avoids configuration conflicts.

---

# Common Mistakes

Many beginners encounter similar issues while working with Eureka.

---

## Mistake 1

Forgetting to add Eureka Client dependency.

Without

```
spring-cloud-starter-netflix-eureka-client
```

the application never registers.

---

## Mistake 2

Missing Spring Cloud BOM

Without

```
spring-cloud-dependencies
```

Maven cannot determine dependency versions.

Result

```
Dependency Version Missing
```

---

## Mistake 3

Incorrect Eureka URL

Wrong

```
localhost:8080/eureka
```

Correct

```
http://localhost:8761/eureka
```

Always verify the Discovery Server port.

---

## Mistake 4

Using Same Port for Multiple Services

Incorrect

```
Account

↓

8080

Loan

↓

8080
```

Result

```
Port Already In Use
```

Correct

```
Account

↓

8080

Loan

↓

8081
```

---

## Mistake 5

Forgetting

```
@EnableEurekaServer
```

Without this annotation,

the application behaves like a normal Spring Boot application instead of a Discovery Server.

---

## Mistake 6

Not Reloading Maven

After modifying

```
pom.xml
```

always

```
Reload Maven

↓

Download Dependencies

↓

Build Again
```

---

## Mistake 7

Ignoring Dashboard Status

Always verify

```
UP
```

before testing services.

If the dashboard shows

```
DOWN
```

or

```
No Instances Available
```

the service registration is unsuccessful.

---

## Mistake 8

Hardcoding Service URLs

Instead of

```
http://localhost:8081
```

use

```
LOAN-SERVICE
```

This allows Eureka to perform service discovery dynamically.

---

# Advantages of Eureka

Using Eureka provides several benefits.

- Automatic Service Registration
- Automatic Service Discovery
- Dynamic Service Location
- Reduced Configuration
- Improved Scalability
- Better Fault Tolerance
- Simplified Communication
- Easy Monitoring
- Centralized Service Registry

---

# Real-World Applications

Eureka is commonly used in enterprise systems.

### Banking

```
Account Service

↓

Loan Service

↓

Payment Service

↓

Card Service
```

All services register with Eureka.

---

### E-Commerce

```
Product Service

↓

Inventory Service

↓

Order Service

↓

Payment Service

↓

Shipping Service
```

---

### Healthcare

```
Patient Service

↓

Doctor Service

↓

Appointment Service

↓

Billing Service
```

---

### Travel Booking

```
Flight Service

↓

Hotel Service

↓

Payment Service

↓

Booking Service
```

All services communicate through Service Discovery.

---

# Interview Questions

## 1. What is Eureka?

Eureka is a Service Discovery Server developed by Netflix and supported by Spring Cloud. It maintains a registry of available microservices and enables service discovery.

---

## 2. What is Service Discovery?

Service Discovery is the process of automatically locating the network location of available microservices instead of using hardcoded URLs.

---

## 3. What is the difference between Eureka Server and Eureka Client?

| Eureka Server | Eureka Client |
|---------------|---------------|
| Maintains Service Registry | Registers with Eureka |
| Provides Service Discovery | Uses Service Discovery |
| Stores Instance Information | Sends Heartbeats |
| Central Registry | Business Application |

---

## 4. Why is Eureka needed?

Eureka eliminates hardcoded service URLs and enables automatic registration and discovery of microservices, making applications easier to scale and maintain.

---

## 5. What is the purpose of `@EnableEurekaServer`?

It converts a normal Spring Boot application into a Eureka Discovery Server by enabling service registry and discovery functionality.

---

## 6. What is a Heartbeat in Eureka?

A heartbeat is a periodic signal sent by a Eureka Client to indicate that it is still running and healthy.

---

## 7. What happens if a service stops sending heartbeats?

Eureka marks the service as unavailable and eventually removes it from the registry so that other services do not attempt to communicate with it.

---

## 8. Which port is commonly used by Eureka Server?

By convention,

```
8761
```

is the default port used by Eureka Server.

---

## 9. Can multiple services register with the same Eureka Server?

Yes.

A single Eureka Server can register and manage many microservices simultaneously.

---

## 10. Why do microservices use service names instead of URLs?

Service names remain constant even if the underlying IP address or port changes. Eureka resolves the service name to the current network location automatically.

---

# Quick Revision

## Components

```
Eureka Discovery Server

↓

Account Service

↓

Loan Service
```

---

## Ports

```
Eureka Server

↓

8761
```

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

---

## Registration Flow

```
Start Service

↓

Read Configuration

↓

Connect to Eureka

↓

Register

↓

Heartbeat

↓

UP
```

---

## Discovery Flow

```
Microservice

↓

Ask Eureka

↓

Get Service Address

↓

Communicate
```

---

## Technologies Used

- Java 21
- Spring Boot 3
- Spring Cloud
- Netflix Eureka
- Maven
- Embedded Tomcat
- REST APIs
- Postman

---

## Key Annotations

| Annotation | Purpose |
|------------|---------|
| `@SpringBootApplication` | Marks the main Spring Boot application class |
| `@EnableEurekaServer` | Enables Eureka Discovery Server |
| `@RestController` | Creates REST APIs |
| `@GetMapping` | Maps HTTP GET requests |

---

# Summary

In this hands-on exercise, we successfully implemented a **Eureka Discovery Server** and registered two independent Spring Boot microservices: **Account Service** and **Loan Service**. By integrating Spring Cloud Netflix Eureka, each microservice automatically registered itself with the Discovery Server, periodically sent heartbeats, and became visible in the Eureka Dashboard with the status **UP**.

This exercise introduced the fundamental concepts of **Service Discovery**, **Service Registry**, **Eureka Server**, and **Eureka Client**, demonstrating how microservices can locate one another without relying on hardcoded URLs. We also explored the registration process, heartbeat mechanism, service health monitoring, and the role of the Eureka Dashboard in managing registered services.

Completing this exercise establishes a strong foundation for building enterprise-grade distributed systems. In the upcoming exercises, these registered microservices will be integrated with an **API Gateway**, enabling centralized request routing, dynamic load balancing, and seamless inter-service communication through the Spring Cloud ecosystem.
