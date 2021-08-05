package androbene.ua.fm

import kotlinx.coroutines.*

fun main() {
    
    val handlerInParent = CoroutineExceptionHandler { context, exception ->
        println("Caught in parent: $exception")
    }
    val handlerDeepest = CoroutineExceptionHandler { context, exception ->
        println("Caught in handlerDeepest: $exception")
    }
    val scope = CoroutineScope(Job())
    scope.launch (handlerInParent) { //      словим вот тут !!! (у родителя)
        launch {
            launch {
                launch (handlerDeepest) { //       тут - не словим ! (внутренний launch распространит исключение до родителя)
                    delay(100)
                    throw Exception("Failed coroutine")
                }
            }
        }
    }
    Thread.sleep(1000)
}