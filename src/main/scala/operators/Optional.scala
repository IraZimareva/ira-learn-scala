package operators

/* Метод List[A].find(p: A => Boolean): Option[A] пытается найти первый элемент, удовлетворяющий предикату p.
 * Напишите функцию foo(list: List[Int]): Int, которая находит в списке list первое число, которое делится на 3, и
 * домножает его на 2. Если таких чисел нет, функция должна вернуть 0.
 *
 * Пользуйтесь только find, анонимными функциями и методами Option. */
object Optional extends App {
    def foo(list: List[Int]): Int = {
        list.find(num => num % 3 == 0).map(_ * 2).getOrElse(0)
    }
    println(foo(List(1, 2, 3, 4, 5, 6))) // == 6
}
