package com.example.sampleproject

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.example.sampleproject.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController

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
                    binding.btnFav.visibility = View.VISIBLE
                    binding.btnFav.setOnClickListener {
                        controller.navigate(NavGraphDirections.actionGlobalMyFavoriteFragment())
                    }
                }
                R.id.detailFragment -> {
                    binding.btnFav.visibility = View.GONE
                }
                R.id.myFavoriteFragment -> {
                    toolbar.setTitle(R.string.my_favorites)
                    binding.btnFav.visibility = View.GONE
                }
            }
        }
    }

    override fun onBackPressed() {
        navController.navigateUp()
    }
}