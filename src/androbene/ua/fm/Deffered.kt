package androbene.ua.fm

import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay

suspend fun main() = coroutineScope {

    val numDeferred1 = async { sum(1, 2, 1400) }
    val numDeferred2 = async { sum(3, 4, 2200) }
    val numDeferred3 = async { sum(5, 6, 50) }
    val num1 = numDeferred1.await()
    println(1)
    val num2 = numDeferred2.await()
    println(2)
    val num3 = numDeferred3.await()

    println("number1: $num1  number2: $num2  number3: $num3")
    println("-----Done-----")
}

suspend fun sum(a: Int, b: Int, time: Int): Int {
    delay(time.toLong()) // имитация продолжитльной работы
    println("${time.toLong()}-ms task done")
    return a + b
}