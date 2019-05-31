package fr.kizux.kotlindemocoroutines

import fr.kizux.kotlindemocoroutines.model.User
import fr.kizux.kotlindemocoroutines.repository.UserRepository
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.runBlocking
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.event.ApplicationReadyEvent
import org.springframework.boot.runApplication
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Component
import java.time.LocalDateTime

@SpringBootApplication
class KotlinDemoCoroutinesApplication

fun main(args: Array<String>) {
	runApplication<KotlinDemoCoroutinesApplication>(*args)
}

@Component
class DateInitialize(private val userRepo: UserRepository) {

	@EventListener(value = [ApplicationReadyEvent::class])
	fun init() {
		runBlocking {
			userRepo.save(User(email="j@hn.doe", signInDate=LocalDateTime.now()))
			userRepo.findAll().onEach { user -> println("Here is $user") }
		}
	}
}