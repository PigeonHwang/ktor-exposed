package com.example.service

import com.example.dbQuery.UserDBQuery
import com.example.model.user.User
import com.example.model.user.UserSerializable
import com.example.model.user.Users
import org.jetbrains.exposed.sql.*

class UserService {
    private var userDBQuery = UserDBQuery()
    suspend fun findAllUsers(): List<UserSerializable> {
        var userList = userDBQuery.findAllUsers()
        var returnList = mutableListOf<UserSerializable>()
        userList.forEach {
            returnList.add(toSerializable(it))
        }
        return returnList
    }

    suspend fun findUserById(input: Long): UserSerializable? {
        return userDBQuery.findUserById(input)?.let { toSerializable(it) }
    }

    suspend fun createUser(input: UserSerializable): User {
        return userDBQuery.createUser(input)
    }

    suspend fun updateUser(inputId: Long, input: UserSerializable) {
        userDBQuery.updateUser(inputId, input)
    }

    suspend fun deleteUser(input: Long) {
        userDBQuery.deleteUser(input)
    }

    private fun toSerializable(user: User): UserSerializable {
        var userSerializable = UserSerializable()
        userSerializable.userName = user.userName
        userSerializable.password = user.password
        userSerializable.userRole = user.userRole
        return userSerializable
    }
}