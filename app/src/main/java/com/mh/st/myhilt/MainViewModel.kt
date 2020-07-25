package com.mh.st.myhilt

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mh.st.myhilt.repository.Repository
import com.mh.st.myhilt.repository.model.Documents
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.plusAssign
import io.reactivex.schedulers.Schedulers
import timber.log.Timber

class MainViewModel @ViewModelInject constructor(val repository: Repository): ViewModel() {

    val disposable = CompositeDisposable()

    private val _documentLiveData = MutableLiveData<List<Documents>>()
    val ducumentLisveData: LiveData<List<Documents>>
    get() = _documentLiveData

    override fun onCleared() {
        super.onCleared()

        disposable.dispose()
    }

    fun fetchImages(searchText: String) {
        disposable += repository.fetchImages(searchText)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe ({
                Timber.d("response: ${it.documents}")
                _documentLiveData.value = it.documents
            },{

            })
    }
}