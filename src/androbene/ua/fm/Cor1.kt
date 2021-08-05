package androbene.ua.fm

import kotlinx.coroutines.*

fun main() = runBlocking {
    val job = launch(Dispatchers.Default) {
        println("Hello from: " + Thread.currentThread().name)
        delay(1000)
    }
    println("Done!")
    job.join()
}