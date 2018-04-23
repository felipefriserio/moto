package br.com.android.moto.ui.splash

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.animation.AnimationUtils
import kotlinx.android.synthetic.main.activity_splash.*
import br.com.android.moto.ui.main.MainActivity
import com.example.logonrm.carros.R


class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        carregar();
    }

    fun carregar() {
        val animacao = AnimationUtils.loadAnimation(this, R.anim.animacao_splash)
        ivLogoSplash.startAnimation(animacao)
        Handler().postDelayed({
            startActivity(Intent(this, MainActivity::class.java))
            this.finish()
        }, 2400)
    }
}