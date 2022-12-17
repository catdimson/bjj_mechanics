package ru.catdimson.bjjmechanics.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ru.catdimson.bjjmechanics.R
import ru.catdimson.bjjmechanics.databinding.ActivityMainBinding
import ru.catdimson.bjjmechanics.ui.root.MainFragment

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        startRootFragment(savedInstanceState)
    }

    private fun startRootFragment(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.main_container, MainFragment.newInstance())
                .commit()
        }
    }
}