package fr.kizux.kotlindemocoroutines.configuration

import fr.kizux.kotlindemocoroutines.handler.UserHandler
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.MediaType
import org.springframework.web.reactive.function.server.coRouter

@Configuration
class RouterConfig {

    @Bean
    fun userRoutes(userHandler: UserHandler) = coRouter {
        "/user".and(accept(MediaType.APPLICATION_JSON)).nest {
            GET("", userHandler::getAll)
            GET("/{id}", userHandler::get)
            POST("").and(contentType(MediaType.APPLICATION_JSON)).invoke(userHandler::create)
        }
    }
}