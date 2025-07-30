package id.calocallo.mypokeapp.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("current_user")
data class CurrentUserEntity(
    @PrimaryKey
    val id: Int = 1,
    val userId: Int,
)