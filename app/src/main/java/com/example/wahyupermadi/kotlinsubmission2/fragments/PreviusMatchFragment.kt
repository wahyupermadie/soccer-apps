package com.example.wahyupermadi.kotlinsubmission2.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.wahyupermadi.kotlinsubmission2.R

class PreviusMatchFragment: Fragment(){
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.fragment_prevmatch, container, false)

    companion object {
        fun newInstance(): PreviusMatchFragment = PreviusMatchFragment()
    }
}