package com.dhxxn.bindingapp.view.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.commit
import com.dhxxn.bindingapp.R
import com.dhxxn.bindingapp.databinding.ActivityMainBinding
import com.dhxxn.bindingapp.view.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportFragmentManager.commit(true) {
            val mainFragment = MainFragment()
            replace(R.id.main_content, mainFragment)
            setPrimaryNavigationFragment(mainFragment)
        }
    }
}