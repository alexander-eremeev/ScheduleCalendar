package com.childmathematics.android.shiftschedule.aspects.logout.ui

import androidx.compose.runtime.Immutable
import com.childmathematics.android.shiftschedule.model.User

@Immutable
data class LogoutState(val user: User = User())
