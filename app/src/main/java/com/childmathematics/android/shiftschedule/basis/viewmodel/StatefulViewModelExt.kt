package com.childmathematics.android.shiftschedule.basis.viewmodel

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
//import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.childmathematics.android.basement.core.viewmodel.StatefulViewModel
import kotlinx.coroutines.CoroutineScope

//@OptIn(ExperimentalLifecycleComposeApi::class)
@Composable
fun <STATE, EFFECT, ACTION, ENVIRONMENT> HandleEffect(
    viewModel: StatefulViewModel<STATE, EFFECT, ACTION, ENVIRONMENT>,
    handle: suspend CoroutineScope.(EFFECT) -> Unit
) {
    val effect by viewModel.effect.collectAsStateWithLifecycle()
    LaunchedEffect(effect) {
        effect?.let {
            handle(it)
            viewModel.resetEffect()
        }
    }
}
