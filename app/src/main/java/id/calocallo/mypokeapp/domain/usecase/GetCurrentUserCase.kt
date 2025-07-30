package id.calocallo.mypokeapp.domain.usecase

import id.calocallo.mypokeapp.domain.entity.User
import id.calocallo.mypokeapp.domain.repository.AuthRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCurrentUserCase @Inject constructor(
    private val repository: AuthRepository
) {
    suspend operator fun invoke(): Flow<User?> {
        return repository.getCurrentUser()
    }
}