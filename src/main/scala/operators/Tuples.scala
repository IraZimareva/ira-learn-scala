package operators

/* Напишите функцию swap3(tuple: (Int, Int, Int)): (Int, Int, Int), возвращающую кортеж с элементами в обратном порядке.
 * swap3((1, 2, 3)) == (3, 2, 1) */
object Tuples extends App {
    def swap3(tuple: (Int, Int, Int)): (Int, Int, Int) = {
        (tuple._3, tuple._2, tuple._1)
    }
}
