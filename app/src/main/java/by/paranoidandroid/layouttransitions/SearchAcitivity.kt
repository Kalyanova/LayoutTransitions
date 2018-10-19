package by.paranoidandroid.layouttransitions

import android.app.Activity
import android.os.Bundle
import android.support.v7.widget.SearchView
import android.transition.Transition
import android.transition.TransitionInflater
import android.transition.TransitionManager
import android.util.Log
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView

class SearchAcitivity: Activity() {
    var searchView: SearchView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        searchView = findViewById<SearchView?>(R.id.search_view)

        /*val searchViewIcon: ImageView = findViewById(android.support.v7.appcompat.R.id.search_mag_icon) as ImageView
        val linearLayoutSearchView = searchViewIcon.parent as ViewGroup
        linearLayoutSearchView.removeView(searchViewIcon)
        linearLayoutSearchView.addView(searchViewIcon)*/

        searchView?.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

            override fun onQueryTextSubmit(p0: String?): Boolean {
                animateTransition()
                return false
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                return false
            }
        })
    }

    private fun animateTransition() {
        val searchResult = TextView(this).apply {
            text = getString(R.string.label_search_result)
        }

        val rootView: ViewGroup = findViewById(R.id.search_page)
        val autoTransition: Transition = TransitionInflater.from(this)
                .inflateTransition(R.transition.transition_set_auto)
        TransitionManager.beginDelayedTransition(rootView, autoTransition)
        rootView.removeView(searchView)
        rootView.addView(searchResult)
    }
}