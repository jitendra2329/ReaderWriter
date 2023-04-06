# ReaderWriter

ReadWriteLocks

ReadWriteLocks is a Scala class that illustrates the use of ReentrantReadWriteLock to provide thread-safe access to a shared resource, which in this case is a List of integers.
Usage

The class defines five Runnable objects, each of which represents a thread that performs a specific operation on the shared List:

    firstThread: Adds the integer 3 to the List
    secondThread: Adds the integer 2 to the List
    thirdThread: Adds the integer 6 to the List
    fourthThread: Reads the current state of the List after a delay of 3 seconds
    fifthThread: Reads the current state of the List after a delay of 3 seconds

To use the class, create an instance of the ReadWriteLocks class and execute each of the Runnable objects as a new thread:

     

        private val executors = Executors.newFixedThreadPool(2)

        executors.execute(readWriteLocks.firstThread)

        executors.execute(readWriteLocks.fourthThread)

        executors.execute(readWriteLocks.secondThread)

        executors.execute(readWriteLocks.thirdThread)

        executors.execute(readWriteLocks.fifthThread)

        executors.shutdown()
  
 When the threads execute, they will acquire either a read lock or a write lock on the shared resource, depending on the type of operation being performed. Multiple threads can hold read locks at the same time, but only one thread can hold a write lock at a time.

The output of the program shows the order in which the threads execute their operations. The writing threads will print a message indicating that they are writing to the List, followed by the current state of the List. The reading threads will print a message indicating that they are reading the List, followed by the current state of the List.


Dependencies

The class uses the ReentrantReadWriteLock class from the Java standard library.

