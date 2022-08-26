package com.example.plugins

import com.example.controller.postRoutingModule
import com.example.controller.userRoutingModule
import io.ktor.server.application.*

fun Application.configureRouting() {
    userRoutingModule()
    postRoutingModule()
}
