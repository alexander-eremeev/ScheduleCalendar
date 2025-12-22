package com.childmathematics.android.shiftschedule.presentation.ui.nonworkingdays.uimodels

import com.childmathematics.android.shiftschedule.data.models.CountryEntity
import com.childmathematics.android.shiftschedule.data.models.NonWorkingDaysEntity


// ViewState для управления данными состояния пользовательского интерфейса
data  class NonWorkingDaysViewState (
    val isLoading: Boolean = false,
    val error: Boolean = false,
    val holiDaysYear: List<NonWorkingDaysEntity> = emptyList(),
    val holiDays: List<NonWorkingDaysEntity> = emptyList(),
    val holiDaysOnlyDays: List<Int> = emptyList() ,
    val nonWorkingDaysYear: List<NonWorkingDaysEntity> = emptyList(),
    val nonWorkingDays: List<NonWorkingDaysEntity> = emptyList(),
    val nonWorkingDaysOnlyDays: List<Int> = emptyList(),
    val nonWorkingDay: List<NonWorkingDaysEntity> = emptyList(),
    val country: CountryEntity? = null,
    val filteredNonWorkingDaysList: List<NonWorkingDaysEntity> = emptyList(), // The filtered list of users
    val searchQuery: String = "",
)