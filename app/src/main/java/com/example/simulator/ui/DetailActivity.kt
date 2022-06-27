 package com.example.simulator.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.simulator.databinding.ActivityDetailBinding
import com.example.simulator.domain.Match

 class DetailActivity : AppCompatActivity() {

     object Extras {
         const val MATCH = "EXTRA_MATCH"
     }

     private lateinit var binding: ActivityDetailBinding

     override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

         setSupportActionBar(binding.toolbar)
         supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }
}