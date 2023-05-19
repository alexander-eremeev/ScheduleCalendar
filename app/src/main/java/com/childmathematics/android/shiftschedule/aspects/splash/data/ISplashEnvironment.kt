package com.childmathematics.android.shiftschedule.aspects.splash.data

import com.childmathematics.android.shiftschedule.model.Credential
import kotlinx.coroutines.flow.Flow

interface ISplashEnvironment {
    fun getCredential(): Flow<Credential>
}
