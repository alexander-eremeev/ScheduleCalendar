package com.childmathematics.android.shiftschedule.aspects.login.data

import com.childmathematics.android.shiftschedule.basis.datasource.preference.PreferenceManager
import com.childmathematics.android.shiftschedule.basis.datasource.server.ServerManager
import com.childmathematics.android.shiftschedule.model.Credential
import com.childmathematics.android.shiftschedule.model.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.merge
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

class LoginEnvironment @Inject constructor(
    private val serverManager: ServerManager,
    private val preferenceManager: PreferenceManager
) : ILoginEnvironment {

    override fun login(email: String): Flow<Any> {
//        override fun login(email: String, password: String): Flow<Any> {
        return merge(
            serverManager.fetchCredential(),
            serverManager.fetchUser(email)
//                    serverManager.fetchUser(email, password)
        ).onEach {
            when (it) {
                is Credential -> preferenceManager.setCredential(it)
                is User -> preferenceManager.setUser(it)
            }
        }
    }

}
