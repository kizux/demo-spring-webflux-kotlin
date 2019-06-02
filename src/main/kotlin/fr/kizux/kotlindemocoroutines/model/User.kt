package fr.kizux.kotlindemocoroutines.model

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table
import java.time.LocalDateTime

const val USER_TABLE_NAME: String = "users"
const val USER_COLUMN_ID_NAME = "id"
const val USER_COLUMN_EMAIL_NAME = "email"
const val USER_COLUMN_BIRTHDATE_NAME = "birthdate"

@Table(USER_TABLE_NAME)
data class User(
        @Id @Column(USER_COLUMN_ID_NAME) val id: Long? = null,
        @Column(USER_COLUMN_EMAIL_NAME) val email: String? = null,
        @Column(USER_COLUMN_BIRTHDATE_NAME) val birthDate: LocalDateTime? = null)