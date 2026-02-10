# Java Low Level Design (LLD) Problems and System Design Implementations

A curated collection of Java-based Low Level Design (LLD) implementations for popular system design problems. Each example demonstrates clean OOP, SOLID principles, and classic design patterns (State, Strategy, Observer, Chain of Responsibility, Factory) with runnable demos. Ideal for interview preparation and hands-on learning.

![Java](https://img.shields.io/badge/Java-20-orange) ![Build](https://img.shields.io/badge/Build-Maven-blue)

- Repository: https://github.com/harshgandhi-1999/low-level-design

## Table of Contents
- [Easy](#easy)
- [Medium](#medium)
- [Hard](#hard)
- [How to Run](#how-to-run)
- [Why this repo?](#why-this-repo)
- [Suggested GitHub Topics (SEO)](#suggested-github-topics-seo)

## Easy
- [Parking Lot LLD](https://github.com/harshgandhi-1999/low-level-design/tree/main/src/main/java/easy/parkinglotlld)
  - Java implementation of a multi-level parking lot: vehicle types (car, bike), spot allocation, availability tracking, and capacity management.
- [Vending Machine LLD](https://github.com/harshgandhi-1999/low-level-design/tree/main/src/main/java/easy/vendingmachinelld)
  - Java State Pattern example with states (Idle, Ready, Dispense, ReturnChange), inventory management, product selection, and coin handling.

## Medium
- [Pub/Sub System LLD](https://github.com/harshgandhi-1999/low-level-design/tree/main/src/main/java/medium/pubsubsystemlld)
  - Topic-based publish/subscribe with subscribers, message delivery, and topic observables. Demonstrates Observer pattern concepts in Java.
- [Logging System LLD](https://github.com/harshgandhi-1999/low-level-design/tree/main/src/main/java/medium/loggingsystemlld)
  - Chain of Responsibility for log handling (INFO, DEBUG, ERROR) with pluggable appenders (Console, File). Extensible design for enterprise logging.
- [Social Network App LLD](https://github.com/harshgandhi-1999/low-level-design/tree/main/src/main/java/medium/socialnetworkapplld)
  - Users, posts, comments, repositories, and a News Feed using Strategy (e.g., chronological). Includes observers for user notifications.
- [Notification System LLD](https://github.com/harshgandhi-1999/low-level-design/tree/main/src/main/java/medium/notificationsystemlld)
  - Multi-channel notifications (Email/SMS/Push) with scheduler, queue, priorities, retry handling, and channel preferences.
- [Digital Wallet Service LLD](https://github.com/harshgandhi-1999/low-level-design/tree/main/src/main/java/medium/digitalwalletsercicelld)
  - Accounts, transactions, and multiple payment methods (card, bank transfer) with currency support and basic validations.

## Hard
- [Splitwise LLD](https://github.com/harshgandhi-1999/low-level-design/tree/main/src/main/java/hard/splitwiselld)
  - Expense sharing for groups with equal/unequal/percentage splits, balance calculation, and transaction management.
- [Rate Limiter LLD](https://github.com/harshgandhi-1999/low-level-design/tree/main/src/main/java/hard/ratelimiterlld)
  - Multiple algorithms: Token Bucket, Leaky Bucket, Fixed Window Counter, and Sliding Window Log with configs and factory pattern.