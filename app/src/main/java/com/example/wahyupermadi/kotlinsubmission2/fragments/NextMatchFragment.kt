package com.example.wahyupermadi.kotlinsubmission2.fragments

import android.support.v4.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.wahyupermadi.kotlinsubmission2.R

class NextMatchFragment : Fragment(){
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.fragment_nextmatch, container, false)

    companion object {
        fun newInstance(): NextMatchFragment = NextMatchFragment()
    }
}