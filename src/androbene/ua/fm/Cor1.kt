package androbene.ua.fm

import kotlinx.coroutines.*

fun main() = runBlocking {
    val job = launch(Dispatchers.Default) {
        delay(1000)
        println("Hello from: " + Thread.currentThread().name)
        delay(1000)
    }
    println("Waiting for job...")
    job.join()
    println("-----Done-----")
}