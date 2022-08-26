package com.example

import io.ktor.server.engine.*
import io.ktor.server.netty.*
import com.example.plugins.*
import io.ktor.server.application.*

/*suspend fun <T> dbQuery(block: () -> T): T =
    withContext(Dispatchers.IO) {
        transaction { block() }
    }*/

fun main() {
    embeddedServer(Netty, port = 3000, host = "127.0.0.1") {
        configureDatabase()
        configureRouting()
        configureSerialization()
        //graphQLModule()
    }.start(wait = true)
}

//fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)