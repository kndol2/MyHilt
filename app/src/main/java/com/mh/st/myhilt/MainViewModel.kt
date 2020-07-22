package com.mh.st.myhilt

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.mh.st.myhilt.repository.Repository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import timber.log.Timber

class MainViewModel @ViewModelInject constructor(val repository: Repository): ViewModel() {

    fun fetchImages() {
        repository.fetchImages("restaurant")
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe ({
                Timber.d("response: $it")
            },{

            })
    }
}