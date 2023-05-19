package com.childmathematics.android.shiftschedule.aspects.login.data

import kotlinx.coroutines.flow.Flow

interface ILoginEnvironment {
    fun login(email: String): Flow<Any>
//    fun login(email: String, password: String): Flow<Any>
}
