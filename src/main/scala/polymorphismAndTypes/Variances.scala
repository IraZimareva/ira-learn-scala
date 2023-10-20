package polymorphismAndTypes

/* Заданы тип A и его подтип B, а также функции, которые умеют распечатывать их поле value
 *
 * Также существует важный метод methodPrint, который принимает на вход экземпляр типа B и функцию, которая умеет
 * распечатывать его значение. methodPrint(printB, objB) компилируется без проблем, однако, иногда нужно задействовать
 * функцию printA.
 *
 * Задание: исправьте компиляцию кода. В конце будет вызывано: methodPrint(printA, objB) */

object Variances extends App {
    class A(val value: String)

    object A {
        def apply(value: String) = new A(value)
    }

    case class B(override val value: String) extends A(value)

    def methodPrint(f: FunctionPrint[B], obj: B) = {
        f(obj)
    }

    class FunctionPrint[-T <: A](prefix: String) {
        def apply(t: T): Unit = println(prefix + " " + t.value)
    }

    object FunctionPrint {
        def apply[T <: A](prefix: String) = new FunctionPrint[T](prefix)
    }

    val objB   = B("It is a B.value")
    val objA   = A("It is a A.value")
    val printA = FunctionPrint[A]("A-value:")
    val printB = FunctionPrint[B]("B-value:")
    methodPrint(printB, objB)
    methodPrint(printA, objB)
}

/* Исправьте определение класса Pair, чтобы он стал ковариантным.
 *
 * Метод printNames принимает на вход пары с объектами типа Person и печатает их имена. Однако нам хочется на вход этому
 * методу передавать также и Student. Для этого нужна ковариантность пар: Pair[Student] <: Pair[Person].
 *
 * Подсказка: в определении Pair в методе replaceFirst тип T стоит в контрвариантной позиции, что мешает быть Pair
 * ковариантным по T. */

object Variances2 extends App {

    class Person(val name: String)

    class Student(name: String, val course: Int) extends Person(name)

    class Pair[+T](val first: T, val second: T) {
        def replaceFirst[BT >: T](newValue: BT): Pair[BT] = {
            new Pair(newValue, second)
        }
    }

    def printNames(pair: Pair[Person]): Unit = {
        println("1: " + pair.first.name + "  2: " + pair.second.name)
    }

    val pair = new Pair(new Student("Pavel", 1), new Student("Oleg", 5))
    printNames(pair.replaceFirst(new Person("Oliver")))
}
