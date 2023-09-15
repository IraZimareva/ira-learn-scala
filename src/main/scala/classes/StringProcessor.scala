package classes

/*  Дан трейт trait StringProcessor. Напишите несколько его реализаций:
tokenDeleter - в методе process обрабатывает строку, удаляя из неё перечисленные знаки препинания: запятая, двоеточие, точка с запятой.
toLowerConvertor - заменяет все прописные буквы на строчные.
shortener - если строка имеет размер больше 20 символов, он оставляет первые 20 и добавляет к ней "...".*/
trait StringProcessor {
    def process(input: String): String
}

object StringProcessorImpl  extends App {
    val tokenDeleter = new StringProcessor {
        override def process(input: String): String = {
            input.replace(",","").replace(":","").replace(";", "")
        }
    }

    val shortener = new StringProcessor {
        override def process(input: String): String = {
            if (input.length > 20) {
                s"${input.take(20)}..."
            }
            else input
        }
    }

    val toLowerConvertor = new StringProcessor {
        override def process(input: String): String = input.toLowerCase()
    }

    val testString = "Привет, меня зовут Ира; Я scala разработчик"
    println(toLowerConvertor.process(testString))
    println(shortener.process(testString))
    println(tokenDeleter.process(testString))
}
