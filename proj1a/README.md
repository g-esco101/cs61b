# [Project 1A: Data Structures](https://sp19.datastructur.es/materials/proj/proj1a/proj1a)

Create implementations of a “Double Ended Queue” using both lists and arrays. They have no capacity restrictions and allow null values.

## Run
Open in intellij IDEA & run the tests.DequeTest.java file.

## Files modified or created
- [src.Deque.java](Deque.java)
- [src.LinkedListDeque.java](LinkedListDeque.java)
- [src.ArrayDeque.java](ArrayDeque.java)
- [tests.DequeTest.java](DequeTest.java)
- [tests.ArrayDequeTest.java](ArrayDeque.java)
- [tests.LinkedListDequeTest.java](LinkedListDequeTest.java)
- [tests.TestUtility.java](TestUtility.java)

Note: The skeleton code came with one file, tests.LinkedListDequeTest.java, which was completely rewritten to contain tests strictly for the [src.LinkedListDeque](LinkedListDeque.java) implementation. 

# Not included in the instructions, but I added
## Method Dependency Injection
Created a [src.Deque](Deque.java) interface that is implemented by [src.LinkedListDeque](LinkedListDeque.java) and [src.ArrayDeque](ArrayDeque.java). In [tests.DequeTest](DequeTest.java), wrote methods that are dependent on the interface, rather than the implementations. Instances of the src.Deque implementations, i.e. [src.LinkedListDeque](LinkedListDeque.java) and [src.ArrayDeque](ArrayDeque.java), are injected into these methods via method dependency injection (a form of inversion of control). This made it possible to write one set of tests for both implementations.

## Performance
The get method in LinkedList.java determines if it is more efficient to traverse the list beginning from the front or the back by comparing the index to the size. If the index is in the first half of the list, it begins traversing from the front. If the index is in the back half of the list, it begins traversal from the back.

## Exceptions thrown under specific conditions. 
The constructors [src.ArrayDeque(src.ArrayDeque<T> other)](LinkedListDeque.java) and [src.LinkedListDeque(src.LinkedListDeque<T> other)](ArrayDeque.java) throw a NullPointerException if other is null. 

## Testing
Wrote tests such that 100% code coverage is achieved. 

The skeleton code supplied by the course came with one file, i.e. tests.LinkedListDequeTest.java, which came with several methods to test the implementations and several utility methods: checkEmpty, checkSize, and printTestStatus.  Almost all methods in it were replaced, except the aforementioned utility functions, which were moved to [tests.TestUtility.java](TestUtilit.java).
