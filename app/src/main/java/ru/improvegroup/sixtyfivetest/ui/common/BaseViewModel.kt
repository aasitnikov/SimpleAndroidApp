package ru.improvegroup.sixtyfivetest.ui.common

import androidx.annotation.CallSuper
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

abstract class BaseViewModel : ViewModel() {

    private val compositeDisposable = CompositeDisposable()
    //TODO: replace with singleLiveEvent
    val error = MutableLiveData<String>()

    protected fun handleError(ex: Throwable) {
        //TODO: log
        error.postValue(ex.message)
    }

    protected fun Disposable.untilDestroy() {
        compositeDisposable.add(this)
    }

    protected fun <T : Any> Single<T>.bindProgress(liveData: MutableLiveData<Boolean>): Single<T> {
        return doOnSubscribe { liveData.postValue(true) }
            .doAfterTerminate { liveData.postValue(false) }
    }

    @CallSuper
    override fun onCleared() {
        compositeDisposable.dispose()
    }
}