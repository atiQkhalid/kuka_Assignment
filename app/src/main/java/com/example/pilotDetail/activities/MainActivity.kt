package com.example.pilotDetail.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.pilotDetail.R
import com.example.pilotDetail.database.model.response.ItemsItem
import com.example.pilotDetail.extensions.gone
import com.example.pilotDetail.extensions.replaceFragmentSafely
import com.example.pilotDetail.extensions.visible
import com.example.pilotDetail.fragments.HomeFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*

/**
 * MainActivity.kt, Main activity class, launcher activity
 */
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navigationView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)

        replaceFragmentSafely(HomeFragment())
    }

    fun storeSelectedArticle(articleItem : ItemsItem){
       // mainActivityViewModel.storeSelectedArticle(articleItem)
    }

    fun updateNavigationViewVisibility(visible: Boolean) {
        if (visible)
            navigationView.visible()
        else
            navigationView.gone()
    }

    private val onNavigationItemSelectedListener =
        BottomNavigationView.OnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> {
                    replaceFragmentSafely(HomeFragment())
                    return@OnNavigationItemSelectedListener true
                }
            }
            false
        }
}