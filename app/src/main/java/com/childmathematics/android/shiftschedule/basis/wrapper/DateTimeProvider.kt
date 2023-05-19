package com.childmathematics.android.shiftschedule.basis.wrapper

import java.time.LocalDateTime

interface DateTimeProvider {
    fun now(): LocalDateTime
}

class DateTimeProviderImpl : DateTimeProvider {
    override fun now(): LocalDateTime {
        return LocalDateTime.now()
    }
}
