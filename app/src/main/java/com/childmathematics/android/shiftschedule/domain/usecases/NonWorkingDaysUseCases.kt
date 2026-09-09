package com.childmathematics.android.shiftschedule.domain.usecases

import com.childmathematics.android.shiftschedule.data.models.NonWorkingDaysEntity
import com.childmathematics.android.shiftschedule.domain.repository.NonWorkingDaysRepository
import com.childmathematics.android.shiftschedule.presentation.util.UiResources
import jakarta.inject.Inject
import kotlinx.coroutines.flow.Flow

class GetAllHoliDaysYearUseCase @Inject constructor (
    private val nonWorkingDayRepository: NonWorkingDaysRepository
) {
    operator fun invoke (year : Int) : Flow<UiResources<List<NonWorkingDaysEntity>>> {
        return nonWorkingDayRepository.getAllHoliDaysYear(year)
    }
}
//------------------------------------------------------------
class GetHoliDaysOnlyDateYearUseCase @Inject constructor (
    private val nonWorkingDayRepository: NonWorkingDaysRepository
) {
    operator fun invoke (year : Int) : Flow<UiResources<List<String>>> {
        return nonWorkingDayRepository.getHoliDaysOnlyDateYear(year)
    }
}
//------------------------------------------------------------
class GetAllNonWorkingDaysYearUseCase @Inject constructor (
    private val nonWorkingDayRepository: NonWorkingDaysRepository
) {
    operator fun invoke (year : Int) : Flow<UiResources<List<NonWorkingDaysEntity>>> {
        return nonWorkingDayRepository.getAllNonWorkingDaysYear(year)
    }
}
//------------------------------------------------------------
class GetAllHoliDaysUseCase @Inject constructor (
    private val nonWorkingDayRepository: NonWorkingDaysRepository
) {
    operator fun invoke (year : Int,month : Int) : Flow<UiResources<List<NonWorkingDaysEntity>>> {
        return nonWorkingDayRepository.getAllHoliDays(year,month)
    }
}
//------------------------------------------------------------
class GetHoliDaysOnlyDaysUseCase @Inject constructor (
    private val nonWorkingDayRepository: NonWorkingDaysRepository
) {
    operator fun invoke (year : Int,month : Int) : Flow<UiResources<List<Int>>> {
        return nonWorkingDayRepository.getHoliDaysOnlyDays(year,month)
    }
}
//------------------------------------------------------------
class GetAllNonWorkingDaysUseCase @Inject constructor (
    private val nonWorkingDayRepository: NonWorkingDaysRepository
) {
    operator fun invoke (year : Int,month : Int) : Flow<UiResources<List<NonWorkingDaysEntity>>> {
        return nonWorkingDayRepository.getAllNonWorkingDays(year,month)
    }
}
//------------------------------------------------------------
class GetNonWorkingDaysOnlyDateYearUseCase @Inject constructor (
    private val nonWorkingDayRepository: NonWorkingDaysRepository
) {
    operator fun invoke (year : Int) : Flow<UiResources<List<String>>> {
        return nonWorkingDayRepository.getNonWorkingDaysOnlyDateYear(year)
    }
}
//------------------------------------------------------------
class GetNonWorkingDaysOnlyDaysUseCase @Inject constructor (
    private val nonWorkingDayRepository: NonWorkingDaysRepository
) {
    operator fun invoke (year : Int,month : Int) : Flow<UiResources<List<Int>>> {
        return nonWorkingDayRepository.getNonWorkingDaysOnlyDays(year,month)
    }
}
//------------------------------------------------------------
class GetNonWorkingDaysOnlyWorkDateYearUseCase @Inject constructor (
    private val nonWorkingDayRepository: NonWorkingDaysRepository
) {
    operator fun invoke (year : Int) : Flow<UiResources<List<String>>> {
        return nonWorkingDayRepository.getNonWorkingDaysOnlyWorkDateYear(year)
    }
}
//------------------------------------------------------------
/*
    fun getNonWorkingDaysOnlyWorkDateYear(year: Int): Flow<UiResources<List<String>>>
 */
class GetNonWorkingDaysOnlyWorkDaysUseCase @Inject constructor (
    private val nonWorkingDayRepository: NonWorkingDaysRepository
) {
    operator fun invoke (year : Int,month : Int) : Flow<UiResources<List<Int>>> {
        return nonWorkingDayRepository.getNonWorkingDaysOnlyWorkDays(year,month)
    }
}
//------------------------------------------------------------
class GetNonWorkingDayUseCase @Inject constructor (
    private val nonWorkingDayRepository: NonWorkingDaysRepository
) {
    operator fun invoke (nonWorkingDayId: Int): Flow<UiResources<List<NonWorkingDaysEntity>>> {
        return nonWorkingDayRepository.getNonWorkingDay(nonWorkingDayId)
    }
}

class InsertNonWorkingDayUseCase @Inject constructor (
    private val nonWorkingDayRepository: NonWorkingDaysRepository
) {
    suspend operator fun invoke (shortName: NonWorkingDaysEntity){
        return nonWorkingDayRepository.insertNonWorkingDay(shortName)
    }
}

class UpdateNonWorkingDayUseCase @Inject constructor (
    private val nonWorkingDayRepository: NonWorkingDaysRepository
) {
    suspend operator fun invoke (shortName: NonWorkingDaysEntity){
        return nonWorkingDayRepository.updateNonWorkingDay(shortName)
    }
}

class DeleteNonWorkingDayUseCase @Inject constructor (
    private val nonWorkingDayRepository: NonWorkingDaysRepository
) {
    suspend operator fun invoke (shortName: NonWorkingDaysEntity){
        return nonWorkingDayRepository.deleteNonWorkingDay(shortName)
    }
}

class SearchNonWorkingDayUseCase @Inject constructor (
    private val nonWorkingDayRepository: NonWorkingDaysRepository
) {
    operator fun invoke (query: String) : Flow<UiResources<List<NonWorkingDaysEntity>>> {
        return nonWorkingDayRepository.searchNonWorkingDay(query)
    }
}
