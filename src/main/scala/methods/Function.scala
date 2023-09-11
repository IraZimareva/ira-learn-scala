package methods


/*
    В объекте LessonData определен метод searchInArray,  который находит в массиве элементы, удовлетворяющие заданному условию:
    Задание: исправьте определение функции searchOdd: List[Int] => List[Int] так, чтобы программа стала компилироваться.
*/

    object Function extends App {
        val searchOdd: List[Int] => List[Int]  = list => LessonData.searchInArray(_ % 2 == 1, list)
        println(searchOdd(List(8,11,12))) // List(11)
    }

object LessonData{
    def searchInArray(cond: Int => Boolean, array: List[Int]): List[Int] = {
        array.filter(cond)
    }
}
