package com.knoldus

import java.util.ConcurrentModificationException

import java.util.concurrent.locks.ReentrantReadWriteLock

class ReadWriteLocks {
  private val lock = new ReentrantReadWriteLock
  private var list: List[Int] = List().empty

  private def writingOperation(writingValue: Int): Unit = {
    lock.writeLock().lock()
    try {
      println(Thread.currentThread().getName + " => writing on the list.")
      list = list :+ writingValue
      Thread.sleep(3000)
      println(s"list = $list at the time of writing.")
    } catch {
      case exception: NullPointerException => println(exception.getMessage)
      case exception: InterruptedException => println(exception.getMessage)
    }
    finally {
      lock.writeLock().unlock()
    }
  }

  private def readingOperation: List[Int] = {
    lock.readLock().lock()
    try {
      println(Thread.currentThread().getName + " reading the list.")
      list
    } catch {
      case e: ConcurrentModificationException => list
    }
    finally {
      lock.readLock().unlock()
    }
  }

  val firstThread: Runnable = new Runnable {
    override def run(): Unit = {
      writingOperation(3)
    }
  }

  val secondThread: Runnable = new Runnable {
    override def run(): Unit = {
      writingOperation(2)
    }
  }

  val thirdThread: Runnable = new Runnable {
    override def run(): Unit = {
      writingOperation(6)
    }
  }

  val fourthThread: Runnable = new Runnable {
    override def run(): Unit = {
      Thread.sleep(3000)
      println(s"list = $readingOperation")
    }
  }

  val fifthThread: Runnable = new Runnable {
    override def run(): Unit = {
      Thread.sleep(3000)
      println(s"list = $readingOperation")
    }
  }
}