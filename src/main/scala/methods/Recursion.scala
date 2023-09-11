package methods

/*
Раскомментируйте строчку в методе fibs и допишите код так, чтобы метод возвращал число Фибоначчи по его порядковому номеру;
 а затем исправьте ошибку компиляции.
 0, 1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, 144, 233, 377, 610, 987, 1597, 2584, 4181, 6765, 10946, 17711,...
 0, 1, 2, 3, 4, 5, 6, 7,  8,  9,  10, 11, 12,  13,  14,  15,  16,  17,   ...
*/
object Recursion extends App{
    def fibs(num: Int) = {
        def recurFib(pos: Int, first: Int, second: Int): Int = {
            if (pos >= num) first + second
            else recurFib(pos + 1, second, first + second)
        }
        if (num == 1) 1
        else if (num == 2) 1
        else recurFib(3, 1, 1)
    }

    println(fibs(5)) //5
    println(fibs(9)) //34
    println(fibs(17)) //1597
}
