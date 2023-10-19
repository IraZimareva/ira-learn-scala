package classes

/* Программист Олег решил выдавать кредиты. Для этого ему нужно реализовать класс CreditBank и его метод issueCredit,
 * который выводит на консоль слово "CREDIT". К сожалению, у Олега нет своего капитала, и он решил собирать выдаваемый
 * кредит по кусочкам в других банках. Имеется пять банков. Все они наследуют трейт: AbstractBank
 *
 * Задание: помогите Олегу собрать кредит, подмешав в нужной последовательности банки в CreditBank и реализовав метод
 * issueCredit ("CREDIT" должен собираться из кусочков a–f). */

trait AbstractBank {
    def a: Char
    def b: Char
    def c: Char
    def d: Char
    def e: Char
    def f: Char
    def issueCredit: Unit
}

trait BankA extends AbstractBank {
    override val b = 'T'
    override val d = 'R'
    override val f = 'I'
}

trait BankB extends AbstractBank {
    override val a = 'E'
    override val f = 'D'
}

trait BankC extends AbstractBank {
    override val b = 'C'
    override val d = 'D'
}

trait BankD extends AbstractBank {
    override val b = 'C'
    override val c = 'R'
    override val d = 'E'
}

trait BankE extends AbstractBank {
    override val b = 'C'
    override val a = 'I'
    override val e = 'T'
}

class CreditBank extends AbstractBank with BankE with BankB with BankD with BankA with BankC {
    override def issueCredit = println("" + b + c + a + d + f + e) // for example: a + b + c + d + e + f
}

object Inheritance2 extends App {
    val creditBank = new CreditBank
    creditBank.issueCredit
}
