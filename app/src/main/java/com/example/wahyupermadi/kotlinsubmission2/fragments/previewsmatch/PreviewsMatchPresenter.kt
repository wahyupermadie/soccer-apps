package com.example.wahyupermadi.kotlinsubmission2.fragments.previewsmatch

import com.example.wahyupermadi.kotlinsubmission2.api.ApiClient
import com.example.wahyupermadi.kotlinsubmission2.api.ApiInterface
import rx.Scheduler
import rx.Subscription
import rx.subscriptions.CompositeSubscription

class PreviewsMatchPresenter(val mainScheduler: Scheduler, val backgroundScheduler: Scheduler, val mView : PreviewsMatchContract.View) : PreviewsMatchContract.Presenter {
    val subscriptions = CompositeSubscription()
    val apiInterface = ApiClient.client.create(ApiInterface::class.java)
    override fun getMatch() {
        val subscription : Subscription
        mView.showProgressBar()
        subscriptions.clear();
        subscription = apiInterface.getPastMatch()
                .observeOn(mainScheduler)
                .subscribeOn(backgroundScheduler)
                .subscribe{
                    mView.displayPreviewsMatch(it.events!!)
                    mView.hideProgressBar()
                }
        subscriptions.add(subscription)
    }

}