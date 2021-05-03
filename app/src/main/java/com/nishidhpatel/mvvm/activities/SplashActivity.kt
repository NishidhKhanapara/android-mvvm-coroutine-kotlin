package com.nishidhpatel.mvvm.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.nishidhpatel.mvvm.ApplicationClass
import com.nishidhpatel.mvvm.R

class SplashActivity : AppCompatActivity() {

    private var mHandler: Handler? = null
    private val delay: Long = 3000;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        mHandler = Handler()
        mHandler!!.postDelayed(runnable, delay)
    }

    val runnable: Runnable = Runnable {
        if (!isFinishing) {


            val isLogin = ApplicationClass.mInstance?.sharedPreferences
                ?.getBoolean(getString(R.string.PREF_LOGIN), false)


            val mMainActivity = Intent(this@SplashActivity, MainActivity::class.java)
            startActivity(mMainActivity)
            overridePendingTransition(R.anim.anim_right_in, R.anim.anim_left_out)
            finish()


        }
    }


}
