package com.example.service

import com.example.dbQuery.PostDBQuery
import com.example.model.post.Post
import com.example.model.post.PostSerializable
import com.example.model.user.User
import com.example.model.user.UserSerializable

class PostService {
    var postDBQuery = PostDBQuery()
    suspend fun findAllPosts(): List<PostSerializable> {
        var postList = postDBQuery.findAllPosts()
        var returnList = mutableListOf<PostSerializable>()
        postList.forEach {
            returnList.add(toSerializable(it))
        }
        return returnList
    }

    suspend fun findPostById(input: Long): PostSerializable? {
        var post = postDBQuery.findPostById(input)
        return postDBQuery.findPostById(input)?.let { toSerializable(it) }
    }

    suspend fun createPost(input: PostSerializable): Post {
        return postDBQuery.createPost(input)
    }

    suspend fun updatePost(inputId: Long, input: PostSerializable) {
        postDBQuery.updatePost(inputId, input)
    }

    suspend fun deletePost(input: Long) {
        postDBQuery.deletePost(input)
    }

    private fun toSerializable(post: Post): PostSerializable {
        var postSerializable = PostSerializable()
        postSerializable.title = post.title
        postSerializable.content = post.content
        postSerializable.userId = post.userId.toString().toLong()
        return postSerializable
    }
}