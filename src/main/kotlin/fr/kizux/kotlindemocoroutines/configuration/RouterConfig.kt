package fr.kizux.kotlindemocoroutines.configuration

import fr.kizux.kotlindemocoroutines.handler.UserHandler
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.server.coRouter

@Configuration
class RouterConfig {

    @Bean
    fun userRoutes(userHandler: UserHandler) = coRouter {
        "/user".nest {
            GET("", userHandler::getAll)
            GET("/{id}", userHandler::get)
            POST("", userHandler::create)
        }
    }
}