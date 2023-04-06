package com.knoldus

import java.util.concurrent.Executors
import java.util.concurrent.locks.ReentrantReadWriteLock


object ReadWriteLocks extends App {

  val readWriteLocks = new ReadWriteLocks

  val executors= Executors.newFixedThreadPool(2)
  executors.execute(readWriteLocks.firstThread)
  executors.execute(readWriteLocks.secondThread)
  executors.execute(readWriteLocks.thirdThread)
  executors.execute(readWriteLocks.fourthThread)
}

class ReadWriteLocks {
  val lock = new ReentrantReadWriteLock
  var counter =  0

  def increament(writingValue: Int): Unit = {
    lock.writeLock().lock()
    try{
      println(Thread.currentThread().getName+ " => writing on count variable.")
      counter = counter + writingValue
      Thread.sleep(3000)
      println(s"count = $counter when writing.")
    }
    finally {
      lock.writeLock().unlock()
    }
  }

  def getCounter : Int  = {
    lock.readLock().lock()
    try{
      println(Thread.currentThread().getName+ " reading the count variable")
      counter
    } finally {
      lock.readLock().unlock()
    }
  }

  val firstThread = new Runnable {
    override def run(): Unit = {
      increament(3)
    }
  }
val secondThread = new Runnable {
    override def run(): Unit = {
      increament(2)
    }
  }
val thirdThread = new Runnable {
    override def run(): Unit = {
      Thread.sleep(3000)
      println(s"count = $getCounter")
    }
  }

  val fourthThread = new Runnable {
    override def run(): Unit = {
      Thread.sleep(3000)
      println(s"count = $getCounter")
    }
  }
}
