package dev.arildo.coroutinesdemo.menu.activity

import android.content.Intent
import android.os.Bundle
import dev.arildo.coroutinesdemo.R
import dev.arildo.coroutinesdemo.animations.activity.AnimationsActivity
import dev.arildo.coroutinesdemo.core.base.BaseActivity
import dev.arildo.coroutinesdemo.databinding.ActivityMenuBinding

class MenuActivity : BaseActivity<ActivityMenuBinding>(R.layout.activity_menu) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setupListener()


    }

    private fun setupListener() {
        binding.btnAnimations.setOnClickListener {
            startActivity(Intent(this, AnimationsActivity::class.java))

        }
        binding.btnSingleRequest.setOnClickListener {
            startActivity(Intent(this, AnimationsActivity::class.java))
        }
        binding.btnMultipleRequests.setOnClickListener {
            startActivity(Intent(this, AnimationsActivity::class.java))
        }
    }
}
