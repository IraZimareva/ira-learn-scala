package methods

import scala.annotation.tailrec

object GenericMethods1 extends App {
    /* Найдите ошибку в коде и реализуйте эффективное вычисление числа Фибоначчи, используя хвостовую рекурсию.
     * В n лежит порядковый номер числа Фибоначчи, требуется вывести его значение.
     * 0, 1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, 144, 233, 377, 610, 987, 1597, 2584, 4181, 6765, 10946, 17711,...
     * 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, ... */
    @tailrec
    def fibs(n: Int, currentNumber: Int = 1, f1: BigInt = 0, f2: BigInt = 1): BigInt = {
        if (n == currentNumber)
            f2
        else {
            // put your code here
            fibs(n, currentNumber + 1, f2, f1 + f2)
        }
    }
    println(fibs(5))  // 5
    println(fibs(9))  // 34
    println(fibs(17)) // 1597
}

/* Задание: напишите обобщенный метод middle возвращающий средний элемент из любого экземпляра Iterable[T] Если
 * элементов четное число, необходимо выбрать элемент с бОльшим порядковым номером. */
object GenericMethods2 extends App {
    def middle[T](data: Iterable[T]): T = {
        // put your code here
        val middleNum               = data.size / 2
        val (_, secondPart) = data.splitAt(middleNum)
        secondPart.head
    }

    println(middle("Scala") == 'a')
    println(middle(Seq(1, 7, 5, 9)) == 5)
}
