package androbene.ua.fm

import kotlinx.coroutines.TimeoutCancellationException
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.withTimeout

suspend fun main() = coroutineScope {
    println(1)
    val res = withTimeout(2000) {
        println("inside")
        delay(1500)
        100500
    }
    println(2)
    println(res)

    println("------------------------------------------------------------")
    try {
        runWithTimeout()
    } catch (e: TimeoutCancellationException) {
        println("TimeoutCancellationException: Time is out.")
    }
}

suspend fun runWithTimeout() {
    withTimeout(350) {
        repeat(5) { n ->
            delay(100)
            println("repeat $n")
        }
    }
}