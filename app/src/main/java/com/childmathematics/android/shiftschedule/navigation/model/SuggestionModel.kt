package com.childmathematics.android.shiftschedule.navigation.model

data class SuggestionModel(val tag: String) {
    val id = tag.hashCode()
}
