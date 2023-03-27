package types

import scala.io.StdIn.readLine

/*
* Cчитайте из консоли два числа: startIndex и endIndex. Они расположены в первой строке и разделены одним пробелом.
* Далее считайте строку str.
* Напечатайте в ответ входную строку, обратив порядок символов от startIndex до endIndex влючительно.
* */
object Strings {
    def main(args: Array[String]): Unit = {
        println("Введите 2 числа")
        val strFirst = readLine()
        println("Введите строку")
        val strSecond = readLine()

        val splitedStr = strFirst.split(" ")
        val startIndex = splitedStr.head.toInt
        val endIndex = splitedStr.last.toInt

        val firstPart = strSecond.take(startIndex)
        val secondPart = strSecond.takeRight(strSecond.length - 1 - endIndex)
        val reversebleStr = strSecond.drop(startIndex).dropRight(strSecond.length - 1 - endIndex).reverse
        println(firstPart + reversebleStr + secondPart)
    }
}
