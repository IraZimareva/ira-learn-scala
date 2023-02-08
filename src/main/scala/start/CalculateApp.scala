package start

import scala.io.StdIn.readInt

//Напишите программу, которая считывает построчно два целых числа и считает их разницу.
object SubtractionApp extends App {
    val x = readInt()
    val y = readInt()
    val result = x-y
    println("result of subtraction: " + result)
}

//Напишите программу, которая считает произведение трех целых чисел.
object MultipleApp extends App {
    val x = readInt()
    val y = readInt()
    val z = readInt()
    println("result of multiple: " + (x * y * z))
//    println((1 to 3).map(_ => readInt()).product)
}
