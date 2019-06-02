package fr.kizux.kotlindemocoroutines.handler

import fr.kizux.kotlindemocoroutines.model.User
import fr.kizux.kotlindemocoroutines.repository.UserRepository
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.*

@Component
class UserHandler(private val userRepo: UserRepository) {

    suspend fun getAll(req: ServerRequest): ServerResponse = ServerResponse.ok().bodyFlowAndAwait(userRepo.findAll())

    suspend fun create(req: ServerRequest): ServerResponse {
        val body: User = req.awaitBody()
        return ServerResponse.ok().bodyAndAwait(userRepo.save(body))
    }

    suspend fun get(req: ServerRequest): ServerResponse {
        val id: Long = req.pathVariable("id").toLong()
        val userFound: User? = userRepo.findOne(id)
        return if(userFound != null) ServerResponse.ok().bodyAndAwait(userFound) else ServerResponse.notFound().buildAndAwait()
    }
}