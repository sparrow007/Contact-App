package com.jackandphantom.contactapp.di

import com.jackandphantom.contactapp.data.repository.LocalRepository
import com.jackandphantom.contactapp.data.repository.Repository
import dagger.Binds
import dagger.Module


@Module
abstract class RepoModule {

    @Binds
    abstract fun provideRepository(localRepository: LocalRepository): Repository
}