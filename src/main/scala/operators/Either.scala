package operators

/* Правильной называется дробь, у которой модуль числителя меньше модуля знаменателя.
 * Будем представлять дробь парой (числитель, знаменатель).
 *
 * Реализуйте операцию деления divide(p: (Int, Int))(q: (Int, Int)): Either[String, (Int, Int)], возвращающую результат
 * деления p на q или текст ошибки. Проверьте корректность входных данных,
 * - если входные дроби неправильные, верните ошибку Left("Invalid input").
 * - Если делитель нулевой, верните Left("Zero divisor").
 * - Если в результате получилась неправильная дробь, ошибка Left("Improper result").
 *
 * Сократите результат до простой дроби. Воспользуйтесь алгоритмом Евклида, разобранным в модуле про кортежи, или
 * методом BigInt.gcd . */

object Either extends App {
    def divide(p: (Int, Int))(q: (Int, Int)): Either[String, (Int, Int)] = {
        def checkInputValue(x: (Int, Int)): Either[String, (Int, Int)] = {
            val (numerator, denominator) = x
            if (numerator.abs < denominator.abs) Right(x) else Left("Invalid input")
        }

        def divideZero(p: (Int, Int))(q: (Int, Int)): Either[String, (Int, Int)] = {
            val (numeratorP, denominatorP) = p
            val (numeratorQ, denominatorQ) = q
            if (denominatorP == 0 || numeratorQ == 0 || denominatorQ == 0) Left("Zero divisor") else Right(q)
        }

        def checkResult(x: (Int, Int)): Either[String, (Int, Int)] = {
            val (numerator, denominator) = x
            if (numerator.abs < denominator.abs) Right(x) else Left("Improper result")
        }

        for {
            _ <- divideZero(p)(q)
            _ <- checkInputValue(p)
            _ <- checkInputValue(q)
            resNumerator   = p._1 * q._2
            resDenominator = p._2 * q._1
            checkedRes <- checkResult((resNumerator, resDenominator))
        } yield {
            val (numeratorRes, denominatorRes) = checkedRes
            val nod                            = BigInt(numeratorRes).gcd(BigInt(denominatorRes)).toInt
            (numeratorRes / nod, denominatorRes / nod)
        }
    }

    // Tests
    divide(1, 0)(1, 2) == Left("Zero divisor")
    divide(1, 2)(1, 0) == Left("Zero divisor")
    divide(1, 2)(0, 2) == Left("Zero divisor")

    divide(3, 4)(1, 10) == Left("Improper result")
    divide(1, 2)(1, 2) == Left("Improper result")

    divide(2, 1)(1, 7) == Left("Invalid input")
    divide(1, 2)(7, 1) == Left("Invalid input")
    divide(1, 1)(2, 2) == Left("Invalid input")
    divide(2, 1)(3, 1) == Left("Invalid input")

    divide(1, 2)(2, 3).isRight
    divide(-1, 2)(2, 3).isRight
    divide(1, -2)(2, 3).isRight
    divide(1, 2)(-2, 3).isRight
    divide(1, 2)(2, -3).isRight

    /* Напишите функцию flatten(options: List[Option[Int]]): List[Int], возвращающую список из всех не-None элементов
     * списка.
     * Пользуйтесь частичными функциями. */
    def flatten(options: List[Option[Int]]): List[Int] = {
        val checkNone: PartialFunction[Option[Int], Int] = { case Some(value) =>
            value
        }
        options.collect(checkNone)
    }
}
