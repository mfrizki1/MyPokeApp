package id.calocallo.mypokeapp.presentation.detail

import id.calocallo.mypokeapp.domain.entity.Pokemon

data class PokemonDetailUiState(
    val isLoading: Boolean = false,
    val pokemon: Pokemon? = null,
    val errorMessage: String? = null
)