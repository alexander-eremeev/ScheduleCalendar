package com.childmathematics.android.shiftschedule.di

import com.childmathematics.android.shiftschedule.domain.repository.CountryRepository
import com.childmathematics.android.shiftschedule.domain.repository.CountryRepositoryImpl
import com.childmathematics.android.shiftschedule.domain.repository.NonWorkingDaysRepository
import com.childmathematics.android.shiftschedule.domain.repository.NonWorkingDaysRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import jakarta.inject.Singleton
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Singleton
    @Binds
    abstract fun bindCountryRepository(
//        countryRepositoryImpl: CountryRepository
        countryRepositoryImpl: CountryRepositoryImpl
    ): CountryRepository
/*
@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindMovieRepository(
        impl: MovieRepositoryImpl
    ): MovieRepository
}

 */
    @Binds
    @Singleton
    abstract fun bindNonWorkingDaysRepository(
        nonWorkingDaysRepositoryImpl: NonWorkingDaysRepositoryImpl
    ): NonWorkingDaysRepository

//    @Provides
//    @Singleton
//    fun provideNoteRepository(
//        dao: UserDao
//    ): UserRepository {
//        return UserRepositoryImpl(dao)
//    }

}
