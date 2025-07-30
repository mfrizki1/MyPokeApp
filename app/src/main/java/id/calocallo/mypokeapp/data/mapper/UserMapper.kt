package id.calocallo.mypokeapp.data.mapper

import id.calocallo.mypokeapp.data.local.entity.UserEntity
import id.calocallo.mypokeapp.domain.entity.User

fun UserEntity.toDomain(): User {
    return User(
        id,
        username,
        password
    )
}

fun User.toEntity(): UserEntity {
    return UserEntity(id, username, password)
}