package fr.kizux.kotlindemocoroutines.model

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table
import java.time.LocalDateTime

const val TABLE_USER_NAME: String = "users"

@Table(TABLE_USER_NAME)
data class User(
        @Id val id: Long? = null,
        @Column("email") val email: String? = null,
        @Column("sign_in_date") val signInDate: LocalDateTime)