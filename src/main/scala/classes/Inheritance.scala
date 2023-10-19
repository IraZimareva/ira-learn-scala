package classes

/* Имеется трейт "Животное", в котором реализован метод "подать голос", а также поле "звук", который животное издает.
Задание: реализуйте классы "Кошка", "Собака" и "Рыба". В последнем случае метод voice должен печатать на экран фразу "Fishes are silent!".
*/
trait Animal {
    val sound: String
    def voice: Unit = println("voice: " + sound)
}

class Cat extends Animal {
    override val sound: String = "Meow!"
}

class Dog extends Animal {
    override val sound: String = "Woof!"
}

class Fish extends Animal {
    override lazy val sound: String = ""
    override def voice: Unit = println("Fishes are silent!")
}

object Inheritance extends App{
    val animals = Seq(new Cat, new Dog, new Fish)
    animals.foreach(_.voice)
}
