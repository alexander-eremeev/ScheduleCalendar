package com.childmathematics.android.basement.lib.navigation.model

data class SuggestionModel(val tag: String) {
    val id = tag.hashCode()
}
