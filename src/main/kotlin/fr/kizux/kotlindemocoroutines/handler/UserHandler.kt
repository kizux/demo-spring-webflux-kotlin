package fr.kizux.kotlindemocoroutines.handler

import fr.kizux.kotlindemocoroutines.model.User
import fr.kizux.kotlindemocoroutines.repository.UserRepository
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.*

@Component
class UserHandler(private val userRepo: UserRepository) {

    suspend fun getAll(req: ServerRequest): ServerResponse = ServerResponse.ok().bodyAndAwait(userRepo.findAll())

    suspend fun create(req: ServerRequest): ServerResponse {
        val body = req.awaitBody<User>()
        val createdUser = userRepo.save(body)
        return ServerResponse.ok().bodyAndAwait(createdUser)
    }
}