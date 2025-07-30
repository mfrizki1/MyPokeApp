package id.calocallo.mypokeapp.presentation.auth

import id.calocallo.mypokeapp.domain.entity.User

data class AuthUiState(
    val isLoading: Boolean = false,
    val isSuccess: Boolean = false,
    val user: User? = null,
    val errorMessage: String? = null
)