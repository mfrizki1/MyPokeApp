package id.calocallo.mypokeapp.data.repository

import id.calocallo.mypokeapp.data.remote.PokemonApiService
import id.calocallo.mypokeapp.domain.entity.Pokemon
import id.calocallo.mypokeapp.domain.repository.PokemonRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PokemonRepositoryImpl @Inject constructor(
    private val pokemonService: PokemonApiService
) : PokemonRepository {
    override suspend fun getPokemons(
        offset: Int,
        limit: Int
    ): Result<List<Pokemon>> {
        return try {
            val response = pokemonService.getPokemons(offset, limit)
            val pokemons = response.results.map { itemDto ->
                Pokemon(name = itemDto.name)
            }
            Result.success(pokemons)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}