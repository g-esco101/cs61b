# [Project 1A: Data Structures](https://sp19.datastructur.es/materials/proj/proj1a/proj1a)

Create implementations of a “Double Ended Queue” using both lists and arrays. They have no capacity restrictions and allow null values.

## Run
Open in intellij IDEA & run the DequeTest.java file.

## Files modified or created
- [Deque.java](Deque.java)
- [LinkedListDeque.java](LinkedListDeque.java)
- [ArrayDeque.java](ArrayDeque.java)
- [DequeTest.java](DequeTest.java)
- [ArrayDequeTest.java](ArrayDeque.java)
- [LinkedListDequeTest.java](LinkedListDequeTest.java)
- [TestUtility.java](TestUtility.java)

Note: The skeleton code came with one file, LinkedListDequeTest.java, which was completely rewritten to contain tests strictly for the [LinkedListDeque](LinkedListDeque.java) implementation. 

# Not included in the instructions, but I added
## Method Dependency Injection
Created a [Deque](Deque.java) interface that is implemented by [LinkedListDeque](LinkedListDeque.java) and [ArrayDeque](ArrayDeque.java). In [DequeTest](DequeTest.java), wrote methods that are dependent on the interface, rather than the implementations. Instances of the Deque implementations, i.e. [LinkedListDeque](LinkedListDeque.java) and [ArrayDeque](ArrayDeque.java), are injected into these methods via method dependency injection (a form of inversion of control). This made it possible to write one set of tests for both implementations.

## Exceptions thrown under specific conditions. 
The constructors [ArrayDeque(ArrayDeque<T> other)](LinkedListDeque.java) and [LinkedListDeque(LinkedListDeque<T> other)](ArrayDeque.java) throw a NullPointerException if other is null. 

## Testing
Wrote tests such that 100% code coverage is achieved. 

The skeleton code supplied by the course came with one file, i.e. LinkedListDequeTest.java, which came with several methods to test the implementations and several utility methods: checkEmpty, checkSize, and printTestStatus.  Almost all methods in it were replaced, except the aforementioned utility functions, which were moved to [TestUtility.java](TestUtilit.java).
