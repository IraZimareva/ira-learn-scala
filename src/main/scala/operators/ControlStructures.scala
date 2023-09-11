package operators
import scala.io.StdIn

/*
    Считайте с клавиатуры число n и выведите на экран все упорядоченные пары взаимно простых чисел от 1 до n,
    не включая n в лексикографическом порядке.
    Подсказка: задача очень просто решается, если вспомнить возможности BigInt.
*/
object ControlStructures extends App {
    val number = StdIn.readInt()
    for(i <- 1 until number; j <- 1 until number)  {
        val bigI = BigInt(i)
        val bigJ = BigInt(j)
        if (bigI.gcd(bigJ) == 1 ) println(s"$i $j")
    }
}
