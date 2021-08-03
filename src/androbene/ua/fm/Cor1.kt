package androbene.ua.fm

import kotlinx.coroutines.*

fun main() = runBlocking {
    launch (Dispatchers.Default) {
        println("Hello from: " + Thread.currentThread().name)
    }
    println("Done!")
}