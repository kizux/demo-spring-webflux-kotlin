package fr.kizux.kotlindemocoroutines.repository

import fr.kizux.kotlindemocoroutines.model.TABLE_USER_NAME
import fr.kizux.kotlindemocoroutines.model.User
import kotlinx.coroutines.flow.Flow
import org.springframework.data.r2dbc.core.DatabaseClient
import org.springframework.data.r2dbc.core.asType
import org.springframework.data.r2dbc.core.await
import org.springframework.data.r2dbc.core.flow
import org.springframework.stereotype.Component

@Component
class UserRepository(private val dbClient: DatabaseClient) {

    fun findAll(): Flow<User> = dbClient.select().from(TABLE_USER_NAME).asType<User>().fetch().flow()

    suspend fun save(user: User) = dbClient.insert().into(User::class.java).table(TABLE_USER_NAME).using(user).await()
}