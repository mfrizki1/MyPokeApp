package id.calocallo.mypokeapp.domain.usecase

import id.calocallo.mypokeapp.domain.entity.User
import id.calocallo.mypokeapp.domain.repository.AuthRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCurrentUserUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    operator fun invoke(): Flow<User?> = authRepository.getCurrentUser()
}