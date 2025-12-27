package com.childmathematics.android.shiftschedule.presentation.ui.nonworkingdays.uimodels

// ViewIntent для обработки действий пользователя
sealed class NonWorkingDaysViewIntent {
    data class LoadHoliDaysOnlyDateYear(val year: Int) : NonWorkingDaysViewIntent() // Intent for loading
    data class LoadNonWorkingDaysOnlyDateYear(val year: Int) : NonWorkingDaysViewIntent() // Intent for loading
    data class LoadNonWorkingDaysOnlyWorkDateYear(val year: Int) : NonWorkingDaysViewIntent() // Intent for loading

    data class LoadHoliDaysYear(val year: Int) : NonWorkingDaysViewIntent() // Intent for loading
    data class LoadHoliDays(val year: Int, val month: Int) : NonWorkingDaysViewIntent() // Intent for loading
    data class LoadHoliOnlyDays (val year: Int, val month: Int) : NonWorkingDaysViewIntent() // Intent for loading
    data class LoadNonWorkingDaysYear(val year: Int) : NonWorkingDaysViewIntent() // Intent for loading
    data class LoadNonWorkingDays(val year: Int, val month: Int) : NonWorkingDaysViewIntent() // Intent for loading
    data class LoadNonWorkingOnlyDays (val year: Int, val month: Int) : NonWorkingDaysViewIntent() // Intent for loading
    data class LoadNonWorkingOnlyWorkDays (val year: Int, val month: Int) : NonWorkingDaysViewIntent() // Intent for loading
    data class LoadNonWorkingDay(val nonWorkingDayId: Int) : NonWorkingDaysViewIntent() // Intent for loading
    data class InsertNonWorkingDay(val shortName: String, val longName: String, val nonWorkingDaysId: Int?)
                        : NonWorkingDaysViewIntent() //
    data class DeleteNonWorkingDay(val shortName: String) : NonWorkingDaysViewIntent() // Intent for deleting a user
    data object ClearNonWorkingDays : NonWorkingDaysViewIntent() // Intent for clearing users
    data class SearchNonWorkingDay(val query: String) : NonWorkingDaysViewIntent() // Intent for searching users
    data class UpdateNonWorkingDay(val name: String) : NonWorkingDaysViewIntent() // Intent for updating name input
    data object UndoDeleteNonWorkingDay : NonWorkingDaysViewIntent() // Intent for undoing a delete
}