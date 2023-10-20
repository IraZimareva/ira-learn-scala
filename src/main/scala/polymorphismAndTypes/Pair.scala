package polymorphismAndTypes

/* Реализуйте неизменяемый класс Pair с методом swap, возвращающим пару, где компоненты поменяны местами. */

case class Pair[T, S](first: T, second: S) {
    def swap: Pair[S, T] = Pair(second, first)
}

/* Исправьте определение класса Pair так, чтобы можно было создавать пары из различных типов (например, Int или String).
 * В этой задаче элементы пары имеют одинаковый тип. Метод smaller должен возвращать наименьшее значение из пары.
 *
 * Подсказка: трэйт Ordered[A] определяет оператор сравнения, что позволяет удобно сравнивать различные элементы.
 * Например, BigInt <: Ordered[BigInt], поэтому можно писать: BigInt(1) < BigInt(2) == true. */

case class Pair2[T <% Ordered[T]](first: T, second: T) {
    def smaller =
        if (first < second) first
        else second
}

object Main extends App {
    val pair = Pair(123, "Oleg").swap
    require(pair.first == "Oleg")
    require(pair.second == 123)

    val p = Pair2(BigInt("1000000000000000"), BigInt("7000000000000000"))
    require(p.smaller == BigInt("1000000000000000"))
}
