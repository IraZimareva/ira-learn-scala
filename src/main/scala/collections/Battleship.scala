package collections

import collections.Naval.{Field, Fleet, Game, Point, Ship}
import com.typesafe.scalalogging.Logger

import scala.annotation.tailrec
import scala.io.StdIn

/* Многие играли в игру Морской бой. В игре используется квадратное поле клеток, на которое перед началом игры каждый из
 * игроков расставляет свои корабли.
 *
 * Представим клетку корабля как пару координат этой клетки на поле.
 *      type Point = (Int, Int)
 * Игровое поле может быть представлено как двумерный массив, хранящий для каждой ячейки булево значение, о том стоит ли на этой клетке корабль:
 *      type Field = Vector[Vector[Boolean]]
 * Проинтерпретируем корабль как список точек:
 *      type Ship = List[Point]
 * А множество всех кораблей на поле как ассоциативный массив. По строковому ключу (имени корабля) находится список точек корабля.
 *      type Fleet = Map[String, Ship]
 * Игра описывается её игровым полем и списком кораблей:
 *      type Game = (Field, Fleet)
 *
 * Данные типы объявлены в объекте Naval. Обращаем внимание на то, что все коллекции иммутабельны.
 * */
/*              Входные данные
    На вход в первой строке вам даётся число кораблей, которые игрок хочет добавить на поле. Далее мы задаем положения кораблей:
    первая строка - название корабля (одним словом) и число клеток, занимаемых им,
    последующие строки - координаты его клеток, по одной клетке (соответственно, по две координаты) на строку.
    */
/*              Задание
    Корабли добавляются на поле field (квадратное поле размера 10 на 10 клеток, задано заранее, булево значение
 * во всех его клетках проинициализировано как false, координаты нумеруются с нуля) в такой же очередности, как они
 * подаются на вход.
 * Необходимо проверять условия:
 * - Корабль не имеет изгибов (ширина корабля всегда единица).
 * - Длина корабля не больше четырёх.
 * - Корабли могут примыкать к границам поля, но не могут касаться друг друга, даже углами своих клеток.
 * Выведите имена кораблей, которые получилось расставить на поле.
 *
 * Для удобства предлагаем реализовать методы, сигнатуры которых написаны в шаблоне.
 */
object Battleship extends App {
    val logger = Logger(getClass.getName)

    // определить, подходит ли корабль по своим характеристикам
    def validateShip(ship: Ship): Boolean = {
        logger.info("Проверяем кораль на валидность")
        val (height, width) = ship.unzip
        if (ship.length > 4) false
        else if (height.count(_ == height.head) == height.length || width.count(_ == width.head) == width.length) true
        else false
    }

    // определить, можно ли его поставить
    def validatePosition(ship: Ship, field: Field): Boolean = {
        logger.info("Проверяем, возможно ли размещение корабля")
        // конструируем рамку вокруг корабля и учитываем возможный выход за рамки поля
        def buildBorderShip(ship: Ship): Ship = {
            val shipBoard = ship.flatMap { case (x, y) =>
                List(
                    (x - 1, y - 1),
                    (x - 1, y),
                    (x - 1, y + 1),
                    (x, y - 1),
                    (x, y),
                    (x, y + 1),
                    (x + 1, y - 1),
                    (x + 1, y),
                    (x + 1, y + 1)
                )
            }.distinct
            shipBoard.filterNot { case (x, y) => x < 0 || x > 9 || y < 0 || y > 9 }
        }
        // проверка, что границы корабля на поле не заняты другими кораблями (= false)
        buildBorderShip(ship).forall { case (x, y) => field(x)(y) == false }
    }

    // добавить корабль во флот
    def enrichFleet(fleet: Fleet, name: String, ship: Ship): Fleet = {
        fleet + (name -> ship)
    }

    // пометить клетки, которые занимает добавляемый корабль
    @tailrec
    def markUsedCells(field: Field, ship: Ship): Field = {
        val (x, y)                = ship.head
        val (firstRaws, lastRaws) = field.splitAt(x)
        val (firstCols, lastCols) = lastRaws.head.splitAt(y)
        val editedfirstCols       = firstCols :+ true
        val joinedRaw             = editedfirstCols ++ lastCols.tail
        val editedfirstRaws       = firstRaws :+ joinedRaw
        val joinedField           = editedfirstRaws ++ lastRaws.tail
        if (ship.length == 1) joinedField
        else markUsedCells(joinedField, ship.tail)
    }

    // логика вызовов методов выше
    def tryAddShip(game: Game, name: String, ship: Ship): Game = {
        val (field, fleet)     = game
        val isValidShip        = validateShip(ship)
        val isValidatePosition = validatePosition(ship, field)
        if (!isValidShip || !isValidatePosition) {
            logger.error("Размещение данного корабля невозможно")
            game
        } else {
            logger.info("Размещение данного корабля возможно")
            logger.info("Добавляем корабль во флот и на поле")
            val enrichedFleet = enrichFleet(fleet, name, ship)
            val enrichedField = markUsedCells(field, ship)
            new Game(enrichedField, enrichedFleet)
        }
    }

    def fillOneShip(): (String, Ship) = {
        println("Введите имя корабля и через пробел количество клеток")
        val nameShipAndFields     = StdIn.readLine
        val nameShipAndFieldsList = nameShipAndFields.split("\\s+").toList
        val nameShip              = nameShipAndFieldsList.head
        val countPointsShip       = nameShipAndFieldsList.tail.head.toInt
        @tailrec
        def fillPoint(input: List[Point]): List[Point] = {
            if (input.length == countPointsShip) input
            else {
                val pointInput = StdIn.readLine().split("\\s+").toList.map(_.toInt)
                val point      = (pointInput.head, pointInput.tail.head)
                fillPoint(input :+ point)
            }
        }
        println("Введите координаты клеток через пробел")
        val ship = fillPoint(List.empty[Point])
        (nameShip, ship)
    }

    @tailrec
    def startGame(game: Game, countShips: Int): Game = {
        if (countShips == 0) game
        else {
            val (nameShip, ship) = fillOneShip()
            logger.info("Размещаем корабль на поле")
            val enrichedGame = tryAddShip(game, nameShip, ship)
            startGame(enrichedGame, countShips - 1)
        }
    }

    // Начало программы
    val raw     = Vector.fill(10)(false)
    val field   = Vector.fill(10)(raw)
    val newGame = (field, Map.empty[String, Ship])
    logger.info("Начало размещения кораблей")
    println("Введите количество кораблей")
    val countShips         = StdIn.readInt() // число кораблей
    val (_, enrichedFleet) = startGame(newGame, countShips)
    logger.info("Все корабли размещены")
    println("\n Корабли, размещённые на поле")
    enrichedFleet.keySet.foreach(println(_))
}

object Naval {
    type Point = (Int, Int)              // клетка корабля
    type Field = Vector[Vector[Boolean]] // игровое поле с расположением кораблей
    type Ship  = List[Point]             // Корабль как список точек
    type Fleet = Map[String, Ship]       // Множество всех кораблей на поле. По строковому ключу (имени корабля) находится список точек корабля.
    type Game  = (Field, Fleet)          // Игра описывается её игровым полем и списком кораблей
}
