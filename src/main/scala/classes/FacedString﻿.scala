package classes

import scala.io.StdIn

/* Объекты-компаньоны, в которых реализован метод unapply называются extractor objects. Этот метод позволяет "разбирать"
объекты, получая аргументы, из которых он был создан. Например, в кейс-классах, он реализован по умолчанию. Это позволяет
записывать конструкции pattern matching для кейс-классов.

Ваша задача - реализовать метод unapply у объекта FacedString. Считать из потока ввода строку, сделать паттерн матчинг с ней,
который определит, могла ли она быть результатом некоторого вызова apply. Если получилось выделить строку, от которой
она была сконструирована, вывести эту строку на экран, если нет - вывести "Could not recognize string".
Подсказка:
Если регулярные выражения вызывают у вас затруднения, можно обойтись без них. У строк есть полезные методы, например, startsWith.*/


object FacedString {
    def apply(input: String) = s"*_*$input*_*"

    def unapply(arg: String): Option[String] = {
        arg match {
            case string if arg.startsWith("*_*") && arg.endsWith("*_*") => Some(arg.slice(3, arg.length - 3))
            case _ => None
        }
    }
}

object Main {
    def main(args: Array[String]) {
        StdIn.readLine() match {
            case FacedString(str) => println(str)
            case _ => println("Could not recognize string")
        }
    }
}