package com.childmathematics.android.basement.core.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

/**
 * State: A type that describes the data your feature needs to perform its logic and render its UI.
 *      The state is persistence during feature lifetime.
 * Effect: Similar to state but it is not persistence such as navigation, show toast, show snackbar.
 * Action: A type that represents all of the actions that cause the state of the application to
 *      change such as user actions, notifications, event sources and more.
 * Environment: A type that holds any dependencies the feature needs, such as
 *      API clients, analytics clients, etc to decouple between UI layer and Data layer.
 *      --------------------------------------------------------------------------------------
 * Состояние: тип, описывающий данные, необходимые вашей функции для выполнения своей логики и отображения пользовательского интерфейса.
 *      Состояние сохраняется в течение срока службы функции.
 * Эффект: Аналогично состоянию, но это не постоянство, такое как навигация, отображение тостов, отображение закусочной.
 * Действие: Тип, представляющий все действия, которые приводят к изменению состояния приложения.
 *      изменить, например, действия пользователя, уведомления, источники событий и многое другое.
 * Environment: тип, который содержит любые зависимости, необходимые функции, такие как
 *      Клиенты API, клиенты аналитики и т. д., чтобы разделить уровень пользовательского интерфейса и уровень данных.
 */
abstract class StatefulViewModel<STATE, EFFECT, ACTION, ENVIRONMENT>(
    initialState: STATE,
    protected val environment: ENVIRONMENT
) : ViewModel() {

    private val _state = MutableStateFlow(initialState)

    private val _effect: MutableStateFlow<EFFECT?> = MutableStateFlow(null)

    val state: StateFlow<STATE> = _state.asStateFlow()

    val effect: StateFlow<EFFECT?> = _effect.asStateFlow()

    abstract fun dispatch(action: ACTION)

    protected fun setState(newState: STATE.() -> STATE) {
        _state.update(newState)
    }

    protected fun setEffect(newEffect: EFFECT) {
        _effect.update { newEffect }
    }

    fun resetEffect() {
        _effect.update { null }
    }

    private fun stateValue(): STATE {
        return state.value
    }

}
