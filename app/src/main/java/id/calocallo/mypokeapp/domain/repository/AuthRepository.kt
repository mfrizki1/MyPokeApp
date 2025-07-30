package id.calocallo.mypokeapp.domain.repository

import id.calocallo.mypokeapp.domain.entity.User
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    suspend fun login(username: String, password: String): Result<User>
    suspend fun saveCurrentUser(user: User)
    fun getCurrentUser(): Flow<User?>
    suspend fun register(username: String, password: String, repassword: String): Result<User>
    suspend fun logout()

    suspend fun isUsernameExists(username: String): Boolean
    suspend fun isUserLoggedIn(): Boolean
}