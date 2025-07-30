package id.calocallo.mypokeapp.domain.usecase

import id.calocallo.mypokeapp.domain.entity.User
import id.calocallo.mypokeapp.domain.repository.AuthRepository
import javax.inject.Inject

class RegisterUseCase @Inject constructor(
    private val repository: AuthRepository
) {
    suspend operator fun invoke(
        username: String,
        password: String,
        repassword: String
    ): Result<User> {

        if (username.isBlank()) {
            return Result.failure(Exception("Username tidak boleh kosong"))
        }

        if (password.count() < 6) {
            return Result.failure(Exception("Password minimal 6 karakter"))
        }

        if (password != repassword) {
            return Result.failure(Exception("Password harus sama"))
        }

        if(repository.isUsernameExists(username)) {
            return Result.failure(Exception("Username sudah terdaftar"))
        }

        return repository.register(username, password, repassword).onSuccess { user ->
            repository.saveCurrentUser(user)
        }

    }
}