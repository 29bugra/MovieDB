package com.mobillium.movieDB.core.extensions

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

fun RecyclerView.pagingListener(callBack: () -> Unit) {
    addOnScrollListener(object : RecyclerView.OnScrollListener() {
        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)
            if (dy > 0) {
                val visibleItemCount = recyclerView.layoutManager?.childCount
                val totalItemCount = recyclerView.layoutManager?.itemCount
                val linearLayoutManager = recyclerView.layoutManager as LinearLayoutManager
                val pastVisibleItems = linearLayoutManager.findFirstVisibleItemPosition()

                if (visibleItemCount != null && totalItemCount != null) {
                    if ((visibleItemCount + pastVisibleItems) >= totalItemCount && pastVisibleItems >= 0 && totalItemCount >= 10) {
                        callBack.invoke()
                    }
                }
            }
        }
    })
}