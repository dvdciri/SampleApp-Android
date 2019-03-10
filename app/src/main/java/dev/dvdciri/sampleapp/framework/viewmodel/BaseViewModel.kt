package dev.dvdciri.sampleapp.framework.viewmodel

import android.arch.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable

open class BaseViewModel : ViewModel() {

    val compositeDisposable = CompositeDisposable()

    public override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }
}
