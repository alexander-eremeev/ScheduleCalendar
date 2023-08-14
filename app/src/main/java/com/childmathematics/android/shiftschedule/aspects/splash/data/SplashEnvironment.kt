package com.childmathematics.android.shiftschedule.aspects.splash.data

import com.childmathematics.android.shiftschedule.basis.datasource.preference.PreferenceManager
import com.childmathematics.android.shiftschedule.model.Credential
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SplashEnvironment @Inject constructor(
    private val preferenceManager: PreferenceManager
) : ISplashEnvironment {
    /*
    override fun getCredential(): Flow<Credential> {
        return preferenceManager.getCredential()
    }

 */
    override fun getCredential(): Flow<Credential> {
        TODO("Not yet implemented")
    }

}
