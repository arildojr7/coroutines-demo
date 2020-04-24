package dev.arildo.coroutinesdemo.animations.activity

import android.os.Bundle
import android.view.View
import dev.arildo.coroutinesdemo.R
import dev.arildo.coroutinesdemo.core.base.BaseActivity
import dev.arildo.coroutinesdemo.core.util.*
import dev.arildo.coroutinesdemo.databinding.ActivityAnimationsBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class AnimationsActivity : BaseActivity<ActivityAnimationsBinding>(R.layout.activity_animations) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        launch {
            openScreenAnimation()
        }

        binding.btnShowUi.setOnClickListener {
            launch {
                hideViewsAnimation()
                showUiAnimation()
            }
        }

    }

    private suspend fun openScreenAnimation() {
        delay(400)
        binding.tvTitle.enterFromTop(500)

        delay(400)
        binding.tvWith.enterFromTop(500)

        delay(400)
        binding.tvCoroutines.enterFromTop(500)

        delay(600)
        binding.ivKotlinLogo.runTransition {
            visibility = View.VISIBLE
        }

        delay(300)
        binding.btnShowUi.enterFromBottom(900)
    }

    private suspend fun hideViewsAnimation() {
        delay(100)
        binding.tvCoroutines.exitToTop(500)
        binding.tvWith.exitToTop(400)
        binding.tvTitle.exitToTop(300)
        binding.ivKotlinLogo.runTransition { visibility = View.INVISIBLE }
        binding.btnShowUi.exitToBottom(800)
    }

    private suspend fun showUiAnimation() {
        binding.llTopHeader.enterFromTop(500)
        delay(300)
        binding.bottomNavigation.enterFromBottom(900)
    }
}
