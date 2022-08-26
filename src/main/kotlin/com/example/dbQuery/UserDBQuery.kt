package com.example.dbQuery

import com.example.DatabaseFactory.dbQuery
import com.example.model.user.User
import com.example.model.user.UserRole
import com.example.model.user.UserSerializable
import com.example.model.user.Users
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.deleteWhere

class UserDBQuery {
    suspend fun findAllUsers(): List<User> = dbQuery {
        User.all().toList()
    }

    suspend fun findUserById(input: Long): User? = dbQuery {
        User.findById(input)
    }

    suspend fun createUser(input: UserSerializable) = dbQuery {
        User.new {
            userName = input.userName
            password = input.password
            userRole = input.userRole
        }
    }

    suspend fun updateUser(inputId: Long, input: UserSerializable) = dbQuery {
        var user = findUserById(inputId)
        if (user != null) {
            user.userName = input.userName
            user.password = input.password
            user.userRole = input.userRole
        }
    }

    suspend fun deleteUser(input: Long) = dbQuery {
        Users.deleteWhere { Users.id eq input }
    }
}