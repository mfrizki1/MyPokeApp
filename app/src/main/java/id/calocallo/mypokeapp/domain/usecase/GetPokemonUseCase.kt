package id.calocallo.mypokeapp.domain.usecase

import id.calocallo.mypokeapp.domain.entity.Pokemon
import id.calocallo.mypokeapp.domain.repository.PokemonRepository
import javax.inject.Inject


class GetPokemonUseCase @Inject constructor(
    private val repository: PokemonRepository
) {
    suspend operator fun invoke(offset: Int = 0, limit: Int = 10): Result<List<Pokemon>> {
        return repository.getPokemons(offset, limit)
    }
}