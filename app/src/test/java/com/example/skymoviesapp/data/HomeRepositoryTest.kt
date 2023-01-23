package com.example.skymoviesapp.data

import com.example.skymoviesapp.TestCoroutineRule
import com.example.skymoviesapp.ui.main.HomeApiService
import com.example.skymoviesapp.ui.main.HomeRepository
import org.junit.Before
import org.junit.Rule
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class HomeRepositoryTest {

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    @Mock
    lateinit var mockNewsApi: HomeApiService

    private lateinit var testSut: HomeRepository

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        testSut = HomeRepository(
            mockNewsApi
        )
    }

}