package androbene.ua.fm

import kotlinx.coroutines.*

fun main() = runBlocking {
    val job = launch(Dispatchers.Default) {
        try {
            workWithResources()
        } finally {
            withContext(NonCancellable) { // без этого контекста не сработает очистка
                delay(500L)
                println("Clean 1 up!")
                delay(500L)
                println("Clean 2 up!")
                delay(500L)
            }
        }
    }
    delay(1000L)
    println("Cancel!")
    job.cancel()
    println("Done!")
}

suspend fun workWithResources() {
    val startTime = System.currentTimeMillis()
    var nextPrintTime = startTime
    var i = 0
    while (i < 5) {
        yield() // без этой точки приостановки вся workWithResources() будет единой монолитной ТЯЖЁЛОЙ РАБОТОЙ
                // и отработает до самого конца даже в случае вызова job.cancel() в процессе
        // print a message twice a second
        if (System.currentTimeMillis() >= nextPrintTime) {
            println("Hello ${i++}")
            nextPrintTime += 500L
        }
    }
}
