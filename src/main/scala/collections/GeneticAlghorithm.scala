package collections

import scala.annotation.tailrec

/* Некоторые генетические алгоритмы для генерации новых хромосом из старых используют приём под названием кроссинговер.
 * Будем представлять хромосому с генами [xxxxx] в виде списка List('x', 'x', 'x', 'x', 'x') . Тогда суть приёма
 * заключается в следующем:
 *
 * Берутся две хромосомы одинаковой длины, например [xxxxx] и [yyyyy]. Списки для них будут выглядеть так:
 * List('x', 'x', 'x', 'x', 'x') List('y', 'y', 'y', 'y', 'y')
 *
 * Выбираются так называемые `точки кроссинговера`. В нашем случае это некоторые индексы в диапазоне [1, длина списка
 * генов хромосомы]. Пусть выбраны индексы 1 и 3.
 * Для каждого индекса, по возрастанию, хромосомы обмениваются своими частями, стоящими после этого индекса. В нашем
 * случае после кроссинговера по индексу 1:
 * List('x', 'y', 'y', 'y', 'y') List('y', 'x', 'x', 'x', 'x')
 *
 * после дальнейшего кроссинговера по индексу 3:
 * List('x', 'y', 'y', 'x', 'x') List('y', 'x', 'x', 'y', 'y')
 *
 * Выведите результат хромосомы после кроссинговера, сначала первую, затем вторую. Без пробелов и знаков препинания. */
object GeneticAlghorithm extends App {
    val points: List[Int] = List(1, 3)                    // точки кроссинговера
    val chr1: List[Char]  = List('x', 'x', 'x', 'x', 'x') // первая хромосома
    val chr2: List[Char]  = List('y', 'y', 'y', 'y', 'y') // вторая хромосома

    @tailrec
    def crossoverAll(first: List[Char], second: List[Char], points: List[Int]): (List[Char], List[Char]) = {
        if (points.length < 1) (first, second)
        else {
            val firstRes  = crossover(first, second, points.head)
            val secondRes = crossover(second, first, points.head)
            crossoverAll(firstRes, secondRes, points.tail)
        }
    }

    def crossover(first: List[Char], second: List[Char], point: Int): List[Char] = {
        val check: PartialFunction[(Char, Int), Char] = {
            case (simbol, index) if index < point => simbol
            case (_, index) if index >= point     => second(index)
        }
        first.zipWithIndex.collect(check)
    }
    val (crossChr1, crossChr2) = crossoverAll(chr1, chr2, points)
    crossChr1.foreach(print(_))
    println()
    crossChr2.foreach(print(_))
}
