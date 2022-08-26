package com.example.controller

import com.example.model.user.UserSerializable
import com.example.service.UserService
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

var userService = UserService()

fun Application.userRoutingModule() {
    // Starting point for a Ktor app:
    routing {
        get("/allusers") {
            var result = userService.findAllUsers()
            call.respond(result)
        }
        get("/user{id?}") {
            var id = call.parameters["id"]?.toLong()
            var result = id?.let { it -> userService.findUserById(it) }
            if(result != null) {
                call.respond(result)
            }else {
                call.respondText("no user!")
            }
        }
        post("/createuser") {
            var inputUser = call.receive<UserSerializable>()
            var user = userService.createUser(inputUser)
            call.respondRedirect("/user${user.id}")
        }
        post("/updateuser{id?}") {
            var id = call.parameters["id"]?.toLong()
            var inputUser = call.receive<UserSerializable>()
            var result = id?.let { it -> userService.updateUser(it, inputUser) }
            call.respondRedirect("/user$id")
        }
        post("/deleteuser{id?}") {
            var id = call.parameters["id"]?.toLong()
            var result = id?.let { it -> userService.deleteUser(it) }
            call.respondRedirect("/allusers")
        }
    }
}