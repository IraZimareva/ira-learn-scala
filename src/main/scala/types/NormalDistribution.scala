package types

import scala.math.{E, Pi, exp, pow, sqrt}

/* Pассчитать плотность вероятности нормального распределения по заданным математическому ожиданию μ,
среднеквадратичному отклонению σ и значению случайной величины x*/
object NormalDistribution extends App {
    def normalDistribution(mu: Double, sigma: Double, x: Double): Double = {
        val firstArg = 1/(sigma * sqrt(2 * Pi))
        val secondArg = exp(- pow((x - mu), 2) / (2 *  pow(sigma, 2)))
        firstArg * secondArg
    }

    println(normalDistribution(1, 1, 1))
}
