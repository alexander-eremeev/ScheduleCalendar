package com.childmathematics.android.shiftschedule.aspects.splash.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.childmathematics.android.shiftschedule.basis.viewmodel.HandleEffect
import com.childmathematics.android.shiftschedule.runtime.navigation.AuthFlow
import com.childmathematics.android.shiftschedule.runtime.navigation.HomeFlow
import com.childmathematics.android.shiftschedule.runtime.navigation.MainFlow

@Composable
fun SplashScreen(
    navController: NavController,
    viewModel: SplashViewModel
) {
    HandleEffect(viewModel) {
        when (it) {
            SplashEffect.NavigateToDashboard -> {
                navController.navigate(HomeFlow.Root.route) {
                    popUpTo(MainFlow.Root.route) {
                        inclusive = true
                    }
                }
            }
            SplashEffect.NavigateToLogin -> {
                navController.navigate(AuthFlow.Root.route) {
                    popUpTo(MainFlow.Root.route) {
                        inclusive = true
                    }
                }
            }
        }
    }
}
