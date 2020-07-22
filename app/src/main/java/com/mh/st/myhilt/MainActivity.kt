package com.mh.st.myhilt

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint

/**
Application 클래스에 Hilt를 설정하고 애플리케이션 수준 구성요소를 사용할 수 있게 되면
Hilt는 @AndroidEntryPoint 주석이 있는 다른 Android 클래스에 종속 항목을 제공할 수 있습니다.
 */
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel.fetchImages()
    }
}
