package id.calocallo.mypokeapp.domain.usecase

import id.calocallo.mypokeapp.domain.entity.User
import id.calocallo.mypokeapp.domain.repository.AuthRepository
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val repository: AuthRepository
) {
    suspend operator fun invoke(username:String, password:String): Result<User>{

        if(password.isBlank()) {
            return Result.failure(Exception("Password tidak boleh kosong"))
        }

        return repository.login(username, password).onSuccess {
             repository.saveCurrentUser(it)
        }
    }
}