# SLF4J Logging & TDD Complete Handbook (Fresher Edition)

## Contents
- SLF4J Logging
- Why Logging
- SLF4J Architecture
- Logger Creation
- Log Levels
- Spring Boot Logging
- TDD
- RED GREEN REFACTOR
- Examples
- Interview Questions

# SLF4J Logging

## What is Logging?

Logging is the process of recording events that occur while an application is running.

Example:

INFO Application Started

WARN Low Disk Space

ERROR Database Connection Failed

## Why Logging?

Logging helps developers:

- Debug applications
- Monitor production systems
- Identify runtime failures
- Trace user actions

## System.out.println() vs Logging

System.out.println():
- Plain text
- No levels
- Console only

Logging:
- Structured logs
- INFO/WARN/ERROR
- Console and file support

## What is SLF4J?

SLF4J stands for:

Simple Logging Facade For Java

Important:

SLF4J is NOT a logging framework.

It is a facade/interface.

Actual logging frameworks:

- Logback
- Log4j
- java.util.logging

## Architecture

Application

↓

SLF4J

↓

Logback

↓

Console/File

## Why Facade?

Today:

Application → SLF4J → Logback

Tomorrow:

Application → SLF4J → Log4j

Application code remains unchanged.

Only dependencies change.

## Logger Creation

```java
private static final Logger logger =
        LoggerFactory.getLogger(StudentService.class);
```

Meaning:

Create a logger for StudentService.

### Why static?

One logger per class.

### Why final?

Cannot be reassigned.

## Log Levels

### INFO

```java
logger.info("Student Saved");
```

Used for normal events.

### WARN

```java
logger.warn("Low Memory");
```

Used for unusual situations.

### ERROR

```java
logger.error("Database Failure");
```

Used when operations fail.

## Placeholders

```java
logger.info("Student Id {}", id);
```

Preferred over string concatenation.

## Spring Boot

Spring Boot automatically provides:

- SLF4J
- Logback

No extra configuration required.

## SLF4J Interview Questions

1. What is SLF4J?
2. Is SLF4J a framework?
3. What is Logback?
4. Why not use System.out.println()?
5. What are INFO, WARN and ERROR?

# Test Driven Development (TDD)

## What is TDD?

TDD stands for:

Test Driven Development

Write tests before writing implementation code.

## Traditional Development

Write Code

↓

Test

## TDD

Write Test

↓

Write Code

↓

Pass Test

## TDD Cycle

RED

↓

GREEN

↓

REFACTOR

## RED

Write a failing test.

```java
@Test
void shouldAddNumbers(){

    assertEquals(30,
            calculator.add(10,20));
}
```

Method doesn't exist.

Result:

RED

## GREEN

Write minimum code.

```java
public int add(int a,int b){
    return 30;
}
```

Test passes.

Result:

GREEN

## REFACTOR

Improve implementation.

```java
public int add(int a,int b){
    return a+b;
}
```

Test still passes.

Result:

REFACTOR

## Complete Flow

Requirement

↓

Write Test

↓

RED

↓

Write Code

↓

GREEN

↓

Improve Code

↓

REFACTOR

## Benefits

- Better Design
- Fewer Bugs
- Safer Refactoring
- Better Test Coverage

## TDD Interview Questions

1. What is TDD?
2. What are the phases of TDD?
3. What happens during RED?
4. What happens during GREEN?
5. What happens during REFACTOR?
6. Is TDD a framework?

## Quick Revision

SLF4J

- Facade
- Logback is implementation
- INFO/WARN/ERROR

TDD

- Test First
- RED
- GREEN
- REFACTOR
