package id.calocallo.mypokeapp.domain.usecase

import id.calocallo.mypokeapp.domain.entity.Pokemon
import id.calocallo.mypokeapp.domain.repository.PokemonRepository
import javax.inject.Inject

class GetPokemonDetailUseCase @Inject constructor(
    private val pokemonRepository: PokemonRepository
) {
    suspend operator fun invoke(name: String): Result<Pokemon> {
        return pokemonRepository.getPokemonDetail(name)
    }
}