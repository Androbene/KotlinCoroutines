package androbene.ua.fm

import kotlinx.coroutines.*
import java.util.concurrent.Executors

fun main() = runBlocking {

    println("1: " + Thread.currentThread().name)

    launch(Dispatchers.Default) {
        println("2: " + Thread.currentThread().name)
    }

    launch(Dispatchers.IO) {
        println("3: " + Thread.currentThread().name)
    }

    customDispatcher()
    println("-----Done-----")
}

suspend fun customDispatcher() {
    val dispatcher: ExecutorCoroutineDispatcher = Executors.newFixedThreadPool(2).asCoroutineDispatcher()
    withContext(dispatcher) {
        delay(100)
        println("4: " + Thread.currentThread().name)
        dispatcher.close()
    }
}
