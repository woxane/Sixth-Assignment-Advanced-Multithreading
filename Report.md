# Solution Report

## Table of Answers
- [Multithreaded Pi Calculation](#Multithreaded-Pi-Calculation)
- [Synchronizing Threads with Semaphore](#Synchronizing-Threads-with-Semaphore)

## Multithreaded Pi Calculation

### Introduction
In this report, I will outline the implementation details of a multithreaded program for calculating the mathematical constant π (pi) to a specified number of floating-point digits.

### Solutions and Implementations
1. **Multithreading Approach**: The program utilizes the ExecutorService from the java.util.concurrent package to create a thread pool. By employing a fixed thread pool with a size of 5, we can control the number of concurrent threads executing the calculation tasks.

2. **Mathematical Algorithms for Pi Calculation**: Various algorithms exist for calculating π, each with different trade-offs in terms of accuracy and computational efficiency. For this implementation, I experimented with the Bailey–Borwein–Plouffe (BBP) formula, known for its rapid convergence to π.

### BBP Algorithm for Pi Calculation
The BBP algorithm utilizes series summation to calculate π. It involves iterating over terms of the series, computing each term independently, and summing them up to approximate π. The advantage of the BBP formula lies in its rapid convergence, allowing for efficient computation of π to a high degree of accuracy.

The BBP formula used in this implementation involves summing terms of the form  :

![BBP](BBP.png)

### References and Resources
- Wikipedia:
    - [Bailey–Borwein–Plouffe formula](https://en.wikipedia.org/wiki/Bailey%E2%80%93Borwein%E2%80%93Plouffe_formula)


## Synchronizing Threads with Semaphore

### Introduction
In this report, I will discuss the design and implementation of a multithreaded program using Java's Semaphore to synchronize access to a shared resource among multiple threads .

### Solutions and Implementations
1. **Controller Class**: The Controller class serves as the main entry point of the program.

2. **Operator Class**: Each Operator thread attempts to access the shared resource in its run() method. Before accessing the resource, it acquires a permit from the Semaphore. Once finished, it releases the permit back to the Semaphore, allowing other threads to access the resource. This ensures that a maximum of two operators can access the resource concurrently, as specified in the problem statement.

3. **Resource Class**: The Resource class represents the shared resource that operators try to access.

## Explanation of Semaphore and Its Use Cases
A Semaphore is a synchronization mechanism used to control access to a shared resource by multiple threads. It maintains a set of permits, where each permit represents permission to access the resource. Semaphores can be used to address various concurrency problems, such as controlling the number of concurrent accesses to a resource, implementing producer-consumer scenarios, and ensuring mutual exclusion in critical sections.

In this program, a Semaphore with a permit count of 2 is used to enforce the constraint that only a maximum of two operators can access the resource concurrently. When an operator tries to access the resource, it must first acquire a permit from the Semaphore. If permits are available, the operator proceeds to access the resource. Otherwise, it waits until a permit becomes available. Once the operation is complete, the operator releases the permit, allowing other threads to acquire it.

### References and Resources
- Java Point:
  - [Java Semaphore](https://www.javatpoint.com/java-semaphore)