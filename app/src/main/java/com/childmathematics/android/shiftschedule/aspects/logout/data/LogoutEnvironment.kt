package com.childmathematics.android.shiftschedule.aspects.logout.data

import com.childmathematics.android.shiftschedule.basis.datasource.preference.PreferenceManager
import com.childmathematics.android.shiftschedule.model.Credential
import com.childmathematics.android.shiftschedule.model.User
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LogoutEnvironment @Inject constructor(
    private val preferenceManager: PreferenceManager
) : ILogoutEnvironment {
    override suspend fun logout() {
//        preferenceManager.setCredential(Credential(token = ""))
//        preferenceManager.setUser(User(email = ""))
    }

    override fun getUser(): Flow<User> {
        TODO("Not yet implemented")
    }
    /*
        override fun getUser(): Flow<User> {
            return preferenceManager.getUser()
        }


     */
}
