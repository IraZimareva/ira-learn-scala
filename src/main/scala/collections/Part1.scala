package collections

import scala.io.StdIn

/* Получив некоторый произвольный список нулей и единиц, разделите их на два списка.
 * Результат выведите через запятую, каждый в отдельную строку, первыми выводятся нули. */
object Part1 extends App {
    val ints: List[Int] = List(0, 1, 1, 0, 1, 0, 0, 1, 1, 1, 0, 1, 0)
    val (zero, one)     = ints.partition(_ == 0)
    println(zero.mkString(","))
    println(one.mkString(","))
}

/* В данной задаче необходимо реализовать алгоритм нахождения k-ой порядковой статистики
 * (https://neerc.ifmo.ru/wiki/index.php?title=%D0%9F%D0%BE%D0%B8%D1%81%D0%BA_k-%D0%BE%D0%B9_%D0%BF%D0%BE%D1%80%D1%8F%D0%B4%D0%BA%D0%BE%D0%B2%D0%BE%D0%B9_%D1%81%D1%82%D0%B0%D1%82%D0%B8%D1%81%D1%82%D0%B8%D0%BA%D0%B8),
 * матожидание времени работы которого составляет O(n). Для этого реализуйте метод kOrder (его сигнатура в шаблоне).
 *
 * На вход в первой строке подаётся k - номер порядковой статистики, которую надо найти. Во второй строке - элементы
 * набора.
 * Вам также нужно реализовать ввод / вывод. */
object Part2 extends App {
    def kOrder(list: List[Int], k: Int): Int = {
        val number          = list.head // опорный элемент
        val (less, greater) = list.partition(_ < number)
        less.length match {
            case count if count == k - 1 => number
            case count if count >= k     => kOrder(less, k)
            case count if count < k      => kOrder(greater.tail, k - less.length - 1)
        }
    }

//    val testString = "3 8 1 12 23" // output: 12
//    val testString = "4 7 6 5 12 9 5" // output: 5
//    val listInputInt = testString.split("\\s+").toList.map(_.toInt)

    val k            = StdIn.readLine.toInt
    val inputString  = StdIn.readLine
    val listInputInt = inputString.split("\\s+").toList.map(_.toInt)
    println(kOrder(listInputInt, k))
}
