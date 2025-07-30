package id.calocallo.mypokeapp.data.repository

import id.calocallo.mypokeapp.data.local.dao.CurrentUserDao
import id.calocallo.mypokeapp.data.local.dao.UserDao
import id.calocallo.mypokeapp.data.local.entity.CurrentUserEntity
import id.calocallo.mypokeapp.data.local.entity.UserEntity
import id.calocallo.mypokeapp.data.mapper.toDomain
import id.calocallo.mypokeapp.domain.entity.User
import id.calocallo.mypokeapp.domain.repository.AuthRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthRepositoryImpl @Inject constructor(
    private val userDao: UserDao,
    private val currentUserDao: CurrentUserDao
) : AuthRepository {
    override suspend fun login(
        username: String,
        password: String
    ): Result<User> {
        return try {
            val userEntity = withContext(Dispatchers.IO) {
                userDao.getUser(username, password)
            }
            if (userEntity != null) {
                Result.success(userEntity.toDomain())
            } else {
                Result.failure(Exception("Username atau password salah"))
            }
        } catch (e: Exception) {
            Result.failure(Exception("Login gagal: ${e.message}"))
        }

    }

    override suspend fun register(
        username: String,
        password: String,
        repassword: String
    ): Result<User> {
        return try {
            val userEntity = UserEntity(
                username = username, password = password
            )
            val userId = userDao.insertUser(userEntity)
            val savedUser = userEntity.copy(id = userId.toInt())

            Result.success(savedUser.toDomain())
        } catch (e: Exception) {
            Result.failure(Exception("Register gagal: ${e.message}"))
        }
    }

    override suspend fun saveCurrentUser(user: User) {
        currentUserDao.setCurrentUser(
            CurrentUserEntity(userId = user.id)
        )
    }

    override fun getCurrentUser(): Flow<User?> {
        return currentUserDao.getCurrentUser().map { entity ->
            entity?.toDomain()
        }
    }


    override suspend fun logout() {

    }

    override suspend fun isUsernameExists(username: String): Boolean {
        return withContext(Dispatchers.IO) {
            userDao.isUsernameExists(username)
        }
    }

    override suspend fun isUserLoggedIn(): Boolean {
        return currentUserDao.hasCurrentUser()
    }
}


