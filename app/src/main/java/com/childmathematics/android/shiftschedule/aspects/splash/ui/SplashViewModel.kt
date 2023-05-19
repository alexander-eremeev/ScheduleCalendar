package com.childmathematics.android.shiftschedule.aspects.splash.ui

import androidx.lifecycle.viewModelScope
import com.childmathematics.android.basement.core.viewmodel.StatefulViewModel
import com.childmathematics.android.shiftschedule.aspects.splash.data.ISplashEnvironment
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    splashEnvironment: ISplashEnvironment
) : StatefulViewModel<Unit, SplashEffect, SplashAction, ISplashEnvironment>(Unit, splashEnvironment) {

    init {
        dispatch(SplashAction.AppLaunch)
    }

    override fun dispatch(action: SplashAction) {
        when (action) {
            is SplashAction.AppLaunch -> {
                viewModelScope.launch {
                    environment.getCredential()
                        .collect {
                            // if (it.isLoggedIn()) {
                                setEffect(SplashEffect.NavigateToDashboard)
//                            } else {
//                                setEffect(SplashEffect.NavigateToLogin)
//                            }
                        }
                }
            }
        }
    }
}

