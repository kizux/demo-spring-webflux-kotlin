package fr.kizux.kotlindemocoroutines.repository

import fr.kizux.kotlindemocoroutines.model.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.reactive.awaitSingle
import org.springframework.data.r2dbc.core.*
import org.springframework.stereotype.Component
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneOffset

@Component
class UserRepository(private val dbClient: DatabaseClient) {
    fun findAll(): Flow<User> = dbClient.select().from(USER_TABLE_NAME).asType<User>().fetch().flow()

    suspend fun findOne(id: Long): User? = dbClient.execute()
            .sql("SELECT * FROM $USER_TABLE_NAME WHERE $USER_COLUMN_ID_NAME = $id")
            .asType<User>()
            .fetch().awaitOneOrNull()

    suspend fun save(user: User): User = dbClient.insert()
            .into<User>()
            .table(USER_TABLE_NAME)
            .using(user)
            .map { t, u ->
                User(
                        t.get(USER_COLUMN_ID_NAME) as Long?,
                        t.get(USER_COLUMN_EMAIL_NAME) as String?,
                        LocalDateTime.ofInstant(t.get(USER_COLUMN_BIRTHDATE_NAME) as Instant?, ZoneOffset.UTC)
                )
            }
            .awaitOne()

    suspend fun deleteAll(): Int = dbClient.delete()
            .from(User::class.java)
            .fetch()
            .rowsUpdated().awaitSingle()
}