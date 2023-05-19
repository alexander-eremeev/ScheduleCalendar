package com.childmathematics.android.shiftschedule.aspects.setting.ui

import com.childmathematics.android.basement.core.viewmodel.StatefulViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SettingViewModel @Inject constructor() :
    StatefulViewModel<SettingState, Unit, Unit, Unit>(SettingState(), Unit) {

    override fun dispatch(action: Unit) {
    }

}
