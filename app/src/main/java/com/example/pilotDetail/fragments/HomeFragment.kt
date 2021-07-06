package com.example.pilotDetail.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DefaultItemAnimator
import com.example.pilotDetail.R
import com.example.pilotDetail.adapter.PilotAdapter
import com.example.pilotDetail.base.BaseFragment
import com.example.pilotDetail.database.model.response.ItemsItem
import com.example.pilotDetail.extensions.replaceFragment
import com.example.pilotDetail.extensions.showToastMsg
import com.example.pilotDetail.viewmodels.HomeViewModel
import kotlinx.android.synthetic.main.fragment_home.*

/**
 * HomeFragment.kt
 */
class HomeFragment : BaseFragment(), HomeViewModel.View, PilotAdapter.NoteItemClickListener {
    private val homeViewModel: HomeViewModel by viewModels()
    private var pilotAdapter: PilotAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        homeViewModel.let {
            it.attachView(this)
            it.getNewsArticles()
        }
        mainActivity.updateNavigationViewVisibility(true)
        onObserveNewsList()
    }

    //once we get the data from repo, populate it with the help of the adapter, NewsAdapter()
    private fun onObserveNewsList() {
        pilotAdapter = PilotAdapter(this)

        homeViewModel.pilotListData.observe(viewLifecycleOwner) {
            it?.let {
                pilotAdapter?.setItems(it)
            }
        }

        pilotAdapter.let {
            newsRecyclerView.apply {
                itemAnimator = DefaultItemAnimator()
                adapter = it
            }
            it?.notifyDataSetChanged()
        }
    }


    //To update the user against any unusual situation
    override fun onUpdateUser(message: String) {
        showToastMsg(message)
    }

    //Show the progress while fetching date from repo
    override fun showProgressBar() {
        progressDialog.show()
    }

    //Hide the progress after fetching date from repo or in error case
    override fun dismissProgressBar() {
        progressDialog.dismiss()
    }

    //On note item click listener
    override fun onItemClickListener(note: ItemsItem) {
        mainActivity.storeSelectedArticle(note)
        replaceFragment(DetailsFragment())
    }
}
