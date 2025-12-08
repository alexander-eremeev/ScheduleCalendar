package com.childmathematics.android.shiftschedule.domain.usecases

import com.childmathematics.android.shiftschedule.data.models.NonWorkingDaysEntity
import com.childmathematics.android.shiftschedule.domain.repository.NonWorkingDaysRepository
import com.childmathematics.android.shiftschedule.presentation.util.UiResources
import jakarta.inject.Inject
import kotlinx.coroutines.flow.Flow

class GetAllNonWorkingDaysUseCase @Inject constructor (
    private val nonWorkingDayRepository: NonWorkingDaysRepository
) {
    operator fun invoke () : Flow<UiResources<List<NonWorkingDaysEntity>>> {
        return nonWorkingDayRepository.getAllNonWorkingDays()
    }
}

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
