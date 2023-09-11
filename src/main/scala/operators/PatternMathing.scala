package operators
/* Сase-класс Pet(name: String, says: String) описывает питомца, с именем name, издающего звук says.
 * Известно, что все, кто говорит "meow" или "nya" - кошки, все, кого зовут "Rex" - собаки, а цифры "0" или "1"
 * (обратите внимание, что нужно проверять наличие символов, а не целочисленных типов) в строке says есть только у
 * роботов. (Кошек и роботов не могут звать "Rex", собаки не мяукают)
 *
 * Используя pattern-matching, определите вид питомца в переменной pet.
 * Выведите "cat" для кошек, "dog" для собак, "robot" для роботов и "unknown" иначе. */

object PatternMathing1 extends App {
    case class Pet(name: String, says: String)
    val pet = Pet("cat", "meow")
//    val pet = Pet("rghatGPT", "0fse1")

    val isRobot = "[01]+".r
    val kind = pet match {
        case Pet(_, "meow" | "nya") => "cat"
        case Pet("Rex", _)          => "dog"
//        case    Pet(_, says)  if says.contains("0") || says.contains("1") => "robot"
        case Pet(_, isRobot()) => "robot"
        case _ => "unknown"
    }
    println(kind)
}

/*
    На вход программе подается имя пользователя, email и список его коммитов, возможно пустой.
    В данном контексте коммит - это просто строка.

    Считанные построчно данные лежат в списке input. После имени мог стоять как пробел, так и перенос строки.
    Каждый коммит был в отдельной строчке. Другими словами, первые два элемента списка это либо имя и электронная почта,
    либо имя с почтой через пробел и первый коммит.

    Используя pattern-matching и регулярные выражения напечатайте через пробел имя пользователя и домен электронной почты
    (все, что стоит после собаки). Для некорректных данных напачатайте "invalid".
*/

object PatternMathing2 extends App {
//    val input = List ("oleg" ,"noleg@email.com" , "7bdaf0a1be3", "a8af118b1a2" , "28d74b7a3fe")
    val input = List ("oleg noleg@email.com" , "7bdaf0a1be3", "a8af118b1a2" , "28d74b7a3fe")
//    val input = List ("oleg noleg@email.com")
//    val input = List ("oleg" , "noleg@email.com")

    val regName = "([a-zA-Z]+)".r
    val regEmail = "(\\w)+@(\\w+\\.\\w+)".r
    val reg = "([a-zA-Z]+)\\s(\\w)+@(\\w+\\.\\w+)".r

    val result: String = input match {
        case List (regName(name), regEmail(_, domen) , tail@_*) => s"$name $domen"
        case List (reg(name, _, domen), tail@_*) => s"$name $domen"
        case _ => "invalid"
    }
    println(result)
}
