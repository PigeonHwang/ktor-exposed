package com.example.dbQuery

import com.example.DatabaseFactory.dbQuery
import com.example.model.post.Post
import com.example.model.post.PostSerializable
import com.example.model.post.PostUser
import com.example.model.post.Posts
import com.example.model.user.User
import com.example.model.user.UserSerializable
import com.example.model.user.Users
import org.jetbrains.exposed.dao.id.EntityIDFactory
import org.jetbrains.exposed.sql.EntityIDColumnType
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.deleteWhere

class PostDBQuery {
    suspend fun findAllPosts(): List<Post> = dbQuery {
        Post.all().toList()
    }

    suspend fun findPostById(input: Long): Post? = dbQuery {
        Post.findById(input)
    }

    suspend fun createPost(input: PostSerializable) = dbQuery {
        Post.new {
            title = input.title
            content = input.content
            userId = input.userId
        }
    }

    suspend fun updatePost(inputId: Long, input: PostSerializable) = dbQuery {
        var post = findPostById(inputId)
        if (post != null) {
            post.title = input.title
            post.content = input.content
            post.userId = input.userId
        }
    }

    suspend fun deletePost(input: Long) = dbQuery {
        Posts.deleteWhere { Posts.id eq input }
    }
}