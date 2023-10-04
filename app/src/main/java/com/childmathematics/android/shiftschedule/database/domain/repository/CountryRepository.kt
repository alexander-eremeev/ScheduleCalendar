package com.childmathematics.android.shiftschedule.database.domain.repository

import com.childmathematics.android.shiftschedule.database.domain.model.Countries
import kotlinx.coroutines.flow.Flow

interface CountryRepository {
    fun getCountriesFromRoom(): Flow<List<Countries>>

    fun getCountryFromRoom(countryId: Int): Flow<Countries>

    fun addCountryToRoom(shortName: Countries)

    fun updateCountryInRoom(shortName: Countries)

    fun deleteCountryFromRoom(shortName: Countries)
}
/*
import kotlinx.coroutines.flow.Flow
import ro.alexmamo.roomjetpackcompose.domain.model.Book

interface BookRepository {
    fun getBooksFromRoom(): Flow<List<Book>>

    fun getBookFromRoom(id: Int): Flow<Book>

    fun addBookToRoom(book: Book)

    fun updateBookInRoom(book: Book)

    fun deleteBookFromRoom(book: Book)
}
 */
