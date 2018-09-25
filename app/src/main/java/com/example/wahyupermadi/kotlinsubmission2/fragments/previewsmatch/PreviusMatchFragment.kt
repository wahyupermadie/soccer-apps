package com.example.wahyupermadi.kotlinsubmission2.fragments.previewsmatch

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.wahyupermadi.kotlinsubmission2.R
import com.example.wahyupermadi.kotlinsubmission2.model.Matchs
import org.jetbrains.anko.support.v4.toast
import rx.schedulers.Schedulers

class PreviusMatchFragment: Fragment(), PreviewsMatchContract.View {
    lateinit var mPresenter : PreviewsMatchPresenter
    var prevmatch: MutableList<Matchs> = mutableListOf()
    lateinit var recyclerView: RecyclerView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_prevmatch, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        mPresenter = PreviewsMatchPresenter(Schedulers.immediate(), Schedulers.immediate(), this)
    }
    override fun displayPreviewsMatch(matchs: List<Matchs>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showAllert(t: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showProgressBar() {
        toast("test")
    }

    override fun hideProgressBar() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    companion object {
        fun newInstance(): PreviusMatchFragment = PreviusMatchFragment()
    }
}
