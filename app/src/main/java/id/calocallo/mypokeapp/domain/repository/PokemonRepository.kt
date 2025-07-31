package id.calocallo.mypokeapp.domain.repository

import id.calocallo.mypokeapp.domain.entity.Pokemon

interface PokemonRepository {
    suspend fun getPokemons(offset:Int, limit:Int): Result<List<Pokemon>>
}