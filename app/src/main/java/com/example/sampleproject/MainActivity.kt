package com.example.sampleproject

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.sampleproject.databinding.ActivityMainBinding
import com.google.android.material.navigation.NavigationView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var toggle: ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        navController = findNavController(R.id.nav_host_fragment_activity_main)

        val toolbar: Toolbar = binding.toolbar
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(true)
        supportActionBar?.title = getString(R.string.attraction_list_page_title)

        navController.addOnDestinationChangedListener { controller, destination, _ ->
            when (destination.id) {
                R.id.listFragment -> {
                    toolbar.setTitle(R.string.attraction_list_page_title)
                }
                R.id.detailFragment -> {}
                R.id.myFavoriteFragment -> {
                    toolbar.setTitle(R.string.my_favorites)
                }
            }
        }

        setUpBottomNav()
        setUpDrawer()
    }

    private fun setUpDrawer() {
        toggle = ActionBarDrawerToggle(this, binding.drawerLayout, R.string.open, R.string.close)
        binding.drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val navView: NavigationView = binding.drawerNavView

        navView.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.listFragment -> {
                    if (navController.currentDestination?.id != R.id.listFragment) {
                        navController.navigate(NavGraphDirections.actionGlobalListFragment())
                        binding.drawerLayout.closeDrawers()
                    }
                }
                R.id.myFavoriteFragment -> {
                    if (navController.currentDestination?.id != R.id.myFavoriteFragment) {
                        navController.navigate(NavGraphDirections.actionGlobalMyFavoriteFragment())
                        binding.drawerLayout.closeDrawers()
                    }
                }
            }
            true
        }

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (toggle.onOptionsItemSelected(item)) {
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    private fun setUpBottomNav() {
        binding.bottomNavView.setOnItemSelectedListener { item ->
            when(item.itemId) {
                R.id.listFragment -> {
                    if (navController.currentDestination?.id != R.id.listFragment) {
                        navController.navigate(NavGraphDirections.actionGlobalListFragment())
                    }
                }

                R.id.myFavoriteFragment -> {
                    if (navController.currentDestination?.id != R.id.myFavoriteFragment) {
                        navController.navigate(NavGraphDirections.actionGlobalMyFavoriteFragment())
                    }
                }
            }
            true
        }
    }

    override fun onBackPressed() {
        navController.navigateUp()
    }
}