package com.example.model

import org.jetbrains.exposed.dao.*
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.LongIdTable
import org.jetbrains.exposed.sql.javatime.CurrentTimestamp
import org.jetbrains.exposed.sql.javatime.datetime
import org.postgresql.util.PGobject
import java.time.LocalDateTime

class PGEnum<T : Enum<T>>(enumTypeName: String, enumValue: T?) : PGobject() {
    init {
        value = enumValue?.name
        type = enumTypeName
    }
}

abstract class BaseTable : LongIdTable() {
    val createdAt = datetime("created_at").defaultExpression(CurrentTimestamp()).clientDefault { LocalDateTime.now() }
    val updatedAt = datetime("updated_at").defaultExpression(CurrentTimestamp()).clientDefault { LocalDateTime.now() }
}

abstract class BaseEntity(id: EntityID<Long>, table: BaseTable) : LongEntity(id) {
    val createdAt by table.createdAt
    var updatedAt by table.updatedAt
}

abstract class BaseEntityClass<T : BaseEntity>(table: BaseTable): LongEntityClass<T>(table) {
    init {
        EntityHook.subscribe { action ->
            if (action.changeType == EntityChangeType.Updated) {
                action.toEntity(this)?.updatedAt = LocalDateTime.now()
            }
        }
    }
}
