package id.calocallo.mypokeapp.data.remote.response

import com.google.gson.annotations.SerializedName

data class PokemonDetailResponse(
    @SerializedName("name")
    val name: String,
    @SerializedName("abilities")
    val abilities: List<AbilityResponse>
) {
    data class AbilityResponse(
        @SerializedName("ability")
        val ability: AbilityDetailResponse
    ) {
        data class AbilityDetailResponse(
            @SerializedName("name")
            val name: String
        )
    }
}