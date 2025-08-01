package id.calocallo.mypokeapp.domain.entity

data class Pokemon(
    val name: String,
    val abilities: List<Ability> = emptyList()
) {
    data class Ability(
        val ability: AbilityDetail
    ) {
        data class AbilityDetail(
            val name: String
        )
    }
}

