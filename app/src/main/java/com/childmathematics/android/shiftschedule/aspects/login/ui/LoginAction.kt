package com.childmathematics.android.shiftschedule.aspects.login.ui

sealed class LoginAction {
    data class ChangeEmail(val value: String) : LoginAction()
    data class ChangePassword(val value: String) : LoginAction()
    object ClickLogin : LoginAction()
    object ClickImePasswordDone : LoginAction()
}
