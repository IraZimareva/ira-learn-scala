package collections

import scala.annotation.tailrec
import scala.io.StdIn

/* В переменной list лежит отсортированный в порядке возрастания список целых чисел.
 * Со списком необходимо выполнить следующие операции:
 * - Взять все числа меньше 100 (список может быть большим)
 * - Выбрать все числа, которые делятся на 4
 * - Поделить их на 4
 * - Вывести на экран в отдельной строке каждый элемент, кроме последнего */
object Part3 extends App {
    val list = List(10, 20, 30, 40, 50, 60, 70, 80, 90, 100, 110, 120, 130, 140, 150)
    list.takeWhile(_ < 100).filter(_ % 4 == 0).map(_ / 4).dropRight(1) foreach (println(_))
}

/* Считайте числа из консоли до слова END. С полученным списком необходимо выполнить:
 * - выбрать каждый второй элемент
 * - каждый выбранный элемент умножить на 2
 * - вывести в консоль сумму элементов полученного списка Рекомендация: для считывания в список можно использовать
 * Stream */
object Part4 extends App {
    @tailrec
    def fillStream(stream: Stream[String]): Stream[String] = {
        val num = StdIn.readLine
        if (num == "END") stream
        else fillStream(stream :+ num)
    }
    val inputString = fillStream(Stream.empty)
    val result =
        inputString.map(_.toInt).zipWithIndex.filter { case (_, index) => index % 2 != 0 }.map(_._1).map(_ * 2).sum
    println(result)
}
