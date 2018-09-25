package com.example.wahyupermadi.kotlinsubmission2.fragments

import com.example.wahyupermadi.kotlinsubmission2.api.ApiClient
import com.example.wahyupermadi.kotlinsubmission2.api.ApiInterface
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.mockito.Mock
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations

class PreviusMatchFragmentTest {


    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun getMatch() {
        val apiService = mock(ApiInterface::class.java)
        apiService.getNextMatch()
        verify(apiService).getNextMatch()
    }
}