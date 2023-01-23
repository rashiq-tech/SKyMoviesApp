package com.example.skymoviesapp.feature

import android.content.Context
import com.example.skymoviesapp.TestCoroutineRule
import com.example.skymoviesapp.ui.main.HomeRepository
import com.example.skymoviesapp.ui.main.MainViewModel
import org.junit.Before
import org.junit.Rule
import org.mockito.Mock
import org.mockito.Mockito.mock
import org.mockito.MockitoAnnotations

class MainViewModelTest {

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    @Mock
    lateinit var mockNewsRepository: HomeRepository
    private lateinit var testSut: MainViewModel

    val context: Context = mock(Context::class.java)

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        testSut = MainViewModel(mockNewsRepository, context)
    }

}