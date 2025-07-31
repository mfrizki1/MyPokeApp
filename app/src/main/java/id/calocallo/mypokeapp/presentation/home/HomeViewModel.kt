package id.calocallo.mypokeapp.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import id.calocallo.mypokeapp.domain.usecase.GetPokemonUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getPokemonUseCase: GetPokemonUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    private var currentOffset = 0
    private val limit = 10

    init {
        loadPokemons()
    }

    fun loadPokemons() {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(
                isLoading = true,
                errorMessage = null
            )

            getPokemonUseCase(offset = 0, limit = limit).fold(
                onSuccess = { pokemons ->
                    _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        pokemons = pokemons,
                        isSuccess = true
                    )
                    currentOffset = pokemons.size
                },
                onFailure = { error ->
                    _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        errorMessage = error.message,
                    )
                }
            )
        }
    }

    fun loadMorePokemons() {
        if (_uiState.value.isLoading) return

        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true)

            getPokemonUseCase(offset = currentOffset, limit = limit).fold(
                onSuccess = { newPokemons ->
                    val currentPokemons = _uiState.value.pokemons
                    _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        pokemons = currentPokemons + newPokemons,
                        isSuccess = true
                    )
                    currentOffset += newPokemons.size
                },
                onFailure = { error ->
                    _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        errorMessage = error.message
                    )
                }
            )
        }
    }
}