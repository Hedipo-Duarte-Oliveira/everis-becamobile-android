package com.hedipoduarte.nttfilmes.presenter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.hedipoduarte.nttfilmes.R
import com.hedipoduarte.nttfilmes.presenter.fragments.PopularFragment
import com.hedipoduarte.nttfilmes.presenter.fragments.TrendingFragment

class MainActivity : AppCompatActivity(),
    BottomNavigationView.OnNavigationItemSelectedListener  {

    private val popularFragment = PopularFragment()
    private val trendingFragment = TrendingFragment()

    private var bottom_navigation_main: BottomNavigationView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        replaceFragment(trendingFragment)
        bottom_navigation_main = findViewById(R.id.bottom_navigation_main)
        bottom_navigation_main!!.selectedItemId = R.id.trending_view

        bottom_navigation_main!!.setOnNavigationItemSelectedListener(this)

    }

    private fun replaceFragment(fragment: Fragment? = null){

        if(fragment != null){

            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.fragment_container_main, fragment)
            transaction.commit()

        }

    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {

        when(item.itemId){

            R.id.popular_view -> replaceFragment(popularFragment)

            R.id.trending_view -> replaceFragment(trendingFragment)

        }
        return true

    }

}
