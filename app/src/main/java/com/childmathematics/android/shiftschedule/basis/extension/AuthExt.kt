package com.childmathematics.android.shiftschedule.basis.extension

import androidx.core.util.PatternsCompat
import com.childmathematics.android.shiftschedule.aspects.login.ui.LoginState
import com.childmathematics.android.shiftschedule.model.Credential

fun LoginState.canLogin() = email.isNotBlank() && password.isNotBlank()

fun LoginState.isValidEmail() = PatternsCompat.EMAIL_ADDRESS.matcher(email).matches()

fun Credential.isLoggedIn() = token.isNotBlank()
