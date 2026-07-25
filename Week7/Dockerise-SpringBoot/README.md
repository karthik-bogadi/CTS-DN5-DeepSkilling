# Docker for Spring Boot Developers - Complete Beginner README

---

# Table of Contents

1. Why Docker?
2. Problems Before Docker
3. What is Docker?
4. Docker Architecture
5. Docker Image
6. Docker Container
7. Image vs Container
8. Docker Engine
9. Docker Workflow
10. Docker Installation
11. Basic Docker Commands
12. Dockerfile
13. Dockerfile Instructions
14. Docker Build Process
15. Docker Run Process
16. Port Mapping
17. EXPOSE Instruction
18. WORKDIR Instruction
19. ENTRYPOINT Instruction
20. CMD Instruction
21. ENTRYPOINT vs CMD
22. Docker Layers
23. Complete Spring Boot Docker Workflow
24. Practical Example
25. Important Interview Questions
26. Quick Revision Notes

---

# 1. Why Docker?

Docker was created to solve the famous problem:

```text
"It works on my machine."
```

### Example

Developer Machine

```text
Java 21
MySQL 8
Windows
```

Application works.

---

Tester Machine

```text
Java 17
No MySQL
Linux
```

Application fails.

---

Problem:

Different environments cause application failures.

---

# Docker Solution

Docker packages everything together:

```text
Application Code
+
Dependencies
+
Runtime
+
Configurations
```

into a single package called:

```text
Docker Image
```

Now application behaves the same everywhere.

---

# 2. What is Docker?

Docker is a platform that allows developers to package applications and run them inside isolated environments called containers.

---

# Real World Analogy

Without Docker:

```text
Send Recipe
Buy Ingredients
Cook Yourself
```

With Docker:

```text
Ready-to-Eat Food Package
```

Docker packages everything required to run an application.

---

# 3. Docker Architecture

```text
Developer
    ↓
Docker CLI
    ↓
Docker Engine
    ↓
Containers
```

---

# Components

### Docker CLI

Commands used by developers.

Examples:

```bash
docker build
docker run
docker ps
```

---

### Docker Engine

Responsible for:

```text
Creating Containers
Starting Containers
Stopping Containers
Managing Images
```

---

### Container

Running application.

---

# 4. Docker Image

## Definition

A Docker Image is a read-only blueprint used to create containers.

---

# Java Analogy

Java:

```java
class Student {}
```

Docker:

```text
student-app-image
```

Both are templates.

Neither is running.

---

# Image Contains

```text
Application Code
Dependencies
Runtime
Libraries
Configuration
```

---

# Example

```text
openjdk:21
```

contains:

```text
Java Runtime
Required Libraries
Linux Environment
```

---

# 5. Docker Container

## Definition

A Docker Container is a running instance of a Docker Image.

---

# Java Analogy

```java
Student s1 = new Student();
```

Student = Image

s1 = Container

---

# Example

Image:

```text
student-app-image
```

Container:

```text
student-app-container
```

---

# Multiple Containers

One image can create multiple containers.

```text
student-app-image
        ↓
Container 1
Container 2
Container 3
```

---

# 6. Image vs Container

| Image       | Container          |
| ----------- | ------------------ |
| Blueprint   | Running Instance   |
| Class       | Object             |
| Read Only   | Writable           |
| Not Running | Running            |
| Template    | Actual Application |

---

# 7. Docker Engine

Docker Engine is the core service of Docker.

---

Example:

```bash
docker run nginx
```

Docker Engine performs:

```text
Download Image
Create Container
Start Container
```

---

# 8. Docker Workflow

```text
Spring Boot Project
        ↓
Dockerfile
        ↓
docker build
        ↓
Docker Image
        ↓
docker run
        ↓
Docker Container
        ↓
Running Application
```

---

# 9. Docker Installation

Install:

```text
Docker Desktop
```

Contains:

```text
Docker Engine
Docker CLI
Docker Compose
```

---

Verify:

```bash
docker --version
```

---

# 10. Basic Docker Commands

## Check Version

```bash
docker --version
```

---

## View Images

```bash
docker images
```

---

## View Running Containers

```bash
docker ps
```

---

## View All Containers

```bash
docker ps -a
```

---

## Download Image

```bash
docker pull mysql
```

---

## Run Container

```bash
docker run nginx
```

---

## Stop Container

```bash
docker stop container-id
```

---

## Start Existing Container

```bash
docker start container-id
```

Used when:

```text
Container already exists
Container was stopped
Need to start again
```

---

## Remove Container

```bash
docker rm container-id
```

---

## Remove Image

```bash
docker rmi image-id
```

---

# 11. How Containers Are Created

Most common:

```bash
docker run image-name
```

Internally:

```text
docker create image-name
docker start container-id
```

---

Manual approach:

Create only:

```bash
docker create nginx
```

Start later:

```bash
docker start container-id
```

---

# 12. Dockerfile

## Definition

A Dockerfile is a text file containing instructions to create a Docker Image.

---

Think:

```text
pom.xml → Maven Instructions

Dockerfile → Docker Instructions
```

---

# Example Dockerfile

```dockerfile
FROM openjdk:21

WORKDIR /app

COPY target/app.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java","-jar","app.jar"]
```

---

# 13. Dockerfile Instructions

---

## FROM

```dockerfile
FROM openjdk:21
```

Meaning:

```text
Start with Java 21 Environment
```

Base Image.

Mandatory in most Dockerfiles.

---

## WORKDIR

```dockerfile
WORKDIR /app
```

Meaning:

```text
Create /app folder
Move into /app folder
```

Future operations happen inside:

```text
/app
```

---

## COPY

```dockerfile
COPY target/app.jar app.jar
```

Meaning:

```text
Copy JAR from local machine
Into Docker Image
```

---

Before:

```text
Laptop
 |
 app.jar
```

After:

```text
Image
 |
 app.jar
```

---

## EXPOSE

```dockerfile
EXPOSE 8080
```

Meaning:

```text
Application uses Port 8080
```

Does NOT publish the port.

---

## ENTRYPOINT

```dockerfile
ENTRYPOINT ["java","-jar","app.jar"]
```

Meaning:

```text
Run this command
when container starts
```

Equivalent:

```bash
java -jar app.jar
```

---

# 14. Docker Build Process

Command:

```bash
docker build -t student-app .
```

---

## Breakdown

### docker build

Create image.

---

### -t

Tag (image name).

Example:

```text
student-app
```

---

### .

Current directory.

Docker looks here for:

```text
Dockerfile
```

---

# Build Process

```text
Read Dockerfile
      ↓
Execute FROM
      ↓
Execute WORKDIR
      ↓
Execute COPY
      ↓
Store ENTRYPOINT
      ↓
Create Image
```

---

# 15. Docker Run Process

Command:

```bash
docker run student-app
```

---

Process:

```text
Find Image
      ↓
Create Container
      ↓
Execute ENTRYPOINT
      ↓
Application Starts
```

---

# 16. Port Mapping

Problem:

Container has its own network.

Browser cannot access it directly.

---

Command:

```bash
docker run -p 8080:8080 student-app
```

---

Format:

```bash
docker run -p HOST_PORT:CONTAINER_PORT image-name
```

---

Example

```bash
docker run -p 9090:8080 student-app
```

Meaning:

```text
Browser Port 9090
      ↓
Container Port 8080
```

Access:

```text
localhost:9090
```

---

# 17. EXPOSE vs Port Mapping

Dockerfile:

```dockerfile
EXPOSE 8080
```

Only documentation.

---

Still need:

```bash
docker run -p 8080:8080 app
```

for browser access.

---

# 18. WORKDIR Detailed Example

Without WORKDIR

```dockerfile
COPY target/app.jar /app/app.jar
```

---

With WORKDIR

```dockerfile
WORKDIR /app

COPY target/app.jar app.jar
```

Cleaner.

---

# 19. ENTRYPOINT

Purpose:

```text
Main Command
```

Example:

```dockerfile
ENTRYPOINT ["java","-jar","app.jar"]
```

Container always runs:

```bash
java -jar app.jar
```

---

# 20. CMD

Purpose:

```text
Default Arguments
```

Example:

```dockerfile
CMD ["--server.port=8080"]
```

---

# 21. ENTRYPOINT vs CMD

## ENTRYPOINT

Fixed Command

Example:

```dockerfile
ENTRYPOINT ["java","-jar","app.jar"]
```

---

## CMD

Default Parameters

Example:

```dockerfile
CMD ["--server.port=8080"]
```

---

Combined Result

```bash
java -jar app.jar --server.port=8080
```

---

Easy Rule:

```text
ENTRYPOINT = Fixed

CMD = Default and Changeable
```

---

# 22. Docker Layers

Every Dockerfile instruction creates a layer.

Example:

```dockerfile
FROM openjdk:21
WORKDIR /app
COPY target/app.jar app.jar
ENTRYPOINT ["java","-jar","app.jar"]
```

Creates:

```text
Layer 4 → ENTRYPOINT
Layer 3 → COPY
Layer 2 → WORKDIR
Layer 1 → FROM
```

---

# Why Layers?

Docker reuses unchanged layers.

Result:

```text
Faster Builds
Less Storage
Better Performance
```

---

# 23. Complete Spring Boot Docker Workflow

## Step 1

Create Spring Boot Application.

---

## Step 2

Build JAR.

```bash
mvn clean package
```

Creates:

```text
target/app.jar
```

---

## Step 3

Create Dockerfile.

```dockerfile
FROM openjdk:21

WORKDIR /app

COPY target/app.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java","-jar","app.jar"]
```

---

## Step 4

Build Image.

```bash
docker build -t student-app .
```

---

## Step 5

Verify Image.

```bash
docker images
```

---

## Step 6

Run Container.

```bash
docker run -p 8080:8080 student-app
```

---

## Step 7

Test API.

```text
http://localhost:8080
```

---

# 24. Practical Example

Controller

```java
@RestController
public class StudentController {

    @GetMapping("/hello")
    public String hello() {
        return "Hello Docker";
    }
}
```

---

Generate JAR

```bash
mvn clean package
```

---

Build Image

```bash
docker build -t student-app .
```

---

Run Container

```bash
docker run -p 8080:8080 student-app
```

---

Access

```text
http://localhost:8080/hello
```

Output:

```text
Hello Docker
```

---

# 25. Important Interview Questions

## What is Docker?

Docker is a platform used to package and run applications inside containers.

---

## What is Docker Image?

A Docker Image is a read-only blueprint used to create containers.

---

## What is Docker Container?

A Docker Container is a running instance of a Docker Image.

---

## Difference Between Image and Container?

```text
Image = Class

Container = Object
```

---

## Difference Between docker build and docker run?

```text
docker build
    ↓
Creates Image

docker run
    ↓
Creates and Starts Container
```

---

## Why do we need FROM?

Defines the base image.

Example:

```dockerfile
FROM openjdk:21
```

---

## Why do we need ENTRYPOINT?

Defines command executed when container starts.

---

## Why do we need COPY?

Copies application files into image.

---

## Why do we need Port Mapping?

Allows browser to access application running inside container.

---

## Does EXPOSE publish ports?

No.

Only documents the intended port.

---

## If Container Is Deleted, Is Image Deleted?

No.

Image remains.

---

## Can One Image Create Multiple Containers?

Yes.

```text
One Image
     ↓
Many Containers
```

---

# 26. Quick Revision Notes

```text
Dockerfile
    ↓
Instructions to create Image

FROM
    ↓
Base Image

WORKDIR
    ↓
Working Directory

COPY
    ↓
Copy Files into Image

EXPOSE
    ↓
Document Application Port

ENTRYPOINT
    ↓
Run Application on Container Startup

docker build
    ↓
Creates Image

docker run
    ↓
Creates + Starts Container

Image
    ↓
Blueprint

Container
    ↓
Running Instance

Port Mapping
    ↓
Host Port → Container Port

Image = Class

Container = Object
```


