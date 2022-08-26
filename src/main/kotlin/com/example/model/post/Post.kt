package com.example.model.post

import com.example.model.BaseEntity
import com.example.model.BaseEntityClass
import com.example.model.BaseTable
import com.example.model.user.User
import com.example.model.user.Users
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.sql.*

object Posts: BaseTable() {
    val title = varchar("title", 255)
    val content = text("content")
    //val userId = reference("user_id", Users.id)
    val userId = long("user_id")
}

class Post(id: EntityID<Long>): BaseEntity(id, Posts) {
    companion object: BaseEntityClass<Post>(Posts)
    var title by Posts.title
    var content by Posts.content
    var userId by Posts.userId
}

class PostSerializable(
    var title: String = "",
    var content: String = "",
    var userId: Long = -1
)

class PostUser(
    var post: Post,
    var user: User
)