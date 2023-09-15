package collections

/*
* Даны три отсортированных по возрастанию списка целых чисел: list1, list2, list3. Пользуясь только for-comprehension
(а также математическими операторами и функцией println) вывести пары различных чисел из первого и второго списка
* (то есть (x,y) такие, что x != y и x — из list1, а y — из list2), произведение которых дает число из третьего списка.

Пары должны быть отсортированны сначала по первому элементу, потом по второму.
Возможно, в стандартный поток будут выведены повторяющиеся пары, оставлять только уникальные не нужно!
*/
object ForComprehension extends App {
    val list1 = List(1, 3, 5, 7)
    val list2 = List(2, 4, 6, 8)
    val list3 = List(1, 3, 5, 8, 10, 12, 14)

    val result = for {
        x <- list1
        z <- list3
        y <- list2 if x!=y && (x * y ==  z)
    } yield {
        println(x,y)
    }
}

/*Вам даны четыре микросервиса
    def service1: Either[String, Double]
    def service2(res1: Double): Either[String, Int]
    def service3: String
    def service4(res1: Double, res2: Int, res3: String): Either[String, String]
Первый, второй и четвертый возвращают Either с ошибкой слева или результатом справа.
service2 должен получить на вход результат работы первого сервиса, а четвертый - трех предыдущих.
Используя только for-comprehension реализуйте функцию result, которая возвращает результат работы четвертого сервиса.
*/
object ForComprehension2 extends App {
    def service1: Either[String, Double] = ???
    def service2(res1: Double): Either[String, Int] = ???
    def service3: String = ???
    def service4(res1: Double, res2: Int, res3: String): Either[String, String] = ???

    def result =  {
        for {
            service1Val <- service1
            service2Val <- service2(service1Val)
            service4Val <- service4(service1Val,service2Val,service3)
        } yield {
            service4Val
        }
    }
}