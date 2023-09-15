package collections

/* List - одна из любимых коллекций скалистов. Её иммутабельность играет на руку при написании параллельных программ, а
 * её API позволяет эффективно работать с элементами, лежащими в начале коллекции. С задачей добавления одиночных
 * элементов в начало она справится хорошо (за константное время), так как реализована на основе односвязного списка, но
 * вот ассимптотическая оценка операции добавления таких элементов в конец вырастет до O(n) , где n - длина списка .
 *
 * И тут на сцену выходит структура данных под названием Difference List.
 *
 * Рассмотрим две операции над списками: операцию .prepend добавления элементов в начало списка и операцию .append
 * добавления элементов в конец списка. Каждую такую операцию можно рассматривать как некоторую функцию List[A] =>
 * List[A], где A - тип элементов списка, тогда некоторая цепочка таких операций - это композиция таких функций. Именно
 * за счёт такого приёма DiffList-ы позволяют избавиться от дорогостоящего добавления в конец, заменяя его на добавление
 * в начало.
 *
 * Вам необходимо реализовать имплементацию интерфейса DiffListImp[A] и методы prepend и append и result:
 * - Метод prepend принимает на вход список s, добавляемый в начало. Вернуть надо новый объект, сконструировав его от
 * новой функции. Эта функция должна возвращать список s , добавленный в начало к списку, возвращаемому из calculate.
 * - Метод append принимает на вход список s, добавляемый в конец. Вернуть надо новый объект, сконструировав его от
 * новой функции. Эта функция должна возвращать результат применения функции calculate к конкатенации списка s и
 * аргумента этой функции.
 * - Метод result применяет все накопленные операции и отдаёт итоговый список.
 *
 * Ваша задача - реализовать недостающие методы интерфейса.
 */

abstract class DiffList[A](calculate: List[A] => List[A]) {
    def prepend(s: List[A]): DiffList[A]

    def append(s: List[A]): DiffList[A]

    def result: List[A]
}

final class DiffListImpl[A](listFunc: List[A] => List[A]) extends DiffList[A](listFunc) {
    def prepend(s: List[A]): DiffListImpl[A] = {
        val concatFunc: List[A] => List[A] = list => s ::: listFunc(list)
        new DiffListImpl(concatFunc)
    }

    def append(s: List[A]): DiffListImpl[A] = {
        val concatFunc: List[A] => List[A] = list => listFunc(s ::: list)
        new DiffListImpl(concatFunc)
    }

    def result: List[A] = {
        listFunc(List.empty)
    }
}

object Main extends App {
    val l1 = List(1, 2, 3)
    val l2 = List(4, 5, 6)
    val dl = new DiffListImpl[Int](identity)

    val result = dl.append(l2).prepend(l1).result
    println(s"result ${result.toString}") // List(1,2,3,4,5,6)
}
