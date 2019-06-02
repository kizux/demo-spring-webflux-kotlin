package fr.kizux.kotlindemocoroutines

import fr.kizux.kotlindemocoroutines.repository.UserRepository
import kotlinx.coroutines.runBlocking
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.event.ApplicationReadyEvent
import org.springframework.boot.runApplication
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Component

@SpringBootApplication
class KotlinDemoCoroutinesApplication

fun main(args: Array<String>) {
    runApplication<KotlinDemoCoroutinesApplication>(*args)
}

@Component
class DataInitializer(private val userRepo: UserRepository) {
    @EventListener(value = [ApplicationReadyEvent::class])
    fun init() {
        runBlocking {
            userRepo.deleteAll()
        }

    }
}