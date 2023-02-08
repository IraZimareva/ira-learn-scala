package start

import scala.io.StdIn.readLine


object ScanEnteredLines extends App {
    val msg = "Hello. Write something and pull enter"
    println(msg)

    val line = readLine()
    println("you entered: " + line)

    println("Thanks. And what is your name?")
    println(s"Hi, $readLine ")
}
