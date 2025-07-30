package id.calocallo.mypokeapp.domain.usecase

import id.calocallo.mypokeapp.domain.repository.AuthRepository
import javax.inject.Inject

class CheckLoginStatusUseCase @Inject constructor(
    private val repository: AuthRepository
) {

    suspend operator fun invoke(): Boolean {
        return repository.isUserLoggedIn()
    }
}