package com.example.controller

import com.example.model.post.PostSerializable
import com.example.model.user.UserSerializable
import com.example.service.PostService
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

var postService = PostService()

fun Application.postRoutingModule() {
    // Starting point for a Ktor app:
    routing {
        get("/allposts") {
            var result = postService.findAllPosts()
            call.respond(result)
        }
        get("/post{id?}") {
            var id = call.parameters["id"]?.toLong()
            var result = id?.let { it -> postService.findPostById(it) }
            if(result != null) {
                call.respond(result)
            }else {
                call.respondText("no post!")
            }
        }
        post("/createpost") {
            var userId = call.parameters["userId"]?.toLong()
            var inputPost = call.receive<PostSerializable>()
            var post = postService.createPost(inputPost)
            call.respondRedirect("/post${post.id}")
        }
        post("/updatepost{id?}") {
            var id = call.parameters["id"]?.toLong()
            var inputpost = call.receive<PostSerializable>()
            var result = id?.let { it -> postService.updatePost(it, inputpost) }
            call.respondRedirect("/post$id")
        }
        post("/deletepost{id?}") {
            var id = call.parameters["id"]?.toLong()
            var result = id?.let { it -> postService.deletePost(it) }
            call.respondRedirect("/allposts")
        }
    }
}