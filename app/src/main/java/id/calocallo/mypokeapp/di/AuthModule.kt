package id.calocallo.mypokeapp.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import id.calocallo.mypokeapp.data.repository.AuthRepositoryImpl
import id.calocallo.mypokeapp.domain.repository.AuthRepository

@Module
@InstallIn(SingletonComponent::class)

abstract class AuthModule {
    @Binds
    abstract fun bindAuthRepository(
        authRepositoryImpl: AuthRepositoryImpl
    ): AuthRepository


}