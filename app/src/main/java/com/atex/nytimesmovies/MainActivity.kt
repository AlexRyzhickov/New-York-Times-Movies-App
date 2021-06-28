package com.atex.nytimesmovies

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import com.atex.nytimesmovies.model.MoviesViewModel
import kotlinx.coroutines.flow.first

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        checkAddData()
    }

    private fun checkAddData() {
        val viewModel = ViewModelProvider(this).get(MoviesViewModel::class.java)

        lifecycleScope.launchWhenCreated {
            viewModel.getListData().first {
                // Get response from server
                Handler(Looper.getMainLooper()).postDelayed({
                    val action =
                        SplashScreenFragmentDirections.actionSplashScreenFragmentToMoviesFragment()
                    findNavController(R.id.nav_host_fragment).navigate(action)
                }, 1200)
                true
            }
        }
    }
}