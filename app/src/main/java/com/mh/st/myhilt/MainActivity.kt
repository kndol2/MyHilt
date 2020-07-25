package com.mh.st.myhilt

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.jakewharton.rxbinding3.widget.textChanges
import com.mh.st.myhilt.databinding.ActivityMainBinding
import com.mh.st.myhilt.repository.model.Documents
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit

/**
Application 클래스에 Hilt를 설정하고 애플리케이션 수준 구성요소를 사용할 수 있게 되면
Hilt는 @AndroidEntryPoint 주석이 있는 다른 Android 클래스에 종속 항목을 제공할 수 있습니다.
 */

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel by viewModels<MainViewModel>()

    private val adapter = ImageAdapter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.etSearch.textChanges()
            .debounce(500, TimeUnit.MILLISECONDS)
            .subscribeOn(Schedulers.io())
            .subscribe {
                viewModel.fetchImages(it.toString())
            }

        binding.recyclerIamge.adapter = adapter

        viewModel.ducumentLisveData.observe(this, Observer<List<Documents>> {
            adapter.setData(it)
        })
    }
}
