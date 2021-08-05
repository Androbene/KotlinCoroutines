package androbene.ua.fm

import kotlinx.coroutines.*

//      Exploring lazy running coroutines
fun main() = runBlocking {
    println("-----Start-----")
    val scope = CoroutineScope(CoroutineName("scope-1") + Job())
    val lazy = scope.launch(start = CoroutineStart.LAZY) {
        println("LAZY STARTED in ${Thread.currentThread().name}")
        delay(1000) // Change this delay less than 500ms to avoid lazy-coroutine cancellation
        println("LAZY FINISHED")
    }
    println("lazy created in ${Thread.currentThread().name}")
    println("lazy is ${if (!lazy.isActive && !lazy.isCancelled && !lazy.isCompleted) "NEW" else "not NEW"}")
    delay(1000)

    lazy.invokeOnCompletion { println("invokeOnCompletion(): lazy was ${if (lazy.isCancelled) "CANCELED" else "COMPLETED"} ") }
    print("lazy is going to start")
    repeat(10) {
        delay(200)
        print(".")
    }
    println("go!\n")
    lazy.start() // Starting lazy coroutine
    delay(500)
    lazy.cancelAndJoin()
    println("-----Done-----")
}