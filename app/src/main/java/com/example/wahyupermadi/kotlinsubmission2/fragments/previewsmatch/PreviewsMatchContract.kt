package com.example.wahyupermadi.kotlinsubmission2.fragments.previewsmatch

import com.example.wahyupermadi.kotlinsubmission2.model.Matchs

interface PreviewsMatchContract {

    interface View {
        fun displayPreviewsMatch(matchs: List<Matchs>)
        fun showAllert(t : String)
        fun showProgressBar()
        fun hideProgressBar()
    }

    interface Presenter{
        fun getMatch()
    }
}