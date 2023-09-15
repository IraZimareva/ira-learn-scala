package classes

/*
* Ваша задача - спроектировать и реализовать класс официанта. Официант умеет принимать блюдо в заказ и в конце повторять,
*  что было заказано. Также он вежлив и представляется.

Требования
- имя класса Waiter
- сигнатура конструктора: (name: String, order: List[String])
- метод для заказа блюда giveMe, принимает название блюда
- метод complete, возвращает список того, что было заказано
- при своем появлении официант здоровается с гостем (ровно один раз)
- необходимо, чтобы была возможна следующая запись
val positions = waiter.giveMe("борщ").giveMe("хлеб").complete()*/
class Waiter(name: String, order: List[String]) {
    def giveMe(nameOfDish: String): Waiter = {
        new Waiter(name, order ::: List(nameOfDish))
    }
    def complete(): List[String] = order
    if (order.isEmpty) println(s"My name $name")
}

object Test extends App {
    val waiter = new Waiter("Иван", List.empty)
    val positions = waiter.giveMe("борщ").giveMe("хлеб").complete()
    println(s"Order: ${positions.mkString(" ")}")
}
