package com.knoldus

import java.util.concurrent.Executors

object Driver extends App {
  private val readWriteLocks = new ReadWriteLocks

  private val executors = Executors.newFixedThreadPool(2)
  executors.execute(readWriteLocks.firstThread)
  executors.execute(readWriteLocks.fourthThread)
  executors.execute(readWriteLocks.secondThread)
  executors.execute(readWriteLocks.thirdThread)
  executors.execute(readWriteLocks.fifthThread)

  executors.shutdown()
}

