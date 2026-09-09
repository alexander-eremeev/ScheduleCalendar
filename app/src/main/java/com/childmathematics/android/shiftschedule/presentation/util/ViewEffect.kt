package com.childmathematics.android.shiftschedule.presentation.util

//эффектов, таких как показ запечатанных Snackbar
sealed class SnackbarEffect { data class ShowSnackbar ( val message: String,
                                                        val actionLabel: String? = null ):
    SnackbarEffect()
}
