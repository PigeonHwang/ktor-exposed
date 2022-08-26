package com.example.model.user

import com.example.model.BaseEntity
import com.example.model.BaseEntityClass
import com.example.model.BaseTable
import com.example.model.PGEnum
import org.jetbrains.exposed.dao.id.EntityID

object Users: BaseTable() {
    val userName = varchar("username", 255)
    val password = varchar("password", 255)
    val userRole = customEnumeration("roles", "user_role", {value -> UserRole.valueOf(value as String)}, { PGEnum("user_role", it) })
}

class User(id: EntityID<Long>): BaseEntity(id, Users) {
    companion object: BaseEntityClass<User>(Users)
    var userName by Users.userName
    var password by Users.password
    var userRole by Users.userRole
}

class UserSerializable(
    var userName: String = "",
    var password: String = "",
    var userRole: UserRole = UserRole.USER
)

enum class UserRole {
    USER,
    ADMIN
}