package id.calocallo.mypokeapp.presentation.home

import id.calocallo.mypokeapp.domain.entity.Pokemon

data class HomeUiState(
    val isLoading: Boolean = false,
    val isSuccess: Boolean = false,
    val pokemons: List<Pokemon> = emptyList(),
    val searchQuery: String = "",
    val errorMessage: String? = null
)
