package com.childmathematics.android.shiftschedule.database.di

import android.content.Context
import androidx.room.Room
import com.childmathematics.android.shiftschedule.database.data.network.CountryDAO
import com.childmathematics.android.shiftschedule.database.data.network.CountryDB
import com.childmathematics.android.shiftschedule.database.data.repository.CountryRepositoryImp
import com.childmathematics.android.shiftschedule.database.domain.repository.CountryRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)

class AppModule {
    @Provides
    fun provideCountryDb(
        @ApplicationContext
        context : Context
    ) = Room.databaseBuilder(
        context,
        CountryDB::class.java,
        "Countries"
    ).build()

    @Provides
    fun provideCountryDao(
        countryDB: CountryDB
    ) = countryDB.countryDAO()

    @Provides
    fun provideCountryRepository(
        countryDao: CountryDAO
    ): CountryRepository = CountryRepositoryImp(
        countryDao =  countryDao
    )


}
/*
import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import ro.alexmamo.roomjetpackcompose.core.Constants.Companion.BOOK_TABLE
import ro.alexmamo.roomjetpackcompose.data.network.BookDao
import ro.alexmamo.roomjetpackcompose.data.network.BookDb
import ro.alexmamo.roomjetpackcompose.data.repository.BookRepositoryImpl
import ro.alexmamo.roomjetpackcompose.domain.repository.BookRepository

@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    @Provides
    fun provideBookDb(
        @ApplicationContext
        context : Context
    ) = Room.databaseBuilder(
        context,
        BookDb::class.java,
        BOOK_TABLE
    ).build()

    @Provides
    fun provideBookDao(
        bookDb: BookDb
    ) = bookDb.bookDao()

    @Provides
    fun provideBookRepository(
        bookDao: BookDao
    ): BookRepository = BookRepositoryImpl(
        bookDao = bookDao
    )
}
 */