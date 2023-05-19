package com.childmathematics.android.shiftschedule.basis.datasource.server

import com.childmathematics.android.shiftschedule.model.Credential
import com.childmathematics.android.shiftschedule.model.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.util.*
import javax.inject.Inject

class ServerManager @Inject constructor() {

    fun fetchCredential(): Flow<Credential> {
        return flow { emit(Credential(token = UUID.randomUUID().toString())) }
    }

    fun fetchUser(email: String): Flow<User> {
//        fun fetchUser(email: String, password: String): Flow<User> {
        return flow { emit(User(email)) }
    }

}
