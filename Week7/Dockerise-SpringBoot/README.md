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




# 🚀 Docker Complete Learning Guide

A complete, beginner-to-placement-ready reference for Docker — built around a real **Spring Boot + MySQL** project. Covers volumes, networks, Dockerizing a Spring Boot app, Docker Compose, interview questions, and a full cheat sheet.

> This is a 4-part learning README. Read it top-to-bottom once, then use it as a lookup reference afterward.

---

## Table of Contents

**[Part 1 — Docker Volumes](#part-1--docker-volumes)**
1. [Introduction to Docker Volumes](#1-introduction-to-docker-volumes)
2. [Why Docker Volumes?](#2-why-docker-volumes)
3. [Why Does Data Get Lost?](#3-why-does-data-get-lost)
4. [Understanding Container Filesystem](#4-understanding-container-filesystem)
5. [Where Does MySQL Store Data?](#5-where-does-mysql-store-data)
6. [Image vs Container vs Volume](#6-image-vs-container-vs-volume)
7. [Why Doesn't Docker Store Data Inside Images?](#7-why-doesnt-docker-store-data-inside-images)
8. [Why Is Data Initially Stored Inside the Container?](#8-why-is-data-initially-stored-inside-the-container)
9. [The Problem Without Volumes](#9-the-problem-without-volumes)
10. [Docker Volume](#10-docker-volume)
11. [Best Real-Life Analogy](#11-best-real-life-analogy)
12. [Docker Volume Lifecycle](#12-docker-volume-lifecycle)
13. [Creating a Volume](#13-creating-a-volume)
14. [List Volumes](#14-list-volumes)
15. [Inspect Volume](#15-inspect-volume)
16. [Remove Volume](#16-remove-volume)
17. [Attach Volume to MySQL](#17-attach-volume-to-mysql)
18. [Internal Working](#18-internal-working)
19. [Named Volumes](#19-named-volumes)
20. [Bind Mounts](#20-bind-mounts)
21. [Named Volume vs Bind Mount](#21-named-volume-vs-bind-mount)
22. [Can My Friend Access My Volume?](#22-can-my-friend-access-my-volume)
23. [Real Company Usage (Volumes)](#23-real-company-usage-volumes)
24. [Interview Questions (Volumes)](#24-interview-questions-volumes)
25. [Common Beginner Mistakes (Volumes)](#25-common-beginner-mistakes-volumes)
26. [Quick Revision (Volumes)](#26-quick-revision-volumes)

**[Part 2 — Docker Networks + Docker + MySQL](#part-2--docker-networks--docker--mysql)**
1. [Introduction to Docker Networks](#1-introduction-to-docker-networks)
2. [Why Docker Networks?](#2-why-docker-networks)
3. [The Problem Without Networks](#3-the-problem-without-networks)
4. [Understanding localhost](#4-understanding-localhost)
5. [Docker Network Types](#5-docker-network-types)
6. [Bridge Network](#6-bridge-network)
7. [Host Network](#7-host-network)
8. [None Network](#8-none-network)
9. [Custom Network](#9-custom-network)
10. [Docker DNS](#10-docker-dns)
11. [Container Communication](#11-container-communication)
12. [Docker Network Commands](#12-docker-network-commands)
13. [Spring Boot + MySQL Communication](#13-spring-boot--mysql-communication)
14. [Docker Hub](#14-docker-hub)
15. [Pulling MySQL](#15-pulling-mysql)
16. [Running MySQL](#16-running-mysql)
17. [Environment Variables](#17-environment-variables)
18. [MYSQL_ROOT_PASSWORD](#18-mysql_root_password)
19. [MYSQL_DATABASE](#19-mysql_database)
20. [MYSQL_USER](#20-mysql_user)
21. [MYSQL_PASSWORD](#21-mysql_password)
22. [Complete MySQL Command](#22-complete-mysql-command)
23. [Connecting Using MySQL Workbench](#23-connecting-using-mysql-workbench)
24. [Running Without Workbench](#24-running-without-workbench)
25. [Common Beginner Doubt](#25-common-beginner-doubt)
26. [Real Company Workflow (Networks)](#26-real-company-workflow-networks)
27. [Interview Questions (Networks)](#27-interview-questions-networks)
28. [Quick Revision (Networks)](#28-quick-revision-networks)

**[Part 3 — Spring Boot + Docker + MySQL](#part-3--spring-boot--docker--mysql)**
1. [Introduction](#1-introduction)
2. [Final Architecture](#2-final-architecture)
3. [Prerequisites](#3-prerequisites)
4. [Spring Boot Project Structure](#4-spring-boot-project-structure)
5. [Build the Project](#5-build-the-project)
6. [Dockerfile](#6-dockerfile)
7. [Understanding Every Instruction](#7-understanding-every-instruction)
8. [Build Docker Image](#8-build-docker-image)
9. [Run MySQL First](#9-run-mysql-first)
10. [application.properties](#10-applicationproperties)
11. [Why localhost Doesn't Work?](#11-why-localhost-doesnt-work)
12. [Run Spring Boot Container](#12-run-spring-boot-container)
13. [Complete Communication Flow](#13-complete-communication-flow)
14. [CRUD Flow](#14-crud-flow)
15. [Internal Working (GET Request)](#15-internal-working-get-request)
16. [What Happens If...](#16-what-happens-if)
17. [Stateless vs Stateful](#17-stateless-vs-stateful)
18. [Common Beginner Mistakes (Spring Boot + Docker)](#18-common-beginner-mistakes-spring-boot--docker)
19. [Real Company Workflow (Spring Boot + Docker)](#19-real-company-workflow-spring-boot--docker)
20. [Interview Questions (Spring Boot + Docker)](#20-interview-questions-spring-boot--docker)
21. [Quick Revision (Spring Boot + Docker)](#21-quick-revision-spring-boot--docker)
22. [Golden Rules](#-golden-rules)

**[Part 4 — Docker Compose + Interview Questions + Cheat Sheet](#part-4--docker-compose--interview-questions--cheat-sheet)**
1. [Introduction to Docker Compose](#1-introduction-to-docker-compose)
2. [Why Docker Compose?](#2-why-docker-compose)
3. [What Problems Does Compose Solve?](#3-what-problems-does-compose-solve)
4. [What is Docker Compose?](#4-what-is-docker-compose)
5. [docker-compose.yml](#5-docker-composeyml)
6. [version](#6-version)
7. [services](#7-services)
8. [image](#8-image)
9. [build](#9-build)
10. [container_name](#10-container_name)
11. [ports](#11-ports)
12. [environment](#12-environment)
13. [volumes](#13-volumes)
14. [depends_on](#14-depends_on)
15. [Bottom Volumes Section](#15-bottom-volumes-section)
16. [Running the Project](#16-running-the-project)
17. [Running in Background](#17-running-in-background)
18. [Stopping Everything](#18-stopping-everything)
19. [Remove Everything](#19-remove-everything)
20. [Internal Working (Compose)](#20-internal-working-compose)
21. [Complete Architecture (Compose)](#21-complete-architecture-compose)
22. [Docker vs Docker Compose](#22-docker-vs-docker-compose)
23. [Real Company Workflow (Compose)](#23-real-company-workflow-compose)
24. [Complete Development Flow](#24-complete-development-flow)
25. [Common Beginner Mistakes (Compose)](#25-common-beginner-mistakes-compose)
26. [Top Docker Interview Questions](#26-top-docker-interview-questions)
27. [Docker Cheat Sheet](#27-docker-cheat-sheet)
28. [Golden Rules (Full List)](#28-golden-rules-full-list)
29. [Complete Docker Flow](#29-complete-docker-flow)
30. [Final Revision (5-Minute Interview Recap)](#-final-revision-5-minute-interview-recap)
31. [Suggested Next Additions](#-my-suggestions-improvements-to-the-readme)

---

# Part 1 — Docker Volumes

## 1. Introduction to Docker Volumes

Until now, we've learned:

- Docker Images
- Docker Containers
- Docker Networks
- Dockerfile
- Port Mapping

One important question still remains:

> **Where should an application store its data?**

Examples of application data:

- MySQL database records
- Uploaded PDF files
- Profile pictures
- Application log files
- Generated reports

All of these are **data**, not application code. Docker Volumes solve this problem.

## 2. Why Docker Volumes?

### Imagine a Student Management System

```text
Spring Boot
    │
    ▼
  MySQL
```

| ID | Name |
|---|---|
| 1 | Karthik |
| 2 | Ravi |

Now suppose MySQL is running inside Docker:

```text
Spring Boot → MySQL Container
```

Everything works — until you delete the container:

```bash
docker rm mysql-db
```

**Where did the student data go? → Gone.**

Why? Because it was stored **inside the container**.

## 3. Why Does Data Get Lost?

Many beginners think *"Docker only contains code."* This is **not** correct.

A Docker container is like a small computer. It has its own file system, temporary files, installed software, database files, and log files:

```text
Container
├── Linux
├── Java
├── MySQL
├── Application
├── Logs
├── Database Files
└── Temporary Files
```

Everything inside this filesystem disappears if the container is removed.

## 4. Understanding Container Filesystem

Suppose you create a MySQL container:

```bash
docker run mysql
```

```
Container → Linux → MySQL Installed
```

Create a database and insert a row:

```sql
CREATE DATABASE student_db;
INSERT INTO student VALUES(1,'Karthik');
```

**Where is this record stored?** Inside MySQL. **Where is MySQL?** Inside the container.

```
Container → MySQL → student_db → Student Table
```

## 5. Where Does MySQL Store Data?

Every database stores its data as files, e.g.:

```text
student.ibd
undo.log
ibdata1
mysql.ibd
```

These files live at `/var/lib/mysql` **inside the container**:

```
Container → /var/lib/mysql → Database Files
```

## 6. Image vs Container vs Volume

This is the most important concept in Docker.

| Docker Image | Docker Container | Docker Volume |
|---|---|---|
| Blueprint | Running Application | Persistent Storage |
| Read Only | Can Change | Can Change |
| Contains Code | Contains Running Data | Stores Important Data |
| Shared | Temporary | Permanent |

### Easy Java Analogy

| Docker Concept | Java Equivalent |
|---|---|
| Docker Image | `class Student { }` (the blueprint) |
| Docker Container | `Student s = new Student();` (a running instance) |
| Docker Volume | Saving that object's data into a database (persists beyond the instance) |

## 7. Why Doesn't Docker Store Data Inside Images?

Suppose you build an image today. Its database contains:

```text
Student
1 Karthik
```

Tomorrow, your friend runs the image and inserts `2 Rahul`. **Should the original image change? No.**

Images are designed to be **immutable** (unchanging). If images changed whenever data changed:

- Every developer would end up with a different image.
- Versioning would become impossible.
- Sharing images would become unreliable.

So Docker separates concerns:

| Layer | Holds |
|---|---|
| Image | Application code |
| Container | Running application |
| Volume | Application data |

## 8. Why Is Data Initially Stored Inside the Container?

Docker does **not** create data — **applications** create data.

- **MySQL** creates databases, tables, rows → needs storage.
- **Spring Boot** uploads files like `Resume.pdf` → needs storage.
- **Logging** creates `application.log` → needs storage.

Docker simply provides a filesystem. Without a volume, the application writes to the container's own filesystem.

## 9. The Problem Without Volumes

```
Create Container → Run MySQL → Insert Students → Delete Container
```

Everything disappears:

```
Container → Student Database → docker rm → Data Lost
```

## 10. Docker Volume

A Docker Volume is simply a **persistent storage location managed by Docker** — think of it as a storage locker outside the container.

```
Container → Docker Volume → Hard Disk
```

Now the container can be deleted while the volume remains.

## 11. Best Real-Life Analogy

**Renting a house, without a volume:**

```
House → Books, Laptop, TV, Clothes
```
House demolished → everything is gone.

**With a bank locker:**

```
House → Bank Locker → Important Documents
```
House demolished → locker remains. Move into another house → everything is still safe.

Docker Volume behaves exactly like the bank locker.

## 12. Docker Volume Lifecycle

```
Create Volume → Attach Volume → Store Data → Delete Container
→ Volume Still Exists → Create New Container → Attach Same Volume
→ Data Restored
```

This is the biggest advantage of Docker Volumes.

## 13. Creating a Volume

```bash
docker volume create mysql-data
```

`docker volume create` → creates a new volume named `mysql-data`.

## 14. List Volumes

```bash
docker volume ls
```

```text
DRIVER   VOLUME NAME
local    mysql-data
```

## 15. Inspect Volume

```bash
docker volume inspect mysql-data
```

Shows the mount location, driver, and labels — useful for debugging.

## 16. Remove Volume

```bash
docker volume rm mysql-data
```

> ⚠️ **Warning:** Only remove a volume if you no longer need its data. Deleting a volume **permanently deletes everything stored inside it.**

## 17. Attach Volume to MySQL

```bash
docker run -d \
--name mysql-db \
-v mysql-data:/var/lib/mysql \
-e MYSQL_ROOT_PASSWORD=root123 \
mysql:8
```

The key line:

```bash
-v mysql-data:/var/lib/mysql
```

| Part | Meaning |
|---|---|
| `-v` | Attach a volume |
| `mysql-data` | Name of the Docker volume |
| `/var/lib/mysql` | Directory inside the container where MySQL stores its database files |

This tells Docker: *"Whenever MySQL writes data to `/var/lib/mysql`, store it in the `mysql-data` volume instead of the container's temporary filesystem."*

## 18. Internal Working

**Without a volume:**
```
MySQL → /var/lib/mysql → Container Storage → docker rm → Data Lost
```

**With a volume:**
```
MySQL → /var/lib/mysql → Docker Volume (mysql-data) → Host Disk
      → docker rm → Data Safe
```

## 19. Named Volumes

```bash
-v mysql-data:/var/lib/mysql
```

Docker manages *where* the data is physically stored.

**Advantages:** easy to use, portable, best choice for databases, recommended for Spring Boot projects.

## 20. Bind Mounts

Instead of letting Docker choose the storage location, **you** choose it.

```bash
# Windows
-v C:\Projects\Database:/var/lib/mysql

# Linux
-v /home/karthik/mysql:/var/lib/mysql
```

Use bind mounts when you want to directly access the files from your host operating system.

## 21. Named Volume vs Bind Mount

| Named Volume | Bind Mount |
|---|---|
| Managed by Docker | Managed by you |
| Safer | More flexible |
| Best for databases | Best for development files |
| Portable | Depends on host path |

## 22. Can My Friend Access My Volume?

**No** — this is a very common misunderstanding.

```
Laptop A → mysql-data Volume → Student Records
Laptop B → Docker Installed → (No mysql-data Volume)
```

Volumes are stored **locally on your machine**. They are **not shared automatically**.

If your friend needs the same data:
- Export the database (e.g., using `mysqldump`).
- Use a shared cloud database.
- Send a SQL backup.

Sharing a Docker image does **not** share your local volume.

## 23. Real Company Usage (Volumes)

In real projects, developers share: source code, Dockerfile, `docker-compose.yml`. Each developer creates their **own local** Docker volume.

For production, databases usually use dedicated storage services or cloud-managed disks instead of local Docker volumes.

## 24. Interview Questions (Volumes)

**Q: What is a Docker Volume?**
A Docker-managed persistent storage mechanism used to store data independently of a container's lifecycle.

**Q: Why are Volumes needed?**
Because containers are temporary, while application data must often survive container deletion and recreation.

**Q: Why does MySQL need a Volume?**
MySQL stores database files. Without a volume, those files would be deleted when the container is removed.

**Q: Can Docker Images store database data?**
No. Images are meant to be immutable. Runtime data belongs in containers or volumes.

**Q: Can two containers use the same Volume?**
Yes, if appropriate for the application. Multiple containers can mount the same volume, but concurrent access should be designed carefully based on the application's requirements.

## 25. Common Beginner Mistakes (Volumes)

- ❌ Thinking images store user data.
- ❌ Thinking volumes are shared across laptops.
- ❌ Deleting a volume without realizing it contains the database.
- ❌ Forgetting to attach the volume when recreating the MySQL container.
- ❌ Assuming `docker rm` also preserves container data automatically.

## 26. Quick Revision (Volumes)

**Without a volume:**
```
Application → Creates Data → Container Filesystem → Data Lost
```

**With a volume:**
```
Application → Docker Volume → Persistent Storage
           → Container Deleted → Data Still Exists
```

### ✅ Part 1 Recap

You now fully understand: Docker Volumes, why they exist, container filesystem behavior, Image vs Container vs Volume, named volumes, bind mounts, volume commands, MySQL data storage, real company usage, interview questions, common mistakes, and quick revision.

---

# Part 2 — Docker Networks + Docker + MySQL

## 1. Introduction to Docker Networks

Until now, we've learned how to run a **single container** — no communication was required. But real applications often need multiple services to communicate:

```
Spring Boot → MySQL
```

If both run inside Docker, they become two separate containers:

```
Spring Boot Container      MySQL Container
```

**How can these two containers communicate?** Docker Networks solve this.

## 2. Why Docker Networks?

Think of two houses with no road between them — people can't travel between them. A Docker Network *is* that road.

```
Spring Boot Container → Docker Network → MySQL Container
```

## 3. The Problem Without Networks

```bash
docker run student-app
docker run mysql
```

Docker creates two **isolated** containers. Spring Boot cannot access MySQL.

## 4. Understanding localhost

This is probably the **most important Docker concept**.

Many beginners think `localhost` means *"my laptop."* Inside Docker, this is **false**.

If you're inside the Spring Boot container, `localhost` means the **Spring Boot container itself** — NOT your laptop, and NOT the MySQL container.

> **Always remember:** `localhost` always refers to the current machine. Inside a container, the current machine is the container itself.

### Example

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/student_db
```

Spring Boot tries to connect — to itself. Does Spring Boot contain MySQL? No. **Connection fails.**

### Correct Way

```properties
spring.datasource.url=jdbc:mysql://mysql-db:3306/student_db
```

Use the **container name** instead of `localhost`. Docker automatically resolves it to the correct container.

## 5. Docker Network Types

| Network | Purpose |
|---|---|
| Bridge | Default communication between containers |
| Host | Container shares the host network |
| None | No networking |
| Custom Bridge | User-created network (recommended) |

For Spring Boot projects we mainly use ✅ Bridge or ✅ Custom Bridge.

## 6. Bridge Network

Docker automatically creates a `bridge` network when Docker is installed. Every container joins this network unless another one is specified.

```bash
docker network ls
```
```text
NETWORK ID   NAME
             bridge
             host
             none
```

**Limitation:** although containers can technically communicate under some conditions on the default bridge, it's less organized. Professional projects usually create their own network.

## 7. Host Network

In host mode, the container shares the host computer's network stack directly:

```
Laptop Network → Container
```

No isolation. Mostly used for specific performance or networking requirements — rarely used in beginner Spring Boot projects.

## 8. None Network

The container has no internet, no communication, and no network. Useful only for very specialized scenarios.

## 9. Custom Network

This is what we actually use.

```bash
docker network create student-network
docker network ls
```
```text
bridge
host
none
student-network
```

**Attach containers to it:**

```bash
# MySQL
docker run -d \
--name mysql-db \
--network student-network \
mysql:8

# Spring Boot
docker run -d \
--name student-app \
--network student-network \
student-app
```

### Architecture

```
            student-network
                  │
        ┌─────────┴─────────┐
        ▼                   ▼
   Spring Boot            MySQL
```

## 10. Docker DNS

Docker's hidden superpower. Suppose a container is named `mysql-db` — Docker automatically assigns it an internal IP, e.g. `172.18.0.2`.

Spring Boot never needs to know this IP — it just references `mysql-db`, and Docker converts it automatically:

```
mysql-db → 172.18.0.2
```

Exactly like the internet: when you type `google.com`, DNS converts it into an IP address. Docker has its own internal DNS.

## 11. Container Communication

```
Spring Boot → mysql-db → Docker DNS → 172.18.0.2 → MySQL
```

This is why `mysql-db` works as a hostname.

## 12. Docker Network Commands

```bash
docker network create student-network   # create
docker network ls                       # list
docker network inspect student-network  # inspect: connected containers, gateway, IPs
docker network rm student-network       # remove
```

## 13. Spring Boot + MySQL Communication

```
Browser → Spring Boot → JDBC → mysql-db → Docker Network → MySQL
```

Notice: Spring Boot never communicates directly with Docker. It simply opens a JDBC connection — the Docker network handles the routing.

## 14. Docker Hub

Before running MySQL, Docker needs the MySQL software — it comes from **Docker Hub**, essentially *"GitHub for Docker images."*

Official images include: MySQL, PostgreSQL, Redis, Nginx, OpenJDK, Ubuntu.

## 15. Pulling MySQL

```bash
docker pull mysql:8
```

`docker pull` downloads the image; `mysql` is the repository; `8` is the version.

```bash
docker images
```
```text
mysql   8
```

## 16. Running MySQL

Many beginners try:

```bash
docker run mysql
```

It fails — because MySQL needs configuration.

## 17. Environment Variables

Imagine installing MySQL on Windows — the installer asks for a root password, database name, user, and password. Docker has no graphical installer; instead, it asks through **environment variables**.

## 18. MYSQL_ROOT_PASSWORD

**Required.**

```bash
-e MYSQL_ROOT_PASSWORD=root123
```

Sets the root user's password. Without this, MySQL refuses to start.

## 19. MYSQL_DATABASE

Instead of manually running `CREATE DATABASE student_db;`, Docker can create it for you:

```bash
-e MYSQL_DATABASE=student_db
```

Container starts → database already exists.

## 20. MYSQL_USER

**Optional.** Creates an additional user:

```bash
-e MYSQL_USER=student
```

Instead of always using `root`, you can use `student`.

## 21. MYSQL_PASSWORD

Password for `MYSQL_USER`:

```bash
-e MYSQL_PASSWORD=student123
```

Now `student` / `student123` can access the database with the permissions granted to that user.

## 22. Complete MySQL Command

```bash
docker run -d \
--name mysql-db \
--network student-network \
-p 3307:3306 \
-v mysql-data:/var/lib/mysql \
-e MYSQL_ROOT_PASSWORD=root123 \
-e MYSQL_DATABASE=student_db \
mysql:8
```

| Part | Meaning |
|---|---|
| `-d` | Background mode |
| `--name` | Container name |
| `--network` | Join `student-network` |
| `-p` | Port mapping |
| `-v` | Attach volume |
| `MYSQL_ROOT_PASSWORD` | Root password |
| `MYSQL_DATABASE` | Create database |

## 23. Connecting Using MySQL Workbench

Common doubt: *"If MySQL runs inside Docker, why do we still use MySQL Workbench?"*

Because Workbench ≠ MySQL Server. Workbench is only a **client**.

```
Workbench → localhost:3307 → Docker → MySQL Server
```

Workbench stores nothing — it only sends SQL queries.

## 24. Running Without Workbench

Workbench is optional — you can enter the container directly:

```bash
docker exec -it mysql-db mysql -u root -p
# enter: root123
```

```sql
SHOW DATABASES;
USE student_db;
SHOW TABLES;
```

Everything works from the terminal.

## 25. Common Beginner Doubt

**"If I already have MySQL installed locally, why use Docker?"**

Because Docker lets each project use its own isolated database version and configuration.

*Example:* Local MySQL — version 8.0.39 on port 3306. Docker MySQL — version 8.4 on port 3307. They can coexist without interfering, as long as you avoid port conflicts.

## 26. Real Company Workflow (Networks)

Every developer gets: Git repository → Dockerfile → `docker-compose.yml`.

```bash
docker compose up
```

Docker automatically downloads MySQL, Java, and required images — no manual installation. Every developer gets the same environment.

## 27. Interview Questions (Networks)

**Q: What is a Docker Network?**
A Docker Network allows containers to communicate with each other securely.

**Q: Why doesn't localhost work?**
Because `localhost` always refers to the current container.

**Q: Why does `mysql-db` work?**
Docker DNS converts the container name into the correct internal IP address.

**Q: What is Docker Hub?**
The official registry used to store and distribute Docker images.

**Q: Why are Environment Variables needed?**
They provide configuration values (such as passwords and database names) to containers at startup without requiring an interactive installer.

## 28. Quick Revision (Networks)

```
Spring Boot → mysql-db → Docker DNS → MySQL → Docker Volume
```

Remember:

```
localhost   → Current Container
mysql-db    → Another Container
```

### ✅ Part 2 Recap

You now understand: Docker Networks, `localhost` vs container name, Docker DNS, bridge networks, custom networks, Docker Hub, pulling MySQL, environment variables, running MySQL in Docker, Workbench (with and without), real company workflow, interview questions, and quick revision.

---

# Part 3 — Spring Boot + Docker + MySQL

## 1. Introduction

Now we combine everything learned so far: Docker Images, Containers, Volumes, Networks, and Docker + MySQL — to connect a **Spring Boot CRUD Application** with **MySQL running inside Docker**. This is one of the most common setups used during Spring Boot development.

## 2. Final Architecture

```text
                Browser / Postman
                       │
                       ▼
              localhost:8080
                       │
                       ▼
             Spring Boot Container
                       │
                       ▼
                 Hibernate (JPA)
                       │
                       ▼
                     JDBC
                       │
                       ▼
                mysql-db Container
                       │
                       ▼
                 Docker Volume
```

Everything learned so far fits into this single architecture.

## 3. Prerequisites

Before Dockerizing the project, make sure you already have:

- ✅ Spring Boot Project
- ✅ MySQL Dependency
- ✅ Spring Data JPA
- ✅ CRUD APIs
- ✅ Maven Installed

## 4. Spring Boot Project Structure

```text
StudentManagement
│
├── src
│   ├── main
│   │   ├── java
│   │   ├── resources
│   │   └── application.properties
│
├── target
├── Dockerfile
└── pom.xml
```

The `Dockerfile` stays in the project root.

## 5. Build the Project

Before Docker can package the application, Maven must create the executable JAR:

```bash
mvn clean package
```

```text
target/StudentManagement-0.0.1-SNAPSHOT.jar
```

**Why do we need the JAR?** Docker doesn't compile your project — it simply copies the already-built JAR into the image. (Think of it like packing a suitcase: you prepare everything first, then Docker packs it.)

```
Source Code → Maven → Executable JAR → Docker
```

## 6. Dockerfile

Create a `Dockerfile` inside the project root:

```dockerfile
FROM eclipse-temurin:21-jdk

WORKDIR /app

COPY target/StudentManagement-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java","-jar","app.jar"]
```

## 7. Understanding Every Instruction

**`FROM eclipse-temurin:21-jdk`** — downloads Java 21 (equivalent to manually installing Java).

**`WORKDIR /app`** — creates `/app` inside the container; every subsequent command runs from here.

**`COPY target/StudentManagement.jar app.jar`** — copies the JAR from your laptop into the container:

```
Laptop → target/StudentManagement.jar → Container → /app/app.jar
```

**`EXPOSE 8080`** — tells Docker *"my application listens on port 8080."* Important: `EXPOSE` does **NOT** make the application accessible from outside — it's only documentation.

**`ENTRYPOINT ["java","-jar","app.jar"]`** — whenever the container starts, Docker automatically runs `java -jar app.jar`. No need to type it manually.

## 8. Build Docker Image

```bash
docker build -t student-app .
```

`docker build` → build image; `-t` → tag; `student-app` → image name; `.` → current folder.

```bash
docker images
```
```text
student-app
mysql
```

Now we have two images.

## 9. Run MySQL First

Before Spring Boot starts, MySQL must already be running:

```bash
docker run -d \
--name mysql-db \
--network student-network \
-p 3307:3306 \
-v mysql-data:/var/lib/mysql \
-e MYSQL_ROOT_PASSWORD=root123 \
-e MYSQL_DATABASE=student_db \
mysql:8
```

**Why first?** Because Spring Boot needs a database connection during startup. If MySQL isn't available, Spring Boot may fail to start (unless you've configured retries).

## 10. application.properties

This is the biggest Docker-related change.

Normally, `jdbc:mysql://localhost:3306/student_db` worked — because MySQL was installed directly on your laptop.

Now Spring Boot runs **inside Docker**. Where does `localhost` point? → the Spring Boot container, **not** your laptop.

❌ **Wrong:**
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/student_db
```

✅ **Correct:**
```properties
spring.datasource.url=jdbc:mysql://mysql-db:3306/student_db
spring.datasource.username=root
spring.datasource.password=root123
```

We use `mysql-db` — the container name — and Docker DNS converts it to the correct IP.

## 11. Why localhost Doesn't Work?

**Without Docker:**
```
Spring Boot → localhost → MySQL   ✅ Works
```

**With Docker:**
```
Spring Boot Container → localhost → Spring Boot Container (no MySQL)   ❌ Fails
```

**Correct, with Docker:**
```
Spring Boot Container → mysql-db → Docker DNS → MySQL Container   ✅ Works
```

## 12. Run Spring Boot Container

```bash
docker run -d \
--name student-app \
--network student-network \
-p 8080:8080 \
student-app
```

| Option | Purpose |
|---|---|
| `-d` | Background mode |
| `--name` | Container name |
| `--network` | Join network |
| `-p` | Publish port |
| `student-app` | Image name |

## 13. Complete Communication Flow

```
Browser → localhost:8080 → Spring Boot → StudentController
        → StudentService → StudentRepository → Hibernate → JDBC
        → mysql-db → MySQL → Docker Volume
```

## 14. CRUD Flow

Suppose a user sends:

```http
POST /students
```
```json
{ "name": "Karthik" }
```

**What happens, step by step:**

1. Browser → Spring Boot
2. Controller receives the request
3. Service validates the data
4. Repository saves the entity
5. Hibernate converts the object into SQL:
   ```sql
   INSERT INTO student(name) VALUES('Karthik');
   ```
6. JDBC sends the SQL
7. Docker Network routes the request
8. MySQL executes the SQL
9. Database files are updated
10. Files are stored inside the `mysql-data` volume

**Complete flow:**
```
Browser → Controller → Service → Repository → Hibernate → JDBC
        → Docker Network → MySQL → Docker Volume
```

## 15. Internal Working (GET Request)

Suppose a user requests `GET /students`:

```
Spring Boot → Repository → SELECT * FROM student → MySQL
            → Returns rows → Hibernate → Student Objects
            → Controller → JSON → Browser
```

## 16. What Happens If...

**Spring Boot stops** (`docker stop student-app`) — the database is safe, because MySQL is still running.

**MySQL stops** (`docker stop mysql-db`) — Spring Boot can't access the database, exactly like local MySQL stopping.

**Spring Boot container is deleted** (`docker rm student-app`) — nothing happens; the image and database still exist.

**MySQL container is deleted:**
- Without a volume → everything is lost.
- With a volume → the database survives.

## 17. Stateless vs Stateful

A very common interview topic.

**Stateless** — e.g. Spring Boot APIs, REST services, an authentication server. They don't permanently store user data inside the application container. If the container dies, just create another one — everything works.

**Stateful** — e.g. MySQL, PostgreSQL, MongoDB. They store user data and need volumes.

| Stateless | Stateful |
|---|---|
| Spring Boot | MySQL |
| Easy to recreate | Needs persistent storage |
| Uses Image | Uses Volume |

## 18. Common Beginner Mistakes (Spring Boot + Docker)

- ❌ Using `localhost` inside a container
- ❌ Forgetting the Docker network
- ❌ Forgetting the Docker volume
- ❌ Running Spring Boot before MySQL
- ❌ Forgetting to build the JAR
- ❌ Forgetting `docker build`
- ❌ Thinking deleting a container deletes the image
- ❌ Thinking the image stores the database

## 19. Real Company Workflow (Spring Boot + Docker)

```
Write Code → mvn clean package → Build Docker Image → Run MySQL
           → Run Spring Boot → Test APIs → Commit Code → Push to Git
```

Notice: the database is **never** committed — only code.

## 20. Interview Questions (Spring Boot + Docker)

**Q: Why doesn't localhost work inside Docker?**
Because `localhost` always refers to the current container.

**Q: Why use `mysql-db`?**
It's the MySQL container name — Docker DNS converts it into the container's IP.

**Q: Why build the JAR before the Docker image?**
Docker packages the JAR; it doesn't compile Java source code.

**Q: Why is Spring Boot stateless?**
Because its application code comes from the image, so containers can be recreated easily.

**Q: Why is MySQL stateful?**
Because it stores user data and needs persistent storage.

**Q: What happens if the MySQL container is deleted?**
Without a volume → database lost. With a volume → database restored.

## 21. Quick Revision (Spring Boot + Docker)

```
Spring Boot Project → mvn clean package → JAR → Dockerfile
                    → docker build → Docker Image → docker run
                    → Spring Boot Container → Docker Network
                    → MySQL Container → Docker Volume
```

## 🧠 Golden Rules

**Rule 1** — Images contain code, Java, and dependencies — **NOT** the database.

**Rule 2** — Containers are temporary. Volumes are permanent.

**Rule 3** — Never use `localhost` between containers. Always use the **container name**.

**Rule 4** — Spring Boot → Stateless. MySQL → Stateful.

### ✅ Part 3 Recap

You now understand: the Spring Boot Dockerfile, `docker build`/`docker run`, `application.properties` changes, `localhost` vs `mysql-db`, Spring Boot + MySQL communication, the CRUD flow, the Hibernate + JDBC flow, Docker volume integration, stateless vs stateful, real company workflow, interview questions, and quick revision.

---

# Part 4 — Docker Compose + Interview Questions + Cheat Sheet

## 1. Introduction to Docker Compose

Suppose your Spring Boot project contains a Spring Boot application **and** a MySQL database. Without Compose, you'd manually run many commands:

```bash
docker network create student-network
docker volume create mysql-data
docker run ... mysql
docker build ...
docker run ... student-app
```

Imagine explaining all of these commands to a new developer — it becomes difficult. **Docker Compose solves this problem.**

## 2. Why Docker Compose?

Docker Compose lets us define the **entire application environment** inside a single file. Instead of remembering many commands, we remember only one:

```bash
docker compose up
```

Everything starts automatically.

### Real-Life Analogy

Every morning: *Wake Up → Brush → Bath → Breakfast → Office.* Without Compose, you remember every step. With Compose, it's **one button** and everything happens. Same idea.

## 3. What Problems Does Compose Solve?

Without Compose you need to remember: image names, port mapping, volumes, networks, environment variables, and startup order. Compose stores all of this for you.

## 4. What is Docker Compose?

A tool that defines and runs **multi-container Docker applications** using a YAML configuration file — usually named `docker-compose.yml`. Everything about your application is written there.

## 5. docker-compose.yml

```yaml
version: "3.9"

services:

  mysql-db:
    image: mysql:8
    container_name: mysql-db
    ports:
      - "3307:3306"
    environment:
      MYSQL_ROOT_PASSWORD: root123
      MYSQL_DATABASE: student_db
    volumes:
      - mysql-data:/var/lib/mysql

  student-app:
    build: .
    container_name: student-app
    ports:
      - "8080:8080"
    depends_on:
      - mysql-db

volumes:
  mysql-data:
```

Don't memorize it — let's understand every line.

## 6. version

```yaml
version: "3.9"
```

Represents the Compose file format version. Modern Docker Compose can often infer the version, so newer examples may omit this field.

## 7. services

Everything under `services:` becomes a container:

```yaml
services:
  mysql-db
  student-app
```

This creates a MySQL container and a Spring Boot container.

## 8. image

```yaml
image: mysql:8
```

Equivalent to `docker pull mysql:8` — Compose automatically downloads it.

## 9. build

```yaml
build: .
```

Means: build an image using the Dockerfile in the current directory. Equivalent to `docker build .`.

## 10. container_name

```yaml
container_name: mysql-db
```

Instead of Docker creating random names (`happy_newton`, `peaceful_turing`), we explicitly define `mysql-db`.

## 11. ports

```yaml
ports:
  - "8080:8080"
```

Equivalent to `docker run -p 8080:8080`. Format: `HostPort:ContainerPort`.

## 12. environment

```yaml
environment:
  MYSQL_ROOT_PASSWORD: root123
  MYSQL_DATABASE: student_db
```

Equivalent to `docker run -e MYSQL_ROOT_PASSWORD=root123`. Compose passes these values during container startup.

## 13. volumes

```yaml
volumes:
  - mysql-data:/var/lib/mysql
```

Equivalent to `docker run -v mysql-data:/var/lib/mysql`. Compose automatically creates `mysql-data` if it doesn't already exist.

## 14. depends_on

```yaml
depends_on:
  - mysql-db
```

Means: start the MySQL container before Spring Boot.

> ⚠️ **Important:** `depends_on` controls **startup order only**. It does **not** guarantee MySQL is fully ready to accept connections. In production, applications often use retry mechanisms or health checks.

## 15. Bottom Volumes Section

```yaml
volumes:
  mysql-data:
```

Declares a **named volume** — Compose creates it automatically. No need for `docker volume create`.

## 16. Running the Project

```bash
docker compose up
```

Compose automatically: builds the Spring Boot image, pulls the MySQL image, creates the network, creates the volume, starts MySQL, and starts Spring Boot — all with **one command**.

## 17. Running in Background

```bash
docker compose up -d
```

Detached mode — the terminal becomes free.

## 18. Stopping Everything

Instead of:
```bash
docker stop mysql-db
docker stop student-app
```

Use:
```bash
docker compose down
```

Compose removes containers and the network. **The volume remains.**

## 19. Remove Everything

```bash
docker compose down -v
```

Removes containers, network, **and** volume. Since the volume is removed, the database is deleted.

## 20. Internal Working (Compose)

```
Read docker-compose.yml → Pull Images → Build Spring Boot
→ Create Network → Create Volume → Create MySQL
→ Create Spring Boot → Application Ready
```

## 21. Complete Architecture (Compose)

```text
             docker-compose.yml
                    │
                    ▼
          Docker Compose Engine
                    │
       ┌────────────┴────────────┐
       ▼                         ▼
 Spring Boot                  MySQL
       │
       ▼
 Docker Network
       │
       ▼
 Docker Volume
```

## 22. Docker vs Docker Compose

| Docker | Docker Compose |
|---|---|
| Single Container | Multiple Containers |
| `docker run` | `docker compose up` |
| Manual Commands | YAML Configuration |
| Manual Network | Automatic Network |
| Manual Volume | Automatic Volume |

## 23. Real Company Workflow (Compose)

```
Developer → Clone Repository → docker compose up → Everything Starts
```

No need to install MySQL, configure the database, create a network, or create a volume — Compose handles everything.

## 24. Complete Development Flow

```
Write Code → mvn clean package → Docker Build → docker compose up
           → Spring Boot Starts → MySQL Starts → CRUD APIs
           → Volume Stores Data
```

## 25. Common Beginner Mistakes (Compose)

- ❌ Using `localhost` inside containers
- ❌ Forgetting to rebuild the image after code changes — remember:
  ```
  Modify Code → mvn clean package → docker build → docker compose up
  ```
- ❌ Forgetting the Docker volume → database lost
- ❌ Forgetting the Docker network → Spring Boot can't find MySQL
- ❌ Thinking images contain the database (wrong)
- ❌ Thinking volumes are shared across machines (wrong — volumes are local)

## 26. Top Docker Interview Questions

**1. What is Docker?**
A containerization platform used to package applications with their dependencies into containers.

**2. What is a Docker Image?**
A read-only template used to create containers.

**3. What is a Docker Container?**
A running instance of a Docker Image.

**4. Difference between Image and Container?**

| Image | Container |
|---|---|
| Template | Running Instance |
| Read Only | Read/Write |
| Blueprint | Running Application |

**5. What is a Dockerfile?**
A text file containing instructions for building a Docker Image.

**6. Why Docker?**
Consistent environment, easy deployment, lightweight, portable.

**7. What is a Docker Volume?**
Persistent storage managed by Docker.

**8. Why Volumes?**
Containers are temporary; volumes preserve data.

**9. What is a Docker Network?**
Allows containers to communicate.

**10. Why doesn't `localhost` work?**
`localhost` always points to the current container.

**11. Why does `mysql-db` work?**
Docker DNS converts `mysql-db` into the container's IP.

**12. Why doesn't Spring Boot need a volume?**
Because Spring Boot is stateless.

**13. Why does MySQL need a volume?**
Because MySQL stores data.

**14. Difference between VM and Docker?**

| Virtual Machine | Docker |
|---|---|
| Full OS | Shares kernel |
| Heavy | Lightweight |
| Slow | Fast |

**15. Difference between EXPOSE and Port Mapping?**
`EXPOSE` documents the container's port. Port mapping (`-p`) makes it accessible from outside.

**16. Difference between ENTRYPOINT and CMD?**
`ENTRYPOINT` is the main executable. `CMD` supplies default arguments (or acts as the default command if `ENTRYPOINT` is not set).

**17. Difference between COPY and ADD?**
Use `COPY` most of the time — `ADD` has extra features.

**18. What is Docker Compose?**
A tool to run multiple containers together.

**19. What is `docker-compose.yml`?**
A configuration file describing containers, networks, volumes, ports, etc.

**20. Why Docker Compose?**
One command starts the complete application.

## 27. Docker Cheat Sheet

**Images**
```bash
docker pull
docker images
docker build
docker rmi
```

**Containers**
```bash
docker run
docker ps
docker ps -a
docker stop
docker start
docker rm
```

**Volumes**
```bash
docker volume create
docker volume ls
docker volume inspect
docker volume rm
```

**Networks**
```bash
docker network create
docker network ls
docker network inspect
docker network rm
```

**Compose**
```bash
docker compose up
docker compose up -d
docker compose down
docker compose down -v
```

## 28. Golden Rules (Full List)

| # | Rule |
|---|---|
| 1 | Docker Image → Application, **NOT** database |
| 2 | Container → Temporary |
| 3 | Volume → Permanent |
| 4 | `localhost` → Current container |
| 5 | Container name → Container-to-container communication |
| 6 | Spring Boot → Stateless |
| 7 | MySQL → Stateful |
| 8 | Compose → Multiple containers |

## 29. Complete Docker Flow

```
Developer → Write Code → mvn clean package → Executable JAR
          → Dockerfile → docker build → Docker Image
          → docker compose up → Spring Boot Container
          → Docker Network → MySQL Container → Docker Volume
          → Database Files
```

---

## 🎯 Final Revision (5-Minute Interview Recap)

| Concept | One-Line Answer |
|---|---|
| Docker | A containerization platform that packages an application with its dependencies |
| Image | Blueprint |
| Container | Running image |
| Dockerfile | Instructions to build an image |
| Docker Volume | Persistent storage |
| Docker Network | Communication between containers |
| Docker Compose | Runs multiple containers using one YAML file |
| Spring Boot | Stateless |
| MySQL | Stateful |
| `localhost` | Current container |
| `mysql-db` | Container name |

**Build process:**
```
Code → Maven → JAR → Docker Image → Container
```

**Complete architecture:**
```text
             Browser
                │
                ▼
      Spring Boot Container
                │
           Hibernate
                │
              JDBC
                │
                ▼
         MySQL Container
                │
                ▼
         Docker Volume
```

---
