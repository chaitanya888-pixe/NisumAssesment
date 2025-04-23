package com.sample.data.di

import com.sample.data.repository.RepositoryImpl
import com.sample.domain.repository.UsersRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryAbstract {
    @Binds
    @Singleton
  abstract  fun getRepoImpl(repositoryImpl: RepositoryImpl):UsersRepository


}