package com.darklabs.repository

import com.auth0.jwt.JWTVerifier
import com.darklabs.db.entity.UserEntity
import com.darklabs.model.User
import com.darklabs.service.TokenManager
import io.ktor.auth.jwt.*
import org.ktorm.database.Database
import org.ktorm.dsl.*
import org.mindrot.jbcrypt.BCrypt

interface AuthRepository {
    suspend fun checkUserExists(user: User): User?
    suspend fun insertUser(user: User): Int
    suspend fun validatePassword(password: String?, savedPassword: String?): Boolean
    suspend fun generateToken(user: User): String?
    suspend fun getJwtPrincipal(jwtCredential: JWTCredential): JWTPrincipal?
    fun verifyToken(): JWTVerifier
    fun getRealm(): String
}

class AuthRepositoryImpl(private val database: Database, private val tokenManager: TokenManager) : AuthRepository {

    override suspend fun checkUserExists(user: User): User? {

        val userName = user.username.lowercase()

        return database.from(UserEntity)
            .select()
            .where { UserEntity.userName eq userName }
            .map {
                User(
                    id = it[UserEntity.id],
                    username = it[UserEntity.userName].orEmpty(),
                    password = it[UserEntity.password].orEmpty()
                )
            }
            .firstOrNull()
    }

    override suspend fun insertUser(user: User): Int {

        val userName = user.username.lowercase()
        val password = user.hashedPassword()

        return database.insert(UserEntity) {
            set(UserEntity.userName, userName)
            set(UserEntity.password, password)
        }
    }

    override suspend fun validatePassword(password: String?, savedPassword: String?): Boolean {
        return BCrypt.checkpw(password, savedPassword)
    }

    override suspend fun generateToken(user: User): String? {
        return tokenManager.generateJwtToken(user)
    }

    override suspend fun getJwtPrincipal(jwtCredential: JWTCredential): JWTPrincipal? {
        val username = jwtCredential.payload.getClaim("username").asString()
        return if (username.isNotEmpty()) {
            val user = checkUserExists(
                user = User(
                    username = username,
                    password = ""
                )
            )
            if (user != null) {
                JWTPrincipal(jwtCredential.payload)
            } else {
                null
            }
        } else {
            null
        }
    }

    override fun verifyToken(): JWTVerifier {
        return tokenManager.verifyJwtToken()
    }

    override fun getRealm(): String {
        return tokenManager.realm
    }
}