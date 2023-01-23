package com.example.skymoviesapp.feature

import com.example.skymoviesapp.TestCoroutineRule
import com.example.skymoviesapp.ui.feature.movies.repository.HomeRepository
import com.example.skymoviesapp.ui.feature.movies.viewmodel.MainViewModel
import org.junit.Before
import org.junit.Rule
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class MainViewModelTest {

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    @Mock
    lateinit var mockMoviesRepository: HomeRepository
    private lateinit var testSut: MainViewModel

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        testSut = MainViewModel(mockMoviesRepository)
    }

}