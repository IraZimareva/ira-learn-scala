package classes

/* Дан список координат в трёхмерном пространстве. Вам нужно написать класс Point, который будет описывать точку в
 * трёхмерном пространстве и объект-компаньон со следующими функциями:
 *  apply - фабрика, принимает три координаты и возвращает экземпляр типа Point
 *  average - принимает List[Point] и вычисляет усреднённую точку между всеми координатами, либо точку с началом осей координат, если её невозможно рассчитать
 *  show - принимает Point и превращает её в строку, состоящую из координат разделённых через пробел
 * Для каждой строки будет вызвана функция apply, затем для всех точек будет вызвана функция average.
 * На выход будет передан результат функции show, примененный к усреднённой точке. */
class Point(val x: Double, val y: Double, val z: Double) {
    override def toString: String = s"$x $y $z"
}

object Point {
    def apply(x: Double, y: Double, z: Double): Point = new Point(x, y, z)
    def average(points: List[Point]): Point = {
        def avg(values: List[Double]): Double = values.sum / values.length
        if (points.isEmpty) new Point(0, 0, 0)
        else {
            val avgX = avg(points.map(_.x))
            val avgY = avg(points.map(_.y))
            val avgZ = avg(points.map(_.z))
            new Point(avgX, avgY, avgZ)
        }
    }
    def show(point: Point): String = point.toString
}
