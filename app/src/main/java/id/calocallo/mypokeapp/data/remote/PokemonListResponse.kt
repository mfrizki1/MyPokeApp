package id.calocallo.mypokeapp.data.remote

data class PokemonListResponse(
    val count:Int,
    val results: List<PokemonItemDto>
)

data class PokemonItemDto(
    val name: String,
    val url: String,
)
