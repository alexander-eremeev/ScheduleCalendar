package com.childmathematics.android.shiftschedule.aspects.logout.data

import com.childmathematics.android.shiftschedule.model.User
import kotlinx.coroutines.flow.Flow

interface ILogoutEnvironment {
    suspend fun logout()
    fun getUser(): Flow<User>
}
