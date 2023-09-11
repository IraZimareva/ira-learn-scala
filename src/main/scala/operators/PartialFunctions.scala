package operators

/* Натуральный логарифм определен только на положительных числах.
 * Функция Math.log возвращает значение натурального логарифма для всех чисел больше 0 и NaN.
 * Напишите частичную функцию log, считающую логарифм с помощью Math.log и определенную только для положительных чисел.
 *
 * Ничего считывать и выводить в консоль не нужно, просто реализуйте функцию. */
object PartialFunctions extends App {
    val log: PartialFunction[Double, Double] = {
        case x if x > 0 => Math.log(x)
    }
}

/* В магазине красок проводится акция: на банки краски объемом от 5 до 10 литров (включительно) скидка 5%, на банки
 * больше 10 литров - 10%. Одна банка представлена кейс классом Jar(name: String, value: Int, price: Double), где name -
 * артикул, value - объем, price - цена в рублях.
 *
 * Напишите частичную функцию discount, которая при подстановке в метод collect списка банок, превратит его в список
 * строк. Каждая строка должна состоять из артикула и размера скидки в рублях, записанных через пробел. В список должны
 * входить только товары с ненулевой скидкой.
 *
 * После применения функции:
 *
 * List( "Морской синий 6л 150.0", "Огненно-красный 12л 500.0" ) */
object PartialFunctions2 extends App {

    case class Jar(name: String, value: Int, price: Double)
    val discount: PartialFunction[Jar, String]= {
        case Jar(name, value, price) if (5 <= value && value <= 10) => s"$name ${price * 0.05}"
        case Jar(name, value, price) if value > 10 => s"$name ${price * 0.1}"
    }

    val testData = List(
        Jar("Морской синий 6л", 6, 3000.0),
        Jar("Огненно-красный 12л", 12, 5000.0),
        Jar("Зеленый 1л", 1, 500.0)
    )
    println(testData.collect(discount))
}
