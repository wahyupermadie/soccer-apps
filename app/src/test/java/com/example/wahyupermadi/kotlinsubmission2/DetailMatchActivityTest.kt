package com.example.wahyupermadi.kotlinsubmission2

import com.example.wahyupermadi.kotlinsubmission2.api.ApiInterface
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.mockito.Mockito
import org.mockito.Mockito.mock
import org.mockito.MockitoAnnotations

class DetailMatchActivityTest {

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun init() {
        val apiInterface = mock(ApiInterface::class.java)
        apiInterface.getDetailNextMatch("576526")
        Mockito.verify(apiInterface).getDetailNextMatch("576526")
    }
}