package dev.dvdciri.sampleapp.framework.viewmodel.viewstate

import android.support.annotation.StringRes

sealed class ErrorUiModel {
    object None : ErrorUiModel()
    data class Message(@StringRes val message: Int) : ErrorUiModel()
}