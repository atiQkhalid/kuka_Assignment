package com.example.pilotDetail.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.pilotDetail.R
import com.example.pilotDetail.base.BaseFragment
import com.example.pilotDetail.database.model.response.ItemsItem
import com.example.pilotDetail.database.model.response.PilotDetailsDao
import com.example.pilotDetail.extensions.backPress
import com.example.pilotDetail.extensions.replaceFragment
import com.example.pilotDetail.extensions.showToastMsg
import com.example.pilotDetail.utils.load
import com.example.pilotDetail.viewmodels.DetailViewModel
import kotlinx.android.synthetic.main.fragment_details.*

/**
 * DetailsFragment.kt
 */
class DetailsFragment : BaseFragment(), View.OnClickListener, DetailViewModel.View {

    private val detailViewModel: DetailViewModel by viewModels()
    private lateinit var pilot: ItemsItem

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        detailViewModel.attachView(this)
        detailViewModel.getPilotDetail(pilot.id!!)
        mainActivity.updateNavigationViewVisibility(false)


        imgBack.setOnClickListener(this)
        imgShare.setOnClickListener(this)
        imgFavourite.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.imgBack -> mainActivity.backPress()
            R.id.imgShare -> {

            }
            /*R.id.btnNewsSource -> {
                replaceFragment(WebViewFragment(articlesItem.url!!))
            }
            R.id.imgFavourite -> {
                detailViewModel.saveNewsInDB(articlesItem)
            }*/
        }
    }

    override fun onUpdateUser(message: String) {
        showToastMsg(message)
    }

    override fun showProgressBar() {
        progressDialog.show()
    }

    override fun dismissProgressBar() {
        progressDialog.dismiss()
    }
}
