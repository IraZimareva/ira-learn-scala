package types

object Types extends App {
    val tmp1: String = "Hello"
    val tmp2: Any = tmp1
    val tmp3: Unit = Math.PI
    val tmp4: AnyVal = Math.PI

    println(tmp2)
    println(tmp3)
    val printFunc = println(tmp4)
    println(printFunc)
}
